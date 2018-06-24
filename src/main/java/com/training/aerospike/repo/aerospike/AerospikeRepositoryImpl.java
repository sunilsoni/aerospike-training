package com.training.aerospike.repo.aerospike;

import com.aerospike.client.Bin;
import com.aerospike.client.IAerospikeClient;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.GenerationPolicy;
import com.aerospike.client.policy.Policy;
import com.aerospike.client.policy.RecordExistsAction;
import com.aerospike.client.policy.WritePolicy;
import com.esotericsoftware.reflectasm.ConstructorAccess;
import com.training.aerospike.core.BinName;
import com.training.aerospike.core.Utils;
import com.training.aerospike.repo.Connection;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class AerospikeRepositoryImpl implements AerospikeRepository {

    private Connection<IAerospikeClient> connection;

    public AerospikeRepositoryImpl(Connection<IAerospikeClient> connection) {
        this.connection = connection;
    }

    @Override
    public void save(Object entity, String id) {
        Key key = createKey(entity.getClass(), id);
        log.info("key: {}", key);

        List<Bin> bins = new ArrayList<>();
        List<Field> fields = getAllDeclaredFields(entity.getClass());
        // log.debug("fields.size(): {}, entity.getClass(): {}", fields.size(), entity.getClass());

        for (Field field : fields) {
            Class fieldType = field.getType();
            Object fieldValue = getFieldValue(entity, field.getName());
            String columnName = getColumnName(field);
            bins.add(new Bin(columnName, fieldValue));
        }

        if (bins.size() > 0) {
            WritePolicy policy = new WritePolicy();
            //policy.sendKey = true;
            policy.generationPolicy = GenerationPolicy.EXPECT_GEN_EQUAL;
            policy.recordExistsAction = RecordExistsAction.UPDATE;

            IAerospikeClient client = connection.openSession();
            client.put(policy, key, bins.toArray(new Bin[bins.size()]));

            log.info(String.format("Data with id %s is persisted successfully!", id));
        }
    }

    @Override
    public void deleteBins(Object entity, String id) {
        Key key = createKey(entity.getClass(), id);
        List<Bin> deletedBins = new ArrayList<>();
        List<Field> fields = getAllDeclaredFields(entity.getClass());

        for (Field field : fields) {
            Class fieldType = field.getType();
            Object fieldValue = getFieldValue(entity, field.getName());
            if (fieldValue == null) {
                deletedBins.add(Bin.asNull(getColumnName(field)));
            }
        }

        IAerospikeClient client = connection.openSession();
        WritePolicy wPolicy = new WritePolicy();
        client.put(wPolicy, key, deletedBins.toArray(new Bin[deletedBins.size()]));
        log.info(String.format(" %s  Bins with id %s is deleted successfully!", deletedBins.size(), id));
    }

    @Override
    public boolean exists(Class clazz, String id) {
        IAerospikeClient client = connection.openSession();
        return client.exists(new Policy(), createKey(clazz, id));
    }

    @Override
    public void deleteByIds(String setName, Set ids) {
        IAerospikeClient client = connection.openSession();
        WritePolicy wPolicy = new WritePolicy();
        try {
            ids.stream()
                    .map(id -> createKey(setName, String.valueOf(id)))
                    .forEach(key -> client.delete(wPolicy, (Key) key));
        } catch (AerospikeRepositoryException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public Object updateOrCreate(Object entity, String id) {
        Object obj = fetch(entity.getClass(), id);
        if (obj == null) {
            save(entity, id);
        }
        return entity;
    }

    @Override
    public Object fetch(Class clazz, String id) {
        List<Object> list = fetchAll(Collections.singletonList(id), clazz);
        if (list.isEmpty()) {
            return null;
        }
        return list.iterator().next();
    }

    //@Override
    public <T> List<T> fetchAll(Collection<String> ids, Class<T> clazz) {
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("ids can not be blank!");
        }
        Key[] keys = ids.stream()
                .map(id -> createKey(clazz, id))
                .collect(Collectors.toList())
                .toArray(new Key[ids.size()]);

        IAerospikeClient client = connection.openSession();
        Record[] records = client.get(null, keys);

        List<T> list = new ArrayList<>();
        for (Record record : records) {
            if (record != null) {
                list.add(convertToType(clazz, record));
            }
        }
        return list;
    }

    private Key createKey(Class<?> type, String id) {
        return createKey(Utils.getSetName(type), id);
    }

    private Key createKey(String setName, String id) {
        return new Key(connection.getConnectionInfo().getNamespace(), setName, id);
    }

    private List<Field> getAllDeclaredFields(Class<?> clazz) {
        List<Field> list = new ArrayList<>();
        for (Class nextClass = clazz; nextClass != Object.class; nextClass = nextClass.getSuperclass()) {
            log.debug("nextClass: {}", nextClass);

            Field[] fields = nextClass.getDeclaredFields();
            for (Field field : fields) {
                int modifiers = field.getModifiers();
                if (!Modifier.isStatic(modifiers)) {
                    list.add(field);
                }
            }
        }
        return list;
    }

    private <T> Object getFieldValue(T instance, String fieldName) {
        Object fieldValue;
        try {
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, instance.getClass());
            fieldValue = pd.getReadMethod().invoke(instance);
        } catch (java.beans.IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage(), e);
            throw new AerospikeRepositoryException(e);
        }
        return fieldValue;
    }

    private String getColumnName(Field field) {
        String columnName = field.getName();
        if (field.isAnnotationPresent(BinName.class)) {
            columnName = field.getAnnotation(BinName.class).value();
        }
        return columnName;
    }

    private <T> T convertToType(Class<T> clazz, Record record) {
        T instance = ConstructorAccess.get(clazz).newInstance();
        List<Field> fieldList = getAllDeclaredFields(instance.getClass());

        for (Field field : fieldList) {
            String fieldName = field.getName();
            Class fieldType = field.getType();
            String columnName = getColumnName(field);

            Object data = record.getValue(columnName);
            if (data == null || data.equals("")) {
                continue;
            }
            setFieldValue(instance, fieldName, data);
        }
        return instance;
    }

    private <T> void setFieldValue(T instance, String fieldName, Object data) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, instance.getClass());
            pd.getWriteMethod().invoke(instance, data);
        } catch (java.beans.IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage(), e);
            throw new AerospikeRepositoryException(e);
        }
    }

}
