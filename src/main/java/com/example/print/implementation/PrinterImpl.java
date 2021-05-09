package com.example.print.implementation;


import com.example.print.api.Printer;

public class PrinterImpl implements Printer {
    public void printResult(String result) {
        if(result != null) {
            System.out.println(result);
        }
    }
}