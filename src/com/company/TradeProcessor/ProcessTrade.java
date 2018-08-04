package com.company.TradeProcessor;

import com.company.TradeProcessor.Validator.ValidationStrategy;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

public class ProcessTrade implements Callable<ProcessTradeResult> {

    private Set<ValidationStrategy> set;
    private Map<String,String> input;

    public ProcessTrade(Set<ValidationStrategy> set, Map<String,String> input){
        this.set = set;
        this.input=input;
    }

    @Override
    public ProcessTradeResult call() throws Exception {


        for(ValidationStrategy validationStrategy:set){
            if(!validationStrategy.validate(input)){
                System.out.println(validationStrategy.getClass().getName());

                return new ProcessTradeResult(false,null);
            }
        }
        return new ProcessTradeResult(true,new Trade.TradeBuilder().buildFromMap(input).build());

    }
}
