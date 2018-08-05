package com.company.TradeProcessor;

import com.company.TradeMapper;
import com.company.TradeProcessor.Validator.TradeValidationStrategy;
import com.company.TradeProcessor.Validator.ValidationSet;
import com.company.TradeProcessor.Validator.ValidationStrategy;
import java.util.Set;
import java.util.concurrent.Callable;

public class ProcessTrade implements Callable<ProcessTradeResult> {

    private TradeMapper input;
    private ValidationSet validationSet;

    public ProcessTrade( TradeMapper input,ValidationSet validationSet){
        this.input=input;
        this.validationSet=validationSet;
    }

    @Override
    public ProcessTradeResult call() {
        Set<ValidationStrategy> set = validationSet.getValidationStrategies();;
        for(ValidationStrategy validationStrategy:set){
            if(!validationStrategy.validate(input.getMap())){
                return new ProcessTradeResult(false,null,input);
            }
        }
        return new ProcessTradeResult(true,new Trade.TradeBuilder().buildFromMap(input.getMap()).build(),input);
    }
}
