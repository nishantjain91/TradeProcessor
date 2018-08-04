package com.company.TradeProcessor;

import java.time.LocalDate;

public class Trade {
    private final String tradeId;
    private final String stockName;
    private final int quantity;
    private final LocalDate tradeDate;
    private final LocalDate settlementDate;
    private final char buySellIndicator;


    @Override
    public String toString() {
        return "Trade{" +
                "tradeId='" + tradeId + '\'' +
                ", stockName='" + stockName + '\'' +
                ", quantity=" + quantity +
                ", tradeDate=" + tradeDate +
                ", settlementDate=" + settlementDate +
                ", buySellIndicator=" + buySellIndicator +
                '}';
    }

    public String getTradeId() {
        return tradeId;
    }

    public String getStockName() {
        return stockName;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getTradeDate() {
        return tradeDate;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public char getBuySellIndicator() {
        return buySellIndicator;
    }

    public Trade(String tradeId, String stockName, int quantity, LocalDate tradeDate, LocalDate settlementDate, char buySellIndicator) {
        this.tradeId = tradeId;
        this.stockName = stockName;
        this.quantity = quantity;
        this.tradeDate = tradeDate;
        this.settlementDate = settlementDate;
        this.buySellIndicator = buySellIndicator;
    }
}
