package com.company.Database;

import com.company.TradeProcessor.Trade;

public interface AbstractStorageFactory<T extends Trade> {
    public AbstractStorage<T> createStorage();
}
