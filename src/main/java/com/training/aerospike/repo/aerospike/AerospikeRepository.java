package com.training.aerospike.repo.aerospike;

public interface AerospikeRepository<T> {

    T fetchOrCreate(String id) throws AerospikeRepositorySystemException;

    T fetch(String id, Class<T> clazz) throws AerospikeRepositorySystemException;

    //T fetchAll(Collection<String> ids, Class<T> clazz) throws AerospikeRepositorySystemException;

    void save(T entity, String id);
}
