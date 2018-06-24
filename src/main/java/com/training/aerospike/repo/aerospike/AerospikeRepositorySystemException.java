package com.training.aerospike.repo.aerospike;

public class AerospikeRepositorySystemException extends Exception {

    public AerospikeRepositorySystemException() {
        super();
    }

    public AerospikeRepositorySystemException(String message) {
        super(message);
    }

    public AerospikeRepositorySystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public AerospikeRepositorySystemException(Throwable cause) {
        super(cause);
    }
}

