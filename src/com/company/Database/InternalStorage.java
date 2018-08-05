package com.company.Database;

import com.company.TradeProcessor.Trade;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

public class InternalStorage<T extends Trade> implements AbstractStorage {

    private final Object lock = new Object();
    private ConcurrentHashMap<String,T> concurrentHashMapById;
    private ConcurrentHashMap<String,List<T>>  concurrentHashMapByBrokerName;
    private ConcurrentHashMap<String,List<T>>  concurrentHashMapByBrokerCode;
    private ConcurrentHashMap<String,Integer> concurrentHashMapForMaximumTrade;

    private InternalStorage internalStorage = null;
    private InternalStorage(){
        concurrentHashMapByBrokerCode = new ConcurrentHashMap<>();
        concurrentHashMapByBrokerName = new ConcurrentHashMap<>();
        concurrentHashMapById = new ConcurrentHashMap<>();
        concurrentHashMapForMaximumTrade = new ConcurrentHashMap<>();
    }

    @Override
    public AbstractStorage getInstance() {
        if(internalStorage==null){
            synchronized (lock){
                if(internalStorage==null){
                    internalStorage = new InternalStorage();
                }
            }
        }
        return internalStorage;
    }


    @Override
    public boolean add(final Object object) {
        T obj = (T)object;
        if(concurrentHashMapById.contains(obj.getTradeId()))return false;
        concurrentHashMapById.put(obj.getTradeId(),obj);
        if(!concurrentHashMapByBrokerName.contains(obj.getBrokerName())){
            concurrentHashMapByBrokerName.put(obj.getBrokerName(),new ArrayList<>());
        }
        concurrentHashMapByBrokerCode.get(obj.getBrokerCode()).add(obj);
        if(!concurrentHashMapByBrokerCode.contains(obj.getBrokerName())){
            concurrentHashMapByBrokerCode.put(obj.getBrokerCode(),new ArrayList<>());
        }
        concurrentHashMapByBrokerCode.get(obj.getBrokerCode()).add(obj);
        if(!concurrentHashMapForMaximumTrade.contains(obj.getStockName())){
            concurrentHashMapForMaximumTrade.put(obj.getStockName(),0);
        }
        concurrentHashMapForMaximumTrade.put(obj.getStockName(),concurrentHashMapForMaximumTrade.get(obj.getStockName()+obj.getQuantity()));
        return true;
    }

    @Override
    public List<T> getTradeListbByBrokerName(String brokerName) {
        if(!concurrentHashMapByBrokerName.contains(brokerName)) return new ArrayList<T>();
        return concurrentHashMapByBrokerName.get(brokerName);
    }

    @Override
    public List<T> getTradeListbByBrokerCode(String brokerCode) {
        if(!concurrentHashMapByBrokerName.contains(brokerCode)) return new ArrayList<T>();
        return concurrentHashMapByBrokerName.get(brokerCode);
    }

    @Override
    public List<String> getTradeListByMaximumQuantity(int numOfResults) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for(String s:concurrentHashMapForMaximumTrade.keySet()){
            if(pq.size()<numOfResults){
                pq.add(new Pair(s,concurrentHashMapForMaximumTrade.get(s)));
            }
            else{
                if(pq.peek().getQuantity()<concurrentHashMapForMaximumTrade.get(s)){
                    pq.poll();
                    pq.add(new Pair(s,concurrentHashMapForMaximumTrade.get(s)));
                }
            }
        }
        ArrayList<String> s= new ArrayList<>();
        for(Pair p:pq){
            s.add(p.getName());
        }
        return s;

    }
    private class Pair implements Comparable<Pair>{
        private String name;
        private int quantity;

        public Pair(String name, int quanity){
            this.name=name;
            this.quantity=quanity;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        @Override
        public int compareTo(Pair o) {
            return this.quantity + o.getQuantity();
        }
    }
}
