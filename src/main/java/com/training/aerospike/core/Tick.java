package com.training.aerospike.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class Tick {
    private Date date;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Double volume;

    public Tick() {
        this.date = null;
    }

    public Tick(Date date) {
        this.date = date;
    }

}