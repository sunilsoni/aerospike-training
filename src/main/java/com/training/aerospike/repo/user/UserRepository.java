package com.training.aerospike.repo.user;

import com.training.aerospike.entity.UserDetails;

import java.util.Optional;

public interface UserRepository {

    void updateUserDetails(UserDetails user);

    Optional<UserDetails> save(UserDetails entity);

    Optional<UserDetails> findByUsername(String username);

    Optional<UserDetails> findById(String id);

}
