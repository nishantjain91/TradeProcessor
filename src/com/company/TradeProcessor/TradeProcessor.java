package com.company.TradeProcessor;


import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TradeProcessor {

    Iterator reader;
    public TradeProcessor(){
    }

    public void exceuteTrades(){
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }
}
