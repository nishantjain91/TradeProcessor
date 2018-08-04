package com.company.TradeProcessor.Validator;

import com.company.TradeProcessor.FieldNames;

import java.util.Map;

public class QuantityValidationStrategy implements  ValidationStrategy{
    @Override
    public boolean validate(Map input) {
        if(!input.containsKey(FieldNames.quantity) )return false;
        String value = (String) input.get(FieldNames.quantity);
        if(value.length()==0) return false;
        try{
            int val = Integer.parseInt(value);
            if(val>=0)return true;
            return false;
        }catch (NumberFormatException ex){
           return false;
        }
    }


}
