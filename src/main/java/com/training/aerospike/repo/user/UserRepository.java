package com.training.aerospike.repo.user;

import com.training.aerospike.entity.Users;
import com.training.aerospike.repo.aerospike.AerospikeRepository;
import com.training.aerospike.repo.aerospike.AerospikeRepositoryException;

public interface UserRepository extends AerospikeRepository<Users> {

   // UserRepository init() throws AerospikeRepositoryException;

    void updateFirstName(String id,String firstName);

    void updateUser(Users user);

    void updateLastName(String id,String lastName);

    void updateDob(String id,String dob);

}
