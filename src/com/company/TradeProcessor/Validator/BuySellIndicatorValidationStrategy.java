package com.company.TradeProcessor.Validator;

import com.company.TradeProcessor.FieldNames;

import java.util.Map;

public class BuySellIndicatorValidationStrategy implements  ValidationStrategy{
    @Override
    public boolean validate(Map input) {
        if(!input.containsKey(FieldNames.brokerCode) )return false;
        String value = (String) input.get(FieldNames.brokerCode);
        if(value.length()==1 &&(value.charAt(0)=='B' || value.charAt(0)=='S'))return true;
        return false;
    }


}
