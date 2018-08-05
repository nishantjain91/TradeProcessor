package com.company.TradeProcessor;

import com.company.TradeMapper;

public class ProcessTradeResult {
    final boolean status;
    final Trade trade;
    final TradeMapper input;
    final String reason;

    public boolean isStatus() {
        return status;
    }

    public Trade getTrade() {
        return trade;
    }

    public TradeMapper getInput() {
        return input;
    }

    public String getReason() {
        return reason;
    }

    public ProcessTradeResult(boolean status, Trade trade, TradeMapper input, String reason) {
        this.status = status;
        this.trade = trade;
        this.input=input;
        this.reason = reason;
    }

}
