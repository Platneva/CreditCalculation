package model;

import java.math.BigDecimal;
import java.util.Scanner;

public class DataIn {
    private int month; // срок кредита в месяцах
    private BigDecimal percent; //годовая процентная ставка
    private BigDecimal price; // стоимость товара
    private BigDecimal downpayment; // первый взнос по кредиту


    public DataIn (int m, BigDecimal per, BigDecimal pr, BigDecimal dp){
        month = m;
        percent = per;
        price = pr;
        downpayment = dp;
    }

    public DataIn(BigDecimal pr, BigDecimal dp){
        price = pr;
        downpayment = dp;

    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;

    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public void setDownpayment(BigDecimal downpayment) {
        this.downpayment = downpayment;
    }

    public int getMonth() {
        return month;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getDownpayment() {
        return downpayment;
    }


}
