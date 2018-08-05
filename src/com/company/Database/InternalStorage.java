package com.company.Database;

import com.company.TradeProcessor.Trade;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InternalStorage<T extends Trade> implements AbstractStorage {

    private final static Object lock = new Object();
    private HashMap<String,T> concurrentHashMapById;
    private ConcurrentHashMap<String,List<T>>  concurrentHashMapByBrokerName;
    private ConcurrentHashMap<String,List<T>>  concurrentHashMapByBrokerCode;
    private ConcurrentHashMap<String,Integer> concurrentHashMapForMaximumTrade;

     InternalStorage(){
        concurrentHashMapByBrokerCode = new ConcurrentHashMap<>();
        concurrentHashMapByBrokerName = new ConcurrentHashMap<>();
        concurrentHashMapById = new HashMap<>();
        concurrentHashMapForMaximumTrade = new ConcurrentHashMap<>();
    }

    @Override
    public boolean add(final Object object) {
        T obj = (T)object;
        //System.out.println(obj);


        synchronized (lock) {
            if (concurrentHashMapById.containsKey(obj.getTradeId())) return false;
            concurrentHashMapById.put(obj.getTradeId(), obj);

            if (!concurrentHashMapByBrokerName.containsKey(obj.getBrokerName())) {
                concurrentHashMapByBrokerName.put(obj.getBrokerName(), new ArrayList<>());
            }
            concurrentHashMapByBrokerName.get(obj.getBrokerName()).add(obj);

            if (!concurrentHashMapByBrokerCode.containsKey(obj.getBrokerCode())) {
                concurrentHashMapByBrokerCode.put(obj.getBrokerCode(), new ArrayList<>());
            }
            concurrentHashMapByBrokerCode.get(obj.getBrokerCode()).add(obj);

            if (!concurrentHashMapForMaximumTrade.containsKey(obj.getStockName())) {
                concurrentHashMapForMaximumTrade.put(obj.getStockName(), 0);
            }
        }
        concurrentHashMapForMaximumTrade.put(obj.getStockName(),concurrentHashMapForMaximumTrade.get(obj.getStockName())+obj.getQuantity());
        return true;
    }

    @Override
    public List<T> getTradeListbByBrokerName(String brokerName) {
        if(!concurrentHashMapByBrokerName.containsKey(brokerName)) return new ArrayList<>();
        return concurrentHashMapByBrokerName.get(brokerName);
    }

    @Override
    public List<T> getTradeListbByBrokerCode(String brokerCode) {
        if(!concurrentHashMapByBrokerCode.containsKey(brokerCode)) return new ArrayList<>();
        return concurrentHashMapByBrokerCode.get(brokerCode);
    }

    @Override
    public List<String> getTradeListByMaximumQuantity(int numOfResults) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for(String s:concurrentHashMapForMaximumTrade.keySet()){
            pq.add(new Pair(s,concurrentHashMapForMaximumTrade.get(s)));
        }

        ArrayList<String> s= new ArrayList<>();
        for(int i=0;i<numOfResults && i<pq.size();i++){
            s.add(Objects.requireNonNull(pq.poll()).getName());
        }
        return s;

    }
    private class Pair implements Comparable<Pair>{
        private String name;
        private int quantity;

        Pair(String name, int quanity){
            this.name=name;
            this.quantity=quanity;
        }

        String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "name='" + name + '\'' +
                    ", quantity=" + quantity +
                    '}';
        }

        int getQuantity() {
            return quantity;
        }

        @Override
        public int compareTo(Pair o) {
            return -this.quantity + o.getQuantity();
        }
    }

}
