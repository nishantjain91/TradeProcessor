package com.company.Server;

import com.company.Database.AbstractStorage;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TradeServer {
    private HttpServer httpServer = HttpServer.create();

    public void setAbstractStorage(AbstractStorage abstractStorage) {
        this.abstractStorage = abstractStorage;
    }

    {
        httpServer.setExecutor(Executors.newCachedThreadPool());
    }

    private static Logger log  = Logger.getLogger(TradeServer.class.getName());
    private AbstractStorage abstractStorage;

    public TradeServer() throws IOException {
        httpServer.bind(new InetSocketAddress(8000),0);

    }
    public  TradeServer(AbstractStorage abstractStorage, int portNumber) throws IOException {
        httpServer.bind(new InetSocketAddress(portNumber),0);
        this.abstractStorage = abstractStorage;
    }

    public boolean start(){
        if(abstractStorage==null){
            log.log(Level.INFO,"No storage is specified");
            return false;
        }
        httpServer.createContext("/topStocks", new TopStocksHandler(abstractStorage));
        httpServer.createContext("/getStocksByBroker", new StockByBrokerHandler(abstractStorage));
        httpServer.start();
        log.log(Level.INFO,"Server started at " + httpServer.getAddress().toString());
        return true;
    }

    public  void shutDown(){
        httpServer.stop(0);
    }
}
