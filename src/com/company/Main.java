package com.company;

import com.company.Database.AbstractStorage;
import com.company.Database.AbstractStorageFactory;
import com.company.Database.InternalStorageFactory;
import com.company.Reader.CSVReader;
import com.company.Server.TradeServer;
import com.company.TradeProcessor.Trade;
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

        AbstractStorageFactory<Trade> abstractStorageFactory = new InternalStorageFactory<>();
        AbstractStorage<Trade> abstractStorage = abstractStorageFactory.createStorage();
        TradeProcessor tradeProcessor= TradeProcessor.getInstance();
        tradeProcessor.exceuteTrades(reader, TradeValidationStrategy.getInstance(),abstractStorage);
        tradeProcessor.shutDown();
        System.out.println(abstractStorage.getTradeListByMaximumQuantity(10));
        System.out.println(abstractStorage.getTradeListbByBrokerCode("MS"));
        System.out.println(abstractStorage.getTradeListbByBrokerName("Morgan Stanley"));

        try {
            TradeServer server = new TradeServer(abstractStorage,8000);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
