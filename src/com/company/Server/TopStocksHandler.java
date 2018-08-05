package com.company.Server;

import com.company.Database.AbstractStorage;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_OK;

public class TopStocksHandler implements HttpHandler {
    private final String LENFIELD = "len";
    private final AbstractStorage abstractStorage;
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        Map<String,String> querypParameters= Util.queryToMap(exchange.getRequestURI().getQuery());
        int numOfResults=5;
        if(querypParameters.containsKey(LENFIELD)){
            try{
                numOfResults = Integer.parseInt(querypParameters.get(LENFIELD));
            }catch (NumberFormatException ex){
                numOfResults=5;
            }
        }
        String s = abstractStorage.getTradeListByMaximumQuantity(numOfResults).toString();
        exchange.sendResponseHeaders(HTTP_OK,s.length());
        exchange.getResponseBody().write(s.getBytes());
        exchange.getResponseBody().flush();
        exchange.getResponseBody().close();
    }

    public TopStocksHandler(AbstractStorage abstractStorage){
        this.abstractStorage = abstractStorage;
    }

}
