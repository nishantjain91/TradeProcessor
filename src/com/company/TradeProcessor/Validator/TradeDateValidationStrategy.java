package com.company.TradeProcessor.Validator;

import com.company.TradeProcessor.FieldNames;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class TradeDateValidationStrategy implements  ValidationStrategy{
    @Override
    public boolean validate(Map input) {
        if(!input.containsKey(FieldNames.tradeDate) )return false;
        String value = (String) input.get(FieldNames.tradeDate);
        if(value.length()==0) return false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        try {
            LocalDate.parse(value,formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }


}
