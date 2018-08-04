package com.company;

import com.company.Reader.CSVReader;

import java.io.IOException;
import java.util.Iterator;

public class Main {

    public static void main(String[] args)   {

        Iterator reader = null;
        try {
            reader = new CSVReader("TradeData.dat",true,"\\|");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(reader.hasNext()){
            System.out.println(reader.next());
        }

    }
}