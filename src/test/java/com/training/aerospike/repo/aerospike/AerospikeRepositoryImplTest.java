package com.training.aerospike.repo.aerospike;

import com.aerospike.client.IAerospikeClient;
import com.training.aerospike.entity.UserDetails;
import com.training.aerospike.fixtures.FixtureBuilder;
import com.training.aerospike.repo.Connection;
import com.training.aerospike.repo.ConnectionInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static com.training.aerospike.repo.aerospike.RepoUtils.getAerospikeConnection;
import static com.training.aerospike.repo.aerospike.RepoUtils.isIsMock;

@Slf4j
public class AerospikeRepositoryImplTest {

    private static Connection<IAerospikeClient> connection;
    private static AerospikeRepositoryImpl repository;

    @BeforeClass
    public static void setup() {
        connection = getAerospikeConnection();
        repository = new AerospikeRepositoryImpl(connection);
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
    public void saveTest() {
        UserDetails expectedUserDetails = FixtureBuilder.buildUserDetails();
        repository.save(expectedUserDetails, expectedUserDetails.getId());
    }

    @Test
    public void fetchOrCreate() {
    }

    @Test
    public void fetch() {


    }
}