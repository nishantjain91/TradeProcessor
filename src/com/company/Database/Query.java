package com.company.Database;


import com.company.TradeProcessor.Trade;

import java.util.List;

public interface Query<T extends Trade> {

    List<T> getTradeListbByBrokerName(String brokerName);
    List<T> getTradeListbByBrokerCode(String brokerCode);
    List<String> getTradeListByMaximumQuantity(int numOfResults);

}
