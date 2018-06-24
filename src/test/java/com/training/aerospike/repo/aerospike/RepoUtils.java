package com.training.aerospike.repo.aerospike;

import com.aerospike.client.IAerospikeClient;
import com.github.srini156.aerospike.client.MockAerospikeClient;
import com.training.aerospike.repo.Connection;
import com.training.aerospike.repo.ConnectionInfo;


public class RepoUtils {
    //127.0.0.1:3000
    //13.127.124.144:3000
    private static final String hostName = System.getProperty("hostName", "13.127.124.144:3000");
    private static final String userName = System.getProperty("userName", "aerotraining");
    private static final String password = System.getProperty("password", "aerotraining");
    private static final String namespace = System.getProperty("namespace", "test");
    private static boolean isMock = Boolean.parseBoolean(System.getProperty("use.mock", "false"));

    private RepoUtils() {
    }

    public static Connection<IAerospikeClient> getAerospikeConnection() {
        ConnectionInfo info = ConnectionInfo.builder()
                .hostWithPort(hostName)
                .namespace(namespace)
                .username(userName)
                .password(password).build();

        if (isMock) {
            return new AerospikeConnectionImpl(info, new MockAerospikeClient());
        } else {
            return new AerospikeConnectionImpl(info);
        }
    }

    public static boolean isIsMock() {
        return isMock;
    }

    public static String getHostName() {
        return hostName;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getPassword() {
        return password;
    }

    public static String getNamespace() {
        return namespace;
    }
}
