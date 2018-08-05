package com.company;

import java.util.Map;

public class TradeMapper {
    private Map<String,String> map;

    public Map<String, String> getMap() {
        return map;
    }

    public TradeMapper(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
