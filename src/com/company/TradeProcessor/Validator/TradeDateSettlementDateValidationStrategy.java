package com.company.TradeProcessor.Validator;

import com.company.TradeProcessor.FieldNames;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class TradeDateSettlementDateValidationStrategy implements  ValidationStrategy{
    @Override
    public boolean validate(Map input) {
        if(!input.containsKey(FieldNames.tradeDate) || !input.containsKey(FieldNames.tradeDate  ) )return false;
        String td = (String) input.get(FieldNames.tradeDate);
        String sd = (String)input.get(FieldNames.settlementDate);
        if(td.length()==0 || sd.length()==0) return false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        try {
            LocalDate tdd  = LocalDate.parse(td,formatter);
            LocalDate sdd = LocalDate.parse(sd,formatter);

            return sdd.isAfter(tdd);
        } catch (DateTimeParseException e) {
            return false;
        }
    }


}
