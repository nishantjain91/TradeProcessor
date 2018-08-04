package com.company.TradeProcessor.Validator;

import com.company.TradeProcessor.FieldNames;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class TradeDateSettlementDateValidationStrategy implements  ValidationStrategy{
    @Override
    public boolean validate(Map input) {
        if(!input.containsKey(FieldNames.tradeDate.toString()) || !input.containsKey(FieldNames.tradeDate .toString() ) )return false;
        String td = (String) input.get(FieldNames.tradeDate.toString());
        String sd = (String)input.get(FieldNames.settlementDate.toString());
        if(td.length()==0 || sd.length()==0) return false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        try {
            LocalDate tdd  = LocalDate.parse(td,formatter);
            LocalDate sdd = LocalDate.parse(sd,formatter);

            return sdd.isEqual(tdd) || sdd.isAfter(tdd);
        } catch (DateTimeParseException e) {
            return false;
        }
    }


}
