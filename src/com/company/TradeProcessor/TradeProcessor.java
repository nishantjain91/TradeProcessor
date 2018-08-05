package com.company.TradeProcessor;

import com.company.Database.AbstractStorage;
 import com.company.TradeMapper;
import com.company.TradeProcessor.Validator.ValidationSet;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TradeProcessor {

    private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static TradeProcessor ourInstance ;
    private final static Object  lock = new Object();

    public static TradeProcessor getInstance() {
        if(ourInstance==null){
            synchronized (lock){
                if(ourInstance==null){
                    ourInstance=new TradeProcessor();
                }
            }
        }
        return ourInstance;
    }

    private TradeProcessor() {
    }

    public void exceuteTrades(final Iterator reader, final  ValidationSet validationSet, final AbstractStorage<Trade> abstractStorage) throws InterruptedException, ExecutionException {
        List<Future<ProcessTradeResult>> futures = new ArrayList<>();
        while(reader.hasNext()){
            TradeMapper  m = (TradeMapper) reader.next();
            futures.add(executorService.submit(new ProcessTrade(m,validationSet,abstractStorage)));
        }
        ProcessTradeResult processTradeResult;
        for(Future<ProcessTradeResult> f: futures){
            processTradeResult = f.get();
            if(!processTradeResult.isStatus()){
                System.out.println(processTradeResult.getReason()+" " + processTradeResult.getInput().toString());
            }
        }
    }

    public void shutDown(){
        executorService.shutdown();
    }

}
