package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Yar
 */
public class MonthData {
    private int id;
    private SimpleIntegerProperty month;
    private SimpleObjectProperty<LocalDate> date;
    private SimpleObjectProperty<BigDecimal> remainder;
    private SimpleObjectProperty<BigDecimal> mainPayment;
    private SimpleObjectProperty<BigDecimal> percents;
    private SimpleObjectProperty<BigDecimal> service;
    private SimpleObjectProperty<BigDecimal> fullPayment;

    public MonthData(int id){
        this.id = id;
    }

    public void setMonth(int month){
        this.month = new SimpleIntegerProperty(month);
    }

    public void setDate(LocalDate date) {
        this.date = new SimpleObjectProperty<LocalDate>(date);
    }

    public void setRemainder(BigDecimal remainder){
        this.remainder = new SimpleObjectProperty<>(remainder);
    }

    public void setMainPayment(BigDecimal mainPayment){
        this.mainPayment = new SimpleObjectProperty<>(mainPayment);
    }

    public void setPercents(BigDecimal percents){
        this.percents = new SimpleObjectProperty<>(percents);
    }

    public void setService(BigDecimal service){
        this.service = new SimpleObjectProperty<>(service);
    }

    public void setFullPayment(BigDecimal fullPayment){
        this.fullPayment = new SimpleObjectProperty<>(fullPayment);
    }

    public int getId() {
        return id;
    }

    public int getMonth() {
        return month.get();
    }

    public LocalDate getDate() {
        return date.get();
    }

    public BigDecimal getRemainder() {
        return remainder.get();
    }

    public BigDecimal getMainPayment() {
        return mainPayment.get();
    }

    public BigDecimal getPercents() {
        return percents.get();
    }

    public BigDecimal getService() {
        return service.get();
    }

    public BigDecimal getFullPayment() {
        return fullPayment.get();
    }

    public SimpleIntegerProperty monthProperty() {
        return month;
    }

    public SimpleObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public SimpleObjectProperty<BigDecimal> remainderProperty() {
        return remainder;
    }

    public SimpleObjectProperty<BigDecimal> mainPaymentProperty() {
        return mainPayment;
    }

    public SimpleObjectProperty<BigDecimal> percentsProperty() {
        return percents;
    }

    public SimpleObjectProperty<BigDecimal> serviceProperty() {
        return service;
    }

    public SimpleObjectProperty<BigDecimal> fullPaymentProperty() {
        return fullPayment;
    }
}
