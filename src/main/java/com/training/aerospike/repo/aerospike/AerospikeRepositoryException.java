package com.training.aerospike.repo.aerospike;

public class AerospikeRepositoryException extends Exception {
    public AerospikeRepositoryException() {
        super();
    }

    public AerospikeRepositoryException(String message) {
        super(message);
    }

    public AerospikeRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public AerospikeRepositoryException(Throwable cause) {
        super(cause);
    }
}
