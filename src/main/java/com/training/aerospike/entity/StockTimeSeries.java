package com.training.aerospike.entity;

import com.training.aerospike.core.BinName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Setter
@Getter
public class StockTimeSeries {

    private String id;

    @BinName(value = "timestamp")
    private Instant timestamp;

    @BinName(value = "open")
    private BigDecimal open;

    @BinName(value = "high")
    private BigDecimal high;

    @BinName(value = "low")
    private BigDecimal low;

    @BinName(value = "close")
    private BigDecimal close;

    @BinName(value = "adjClose")
    private BigDecimal adjustedClose;

    @BinName(value = "volume")
    private BigDecimal volume;

    @BinName(value = "dividendAmt")
    private BigDecimal dividendAmount;

    @BinName(value = "splitCoefnt")
    private BigDecimal splitCoefficient;


}
