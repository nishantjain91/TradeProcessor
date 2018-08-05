package com.company.Server;

import com.company.Database.AbstractStorage;
import com.company.TradeProcessor.FieldNames;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_OK;

public class StockByBrokerHandler implements HttpHandler {
    private final AbstractStorage abstractStorage;
    private final String BROKERNAME = "brokerName";
    private final String BROKERCODE = "brokerCode";

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Map<String,String> querypParameters= Util.queryToMap(exchange.getRequestURI().getQuery());
        String s="";
        if(querypParameters.containsKey(BROKERNAME)){
            s = abstractStorage.getTradeListbByBrokerName(querypParameters.get(BROKERNAME)).toString();
        }
        else if (querypParameters.containsKey(BROKERCODE)){
            s = abstractStorage.getTradeListbByBrokerCode(querypParameters.get(BROKERCODE)).toString();
        }
        System.out.println(s);
        exchange.sendResponseHeaders(HTTP_OK,s.length());
        exchange.getResponseBody().write(s.getBytes());
        exchange.getResponseBody().flush();
        exchange.getResponseBody().close();
    }

    public StockByBrokerHandler(AbstractStorage abstractStorage){
        this.abstractStorage = abstractStorage;
    }

}
