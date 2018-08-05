package com.company.TradeProcessor;

public enum FieldNames {
    tradeId("tradeId"),
    stockName("stockName"),
    brokerName("brokerName"),
    brokerCode("brokerCode"),
    quantity("quantity"),
    tradeDate("tradeDate"),
    settlementDate("settlementDate"),
    buySellIndicator("buySellIndicator");

    private final String text;

    FieldNames(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
