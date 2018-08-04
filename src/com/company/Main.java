package com.company;

import com.company.Reader.CSVReader;
import com.company.TradeProcessor.TradeProcessor;

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
        TradeProcessor tradeProcessor= new TradeProcessor(reader);
        tradeProcessor.exceuteTrades();

    }
}
