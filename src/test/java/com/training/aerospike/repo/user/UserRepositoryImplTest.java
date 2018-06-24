package com.training.aerospike.repo.user;

import com.aerospike.client.IAerospikeClient;
import com.training.aerospike.core.Utils;
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
                client.truncate(null, info.getNamespace(), Utils.getSetName(UserDetails.class), null);
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
        UserDetails expectedUserDetails = FixtureBuilder.buildUserDetails();
        Optional<UserDetails> userDetails = repository.save(expectedUserDetails);
        Assert.assertTrue(userDetails.isPresent());

        Optional<UserDetails> actual = repository.findById(expectedUserDetails.getId());
        Assert.assertTrue(actual.isPresent());
        Assert.assertEquals(expectedUserDetails.getId(), actual.get().getId());

        Assert.assertEquals(expectedUserDetails.getDob(), actual.get().getDob());
        Assert.assertEquals(expectedUserDetails.getFirstName(), actual.get().getFirstName());
        Assert.assertEquals(expectedUserDetails.getLastName(), actual.get().getLastName());
        Assert.assertEquals(expectedUserDetails.getGender(), actual.get().getGender());
        Assert.assertEquals(expectedUserDetails.getPassword(), actual.get().getPassword());
        Assert.assertEquals(expectedUserDetails.getRegion(), actual.get().getRegion());
        Assert.assertEquals(expectedUserDetails.getUsername(), actual.get().getUsername());
        Assert.assertEquals(expectedUserDetails.getInterests(), actual.get().getInterests());
    }
}