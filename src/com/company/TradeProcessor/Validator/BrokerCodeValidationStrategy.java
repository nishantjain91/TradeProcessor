package com.company.TradeProcessor.Validator;

import com.company.TradeProcessor.FieldNames;

import java.util.Map;

public class BrokerCodeValidationStrategy implements  ValidationStrategy{
    @Override
    public boolean validate(Map input) {
        if(!input.containsKey(FieldNames.brokerCode) )return false;
        String value = (String) input.get(FieldNames.brokerCode);
        if(value.length()==0) return false;
        return value.matches("[A-Za-z0-9]+");
    }


}
