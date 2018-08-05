package com.company.TradeProcessor.Validator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TradeValidationStrategy implements ValidationSet{
    private static Set<ValidationStrategy> validationStrategySet = new HashSet<>();
    static {
        validationStrategySet.add(new BrokerCodeValidationStrategy());
        validationStrategySet.add(new BrokerNameValidationStrategy());
        validationStrategySet.add(new BuySellIndicatorValidationStrategy());
        validationStrategySet.add(new QuantityValidationStrategy());
        validationStrategySet.add(new StockNameValidationStrategy());
        validationStrategySet.add(new TradeDateSettlementDateValidationStrategy());
        validationStrategySet.add(new TradeIdValidationStrategy());
        validationStrategySet = Collections.unmodifiableSet(validationStrategySet);
    }

    @Override
    public Set<ValidationStrategy> getValidationStrategies(){
        return validationStrategySet;
    }
    private static TradeValidationStrategy ourInstance = new TradeValidationStrategy();

    public static TradeValidationStrategy getInstance() {
        return ourInstance;
    }

    private TradeValidationStrategy() {
    }
}
