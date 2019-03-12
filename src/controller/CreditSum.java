package controller;

import model.DataIn;

import java.math.BigDecimal;
import java.util.Scanner;

public class CreditSum {

    private DataIn di;

    private void sum (DataIn di){

        System.out.print(new BigDecimal(String.valueOf(di.getPrice())).subtract(di.getDownpayment()));
    }

    public CreditSum (DataIn data){
        di = data;

        sum (di);
    }

}
