package model;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Yar
 */
public class CreditCalculation {

    private int calcID;
    private BigDecimal aCoefficent;
    private Map <Integer, BigDecimal> monthPayments;
    private BigDecimal fullAmount;
    private CreditData creditData;

    public CreditCalculation(CCBuilder ccBuilder) {
        calcID = ccBuilder.calcID;
        aCoefficent = ccBuilder.aCoefficent;
        monthPayments = ccBuilder.monthPayments;
        fullAmount = ccBuilder.fullAmount;
        creditData = ccBuilder.creditData;
    }


    public static class CCBuilder{

        private int calcID;
        private BigDecimal aCoefficent;
        private Map <Integer, BigDecimal> monthPayments;
        private BigDecimal fullAmount;
        private CreditData creditData;

        public CCBuilder(int calcID){
            this.calcID = calcID;
        }

        public CCBuilder aCoefficent(BigDecimal val){
            this.aCoefficent = val;
            return this;
        }

        public CCBuilder monthPayments(Map<Integer, BigDecimal> val){
            monthPayments = val;
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

    public Map<Integer, BigDecimal> getMonthPayments() {
        return monthPayments;
    }

    public BigDecimal getFullAmount() {
        return fullAmount;
    }

    public CreditData getCreditData() {
        return creditData;
    }
}
