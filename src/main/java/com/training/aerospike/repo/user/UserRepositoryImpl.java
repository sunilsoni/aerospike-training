package com.training.aerospike.repo.user;

import com.aerospike.client.IAerospikeClient;
import com.training.aerospike.entity.Users;
import com.training.aerospike.repo.Connection;
import com.training.aerospike.repo.RepositoryFactory;
import com.training.aerospike.repo.aerospike.AerospikeRepository;
import com.training.aerospike.repo.aerospike.AerospikeRepositoryException;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;

@Slf4j
public class UserRepositoryImpl implements UserRepository {

    private Connection<IAerospikeClient> connection;
    private UserRepository userRepository;

    public UserRepositoryImpl(Connection<IAerospikeClient> connection){
        this.userRepository=RepositoryFactory.createUserRepository(connection);
        log.info("userRepository: ",userRepository);
    }

    @Override
    public void updateFirstName(String id, String firstName) {

       // userRepository.updateFirstName();
    }

    @Override
    public void updateUser(Users user) {

    }

    @Override
    public void updateLastName(String id, String lastName) {

    }

    @Override
    public void updateDob(String id, String dob) {

    }

    @Override
    public Users fetchOrCreate(String id) throws AerospikeRepositoryException {
        return null;
    }

    @Override
    public Users fetch(String id) throws AerospikeRepositoryException {
        return null;
    }

    @Override
    public void save(Users entity) {
        userRepository.save(entity);
    }
}
