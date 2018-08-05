package com.company.TradeProcessor;

import com.company.TradeMapper;

public class ProcessTradeResult {
    private final boolean status;
    private final Trade trade;
    private final TradeMapper input;
    private final String reason;

    boolean isStatus() {
        return status;
    }

    public Trade getTrade() {
        return trade;
    }

    public TradeMapper getInput() {
        return input;
    }

    String getReason() {
        return reason;
    }

    public ProcessTradeResult(boolean status, Trade trade, TradeMapper input, String reason) {
        this.status = status;
        this.trade = trade;
        this.input=input;
        this.reason = reason;
    }

}
