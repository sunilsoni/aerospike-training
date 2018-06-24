package com.training.aerospike.repo.stock;

import com.aerospike.client.IAerospikeClient;
import com.aerospike.client.Key;
import com.training.aerospike.entity.StockTimeSeries;
import com.training.aerospike.repo.Connection;
import com.training.aerospike.repo.RepositoryFactory;
import com.training.aerospike.repo.aerospike.AerospikeRepository;

import java.util.Optional;

public class StocksRepositoryImpl implements StocksRepository {

    AerospikeRepository<StockTimeSeries> aerospikeRepository;

    public StocksRepositoryImpl(Connection<IAerospikeClient> connection) {
        this.aerospikeRepository = RepositoryFactory.createAerospikeRepository(connection);
    }

    @Override
    public Optional<StockTimeSeries> save(StockTimeSeries entity) {
        aerospikeRepository.save(entity, entity.getId());
        return Optional.empty();
    }

    @Override
    public Optional<StockTimeSeries> getStocks() {

        return Optional.empty();
    }

    @Override
    public void updateTimeSeries(StockTimeSeries stoks) {

    }

    @Override
    public void getSummary(Long count, Key summaryKey, long overallRndNum, int numOfStocks) {

    }
}
