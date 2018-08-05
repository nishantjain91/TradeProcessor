package com.company;

import com.company.Reader.CSVReader;
import com.company.TradeProcessor.TradeProcessor;
import com.company.TradeProcessor.Validator.TradeValidationStrategy;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Iterator reader = null;
        try {
            reader = new CSVReader("TradeData.dat",true,"\\|");
        } catch (IOException e) {
            e.printStackTrace();
        }
        TradeProcessor tradeProcessor= TradeProcessor.getInstance();
        tradeProcessor.exceuteTrades(reader, TradeValidationStrategy.getInstance());
        tradeProcessor.shutDown();

    }
}
