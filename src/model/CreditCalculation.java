package model;

import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

/**
 * @author Yar
 */
public class CreditCalculation {

    private int calcID;
    private BigDecimal aCoefficent;
    private ObservableList<MonthData> monthPayments; //Месячные платежи
    private BigDecimal percents;
    private BigDecimal fullAmount; //Полная стоимость
    private CreditData creditData;

    public CreditCalculation(CCBuilder ccBuilder) {
        calcID = ccBuilder.calcID;
        aCoefficent = ccBuilder.aCoefficent;
        monthPayments = ccBuilder.monthPayments;
        percents = ccBuilder.percents;
        fullAmount = ccBuilder.fullAmount;
        creditData = ccBuilder.creditData;
    }


    public static class CCBuilder{
        private int calcID;
        private BigDecimal aCoefficent;
        private ObservableList <MonthData> monthPayments;
        private BigDecimal percents;
        private BigDecimal fullAmount;
        private CreditData creditData;

        public CCBuilder(int calcID){
            this.calcID = calcID;
        }

        public CCBuilder aCoefficent(BigDecimal val){
            this.aCoefficent = val;
            return this;
        }

        public CCBuilder monthPayments(ObservableList<MonthData> val){
            monthPayments = val;
            return this;
        }

        public CCBuilder percents(BigDecimal val){
            percents = val;
            return this;
        }

        public CCBuilder fullAmount(BigDecimal val){
            fullAmount = val;
            return this;
        }

        public CCBuilder creditData(CreditData val){
            creditData = val;
            return this;
        }

        public CreditCalculation build(){
            return new CreditCalculation(this);
        }
    }

    public int getCalcID() {
        return calcID;
    }

    public BigDecimal getaCoefficent() {
        return aCoefficent;
    }

    public ObservableList<MonthData> getMonthPayments() {
        return monthPayments;
    }

    public BigDecimal getPercents() { return percents; }

    public BigDecimal getFullAmount() {
        return fullAmount;
    }

    public CreditData getCreditData() {
        return creditData;
    }
}
