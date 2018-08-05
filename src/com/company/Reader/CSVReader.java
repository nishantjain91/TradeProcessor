package com.company.Reader;

import com.company.TradeMapper;
import com.company.TradeProcessor.FieldNames;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class CSVReader extends Reader implements Iterator<TradeMapper> {


    private String[] fields;
    private BufferedReader br;
    private String splitCharacter;
    private String line;


    public CSVReader(String fileName, boolean header, String split) throws IOException{
        br = new BufferedReader(new FileReader(fileName));
        this.splitCharacter = split;
        if(header==true) processHeaders(br.readLine());
        else processHeaders();
        advance();
    }

    private void processHeaders() {
        FieldNames[] fn = FieldNames.values();
        fields = new String[fn.length];
        for(int i=0;i<fields.length;i++){
            fields[i] = fn[i].toString();
        }
    }

    private void processHeaders(String headers){
        if(headers==null || headers==""){
            processHeaders();
            return;
        }
        fields = headers.split(splitCharacter);
        for (int i = 0; i < fields.length; i++)
            fields[i] = fields[i].trim();
    }


    @Override
    public boolean hasNext() {
        return line!=null;
    }

    @Override
    public TradeMapper next() {
        Map<String,String> map = new HashMap<>();
        String s = line;

        String[] sArray = line.split(splitCharacter);
        for (int i = 0; i < sArray.length; i++)
            sArray[i] = sArray[i].trim();

        for (int i = 0; i < sArray.length; i++)
            map.put(fields[i],sArray[i]);
        advance();
        return new TradeMapper(map);
    }

    private void advance(){
        try{
            line = br.readLine();
        }catch (IOException e){
            if(line ==null && br!=null){
                try {
                    br.close();
                }catch (IOException ex){
                    System.out.println(ex);
                }
            }
        }
    }
}
