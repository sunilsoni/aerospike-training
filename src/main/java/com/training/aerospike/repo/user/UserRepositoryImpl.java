package com.training.aerospike.repo.user;

import com.aerospike.client.IAerospikeClient;
import com.training.aerospike.entity.UserDetails;
import com.training.aerospike.repo.Connection;
import com.training.aerospike.repo.RepositoryFactory;
import com.training.aerospike.repo.aerospike.AerospikeRepository;
import com.training.aerospike.repo.aerospike.AerospikeRepositorySystemException;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class UserRepositoryImpl implements UserRepository {

    AerospikeRepository<UserDetails> aerospikeRepository;

    public UserRepositoryImpl(Connection<IAerospikeClient> connection) {
        this.aerospikeRepository = RepositoryFactory.createAerospikeRepository(connection);
    }

    @Override
    public void updateUserDetails(UserDetails user) {
        log.info(String.format("UserDetails with id %s updated!", user.getId()));

    }

    @Override
    public void delete(String id) {
        //  aerospikeRepository.d
    }

    @Override
    public Optional<UserDetails> save(UserDetails entity) {
        aerospikeRepository.save(entity, entity.getId());
        log.info(String.format("UserDetails with id %s saved!", entity.getId()));
        return Optional.of(entity);
    }

    @Override
    public Optional<UserDetails> findByUsername(String username) {
        log.info(String.format("UserDetails.findByUsername for id %s ", username));
        return Optional.empty();
    }

    @Override
    public Optional<UserDetails> findById(String id) {
        Optional<UserDetails> result;
        try {
            Object obj = aerospikeRepository.fetch(id, UserDetails.class);
            result = Optional.of((UserDetails) obj);
        } catch (AerospikeRepositorySystemException e) {
            return Optional.empty();
        }
        log.info(String.format("UserDetails.findById  with id %s ", id));
        return result;
    }
}
