package com.company.TradeProcessor;


import com.company.TradeProcessor.Validator.*;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TradeProcessor {

    Iterator reader;
    public TradeProcessor(Iterator reader){
        this.reader = reader;
    }

    public void exceuteTrades() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Set<ValidationStrategy> validationStrategySet = makeValidationStrategySet();
        List<ProcessTrade> tasks = new ArrayList<>();
        while(reader.hasNext()){
            Map<String,String>m = (Map<String, String>) reader.next();
            tasks.add(new ProcessTrade(validationStrategySet,m));
        }
        List<Future<ProcessTradeResult>> futures= executorService.invokeAll(tasks);
        for(Future<ProcessTradeResult> f: futures){
            ProcessTradeResult processTradeResult= f.get();
            if(processTradeResult.isStatus()==true)
                System.out.println(processTradeResult.getTrade());
        }
        executorService.shutdown();
    }

    private Set<ValidationStrategy> makeValidationStrategySet(){
        Set<ValidationStrategy> validationStrategySet = new HashSet<>();
        validationStrategySet.add(new BrokerCodeValidationStrategy());
        validationStrategySet.add(new BrokerNameValidationStrategy());
        validationStrategySet.add(new BuySellIndicatorValidationStrategy());
        validationStrategySet.add(new QuantityValidationStrategy());
        validationStrategySet.add(new StockNameValidationStrategy());
        validationStrategySet.add(new TradeDateSettlementDateValidationStrategy());
        validationStrategySet.add(new TradeIdValidationStrategy());

        return validationStrategySet;
    }
}
