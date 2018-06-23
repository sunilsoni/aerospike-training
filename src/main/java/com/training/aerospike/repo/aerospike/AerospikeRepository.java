package com.training.aerospike.repo.aerospike;

import java.time.LocalDate;

public interface AerospikeRepository<T> {

    T fetchOrCreate(String id) throws AerospikeRepositoryException;

    T fetch(String id) throws AerospikeRepositoryException;

    void save(T entity);
}
