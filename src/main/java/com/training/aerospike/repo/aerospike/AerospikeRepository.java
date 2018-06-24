package com.training.aerospike.repo.aerospike;

import java.util.Set;

public interface AerospikeRepository<T> {

    T updateOrCreate(T entity, String id) throws AerospikeRepositorySystemException;

    T fetch(Class<T> clazz, String id) throws AerospikeRepositorySystemException;

    //T fetchAll(Collection<String> ids, Class<T> clazz) throws AerospikeRepositorySystemException;

    void save(T entity, String id);

    void deleteBins(T entity, String id);

    void deleteByIds(String setName, Set<String> ids);

    boolean exists(Class<T> clazz, String id);
}
