package com.training.aerospike.repo;

import java.io.Closeable;

public abstract class Connection<T> implements Closeable {

    private ConnectionInfo info;

    public Connection(ConnectionInfo info) {
        this.info = info;
    }

    public ConnectionInfo getConnectionInfo() {
        return this.info;
    }

    public abstract T openSession();
}
