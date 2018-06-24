package com.training.aerospike.repo;

import com.aerospike.client.IAerospikeClient;
import com.training.aerospike.repo.aerospike.AerospikeRepository;
import com.training.aerospike.repo.aerospike.AerospikeRepositoryImpl;
import com.training.aerospike.repo.user.UserRepository;
import com.training.aerospike.repo.user.UserRepositoryImpl;

public class RepositoryFactory {

    private RepositoryFactory() {

    }

    public static UserRepository createUserRepository(Connection<IAerospikeClient> connection) {
        return new UserRepositoryImpl(connection);
    }


    public static AerospikeRepository createAerospikeRepository(Connection<IAerospikeClient> connection) {
        return new AerospikeRepositoryImpl(connection);
    }
}
