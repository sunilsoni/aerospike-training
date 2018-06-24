package com.training.aerospike.repo.user;

import com.aerospike.client.IAerospikeClient;
import com.training.aerospike.entity.UserDetails;
import com.training.aerospike.fixtures.FixtureBuilder;
import com.training.aerospike.repo.Connection;
import com.training.aerospike.repo.ConnectionInfo;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.Assert;

import java.io.IOException;
import java.util.Optional;

import static com.training.aerospike.repo.aerospike.RepoUtils.getAerospikeConnection;
import static com.training.aerospike.repo.aerospike.RepoUtils.isIsMock;

public class UserRepositoryImplTest {

    private static Connection<IAerospikeClient> connection;
    private static UserRepositoryImpl repository;

    @BeforeClass
    public static void setup() {
        connection = getAerospikeConnection();
        repository = new UserRepositoryImpl(connection);
    }

    @AfterClass
    public static void tearDown() {
        try {
            if (!isIsMock()) {
                IAerospikeClient client = connection.openSession();
                ConnectionInfo info = connection.getConnectionInfo();
                //client.truncate(null,info.getNamespace(),repository.getSetName(UserDetails.class),null);
            }
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateUserDetails() {

    }

    @Test
    public void save() {
        UserDetails expectedUserDetails = FixtureBuilder.buildUserDetails();
        Optional<UserDetails> actual = repository.save(expectedUserDetails);
        Assert.assertTrue(actual.isPresent());
        Assert.assertEquals(expectedUserDetails, actual.get());
    }

    @Test
    public void findByUsername() {
    }

    @Test
    public void findById() {
    }
}