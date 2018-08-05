package com.company.TradeProcessor;

import com.company.Database.AbstractStorage;
import com.company.TradeMapper;
import com.company.TradeProcessor.Validator.ValidationSet;
import com.company.TradeProcessor.Validator.ValidationStrategy;
import java.util.Set;
import java.util.concurrent.Callable;

public class ProcessTrade implements Callable<ProcessTradeResult> {

    private TradeMapper input;
    private ValidationSet validationSet;
    private AbstractStorage<Trade> abstractStorage;

    public ProcessTrade( TradeMapper input,ValidationSet validationSet, AbstractStorage<Trade> abstractStorage){
        this.input=input;
        this.validationSet=validationSet;
        this.abstractStorage=abstractStorage;
    }

    @Override
    public ProcessTradeResult call() {
        Set<ValidationStrategy> set = validationSet.getValidationStrategies();;
        for(ValidationStrategy validationStrategy:set){
            if(!validationStrategy.validate(input.getMap())){
                return new ProcessTradeResult(false,null,input);
            }
        }
        Trade obj = new Trade.TradeBuilder().buildFromMap(input.getMap()).build();
        boolean res = abstractStorage.add(obj);
       // System.out.println(obj.getTradeId());
        //System.out.println(res);
        if(res==false)return new ProcessTradeResult(false,null,input);
        return new ProcessTradeResult(true,obj,input);
    }
}
