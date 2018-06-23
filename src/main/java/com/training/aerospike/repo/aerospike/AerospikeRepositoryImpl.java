package com.training.aerospike.repo.aerospike;

import com.aerospike.client.IAerospikeClient;
import com.training.aerospike.repo.Connection;

import java.time.LocalDate;

public abstract class AerospikeRepositoryImpl implements AerospikeRepository {

    private Connection<IAerospikeClient> connection;

    public AerospikeRepositoryImpl(Connection<IAerospikeClient> connection) {
        this.connection = connection;
    }
}
