package com.training.aerospike.repo.stock;

import com.aerospike.client.Key;
import com.training.aerospike.entity.StockTimeSeries;

import java.util.Optional;

public interface StocksRepository {

    Optional<StockTimeSeries> save(StockTimeSeries entity);

    Optional<StockTimeSeries> getStocks();

    void updateTimeSeries(StockTimeSeries stoks);

    void getSummary(Long count, Key summaryKey, long overallRndNum, int numOfStocks);
}
