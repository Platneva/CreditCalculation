package model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Yar
 */
public class CreditData {

    private int id;
    private BigDecimal amount;
    private BigDecimal interestRateYear;
    private int duration;
    private LocalDate startDate;
    private CreditType type;

    private CreditData(CreditBuilder creditBuilder) {
        this.id = creditBuilder.id;
        this.amount = creditBuilder.amount;
        this.interestRateYear = creditBuilder.interestRateYear;
        this.duration = creditBuilder.duration;
        this.startDate = creditBuilder.startDate;
        this.type = creditBuilder.type;
    }

    public static class CreditBuilder {

        private int id;
        private BigDecimal amount;
        private BigDecimal interestRateYear;
        private int duration;
        private LocalDate startDate;
        private CreditType type;

        public CreditBuilder(int id){
            this.id = id;
        }

        public CreditBuilder amount(BigDecimal val){
            this.amount = val;
            return this;
        }

        public CreditBuilder interestRateYear(BigDecimal val) {
            interestRateYear = val;
            return this;
        }

        public CreditBuilder duration(int val){
            duration = val;
            return this;
        }

        public CreditBuilder startDate(LocalDate val){
            startDate = val;
            return this;
        }

        public CreditBuilder type(CreditType val){
            type = val;
            return this;
        }

        public CreditData build(){
            return new CreditData(this);
        }
    }

    public int getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getInterestRateYear() {
        return interestRateYear;
    }

    public int getDuration() {
        return duration;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public CreditType getType() {
        return type;
    }
}
