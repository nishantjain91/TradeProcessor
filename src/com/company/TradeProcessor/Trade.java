package com.company.TradeProcessor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Trade {
    private final String tradeId;
    private final String stockName;
    private final int quantity;
    private final LocalDate tradeDate;
    private final LocalDate settlementDate;
    private final char buySellIndicator;
    private final String brokerCode;
    private final String brokerName;

    @Override
    public String toString() {
        return "Trade{" +
                "tradeId='" + tradeId + '\'' +
                ", stockName='" + stockName + '\'' +
                ", quantity=" + quantity +
                ", tradeDate=" + tradeDate +
                ", settlementDate=" + settlementDate +
                ", buySellIndicator=" + buySellIndicator +
                ", brokerCode='" + brokerCode + '\'' +
                ", brokerName='" + brokerName + '\'' +
                '}';
    }

    public String getTradeId() {
        return tradeId;
    }

    public String getStockName() {
        return stockName;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getTradeDate() {
        return tradeDate;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public char getBuySellIndicator() {
        return buySellIndicator;
    }

    public String getBrokerCode() {
        return brokerCode;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public Trade(String tradeId, String stockName, int quantity, LocalDate tradeDate, LocalDate settlementDate, char buySellIndicator, String brokerCode, String brokerName) {
        this.tradeId = tradeId;
        this.stockName = stockName;
        this.quantity = quantity;
        this.tradeDate = tradeDate;
        this.settlementDate = settlementDate;
        this.buySellIndicator = buySellIndicator;
        this.brokerCode = brokerCode;
        this.brokerName = brokerName;
    }

    public static class TradeBuilder{

        private String tradeId;
        private String stockName;
        private int quantity;
        private LocalDate tradeDate;
        private LocalDate settlementDate;
        private char buySellIndicator;
        private String brokerCode;
        private String brokerName;

        public TradeBuilder(){
            quantity = Integer.MIN_VALUE;
            buySellIndicator = 0;
        }

        public Trade build(){
            return new Trade(tradeId,stockName,quantity,tradeDate,settlementDate,buySellIndicator,brokerCode,brokerName);
        }
        public TradeBuilder buildFromMap(Map<String,String> map){
              for(String key: map.keySet()){
                  this.add(key,map.get(key));
              }
              return this;
        }

        private TradeBuilder add(String key,String value){
            switch (FieldNames.valueOf(key)){
                case brokerCode:
                        this.brokerCode=value;
                        break;
                case brokerName:
                    this.brokerName=value;
                    break;
                case tradeId:
                    this.tradeId=value;
                    break;
                case stockName:
                    this.stockName=value;
                    break;
                case quantity:
                    this.quantity = Integer.parseInt(value);
                    break;
                case tradeDate:
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    this.tradeDate = LocalDate.parse(value,formatter);
                    break;
                case settlementDate:
                    formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    this.settlementDate = LocalDate.parse(value,formatter);
                    break;
                case buySellIndicator:
                    this.buySellIndicator= value.charAt(0);
                    break;
            }
            return this;
        }
    }
}
