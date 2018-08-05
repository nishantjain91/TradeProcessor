package com.company.TradeProcessor;

import com.company.TradeMapper;

public class ProcessTradeResult {
    boolean status;
    Trade trade;
    TradeMapper input;

    public boolean isStatus() {
        return status;
    }

    public Trade getTrade() {
        return trade;
    }

    public TradeMapper getInput() {
        return input;
    }

    public ProcessTradeResult(boolean status, Trade trade, TradeMapper input) {
        this.status = status;
        this.trade = trade;
        this.input=input;
    }
}
