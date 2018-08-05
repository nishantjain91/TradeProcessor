package com.company.Database;

import com.company.TradeProcessor.Trade;

public class InternalStorageFactory<T extends Trade> implements AbstractStorageFactory<Trade> {

    private static InternalStorage<Trade> internalStorage;
    private static final Object lock = new Object();
    public InternalStorageFactory(){

    }

    @Override
    public AbstractStorage<Trade> createStorage() {
        if(internalStorage==null){
            synchronized (lock){
                if(internalStorage==null){
                    internalStorage = new InternalStorage<>();
                }
            }
        }
        return internalStorage;
    }
}
