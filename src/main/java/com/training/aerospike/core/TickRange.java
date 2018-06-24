package com.training.aerospike.core;

public class TickRange {

    private Tick startTick;
    private Tick endTick;

    public TickRange(Tick startTick, Tick endTick) {
        this.startTick = startTick;
        this.endTick = endTick;
    }

    public Tick getStartTick() {
        return startTick;
    }

    public Tick getEndTick() {
        return endTick;
    }
}