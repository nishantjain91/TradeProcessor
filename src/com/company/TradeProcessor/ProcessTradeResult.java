package com.company.TradeProcessor;

public class ProcessTradeResult {
    boolean status;
    Trade trade;

    public boolean isStatus() {
        return status;
    }

    public Trade getTrade() {
        return trade;
    }

    public ProcessTradeResult(boolean status, Trade trade) {
        this.status = status;
        this.trade = trade;
    }
}
