package model.calculation;

import model.CreditCalculation;
import model.CreditData;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yar
 */
public class AnnuityCalc extends Calculator {
    @Override
    public CreditCalculation calculate(CreditData data) {

        //Коэффицент аннуитета
        BigDecimal monthInterestRate = data.getInterestRateYear().divide(new BigDecimal(12), 3, RoundingMode.HALF_UP);
        monthInterestRate = monthInterestRate.divide(new BigDecimal(100), 3, RoundingMode.HALF_UP);
        System.out.println("Месячная процентная ставка: " + monthInterestRate.toPlainString());

        BigDecimal a = BigDecimal.ONE.add(monthInterestRate);
        BigDecimal b = a;
        //Возводим BigDecimal в степень кустарным методом
        for (int i = 0; i < data.getDuration(); i++){
            b = b.multiply(a);
        }
        BigDecimal c = monthInterestRate.multiply(b);

        BigDecimal d = b.subtract(BigDecimal.ONE);
        BigDecimal aCoefficent = c.divide(d, 3, RoundingMode.HALF_UP);
        System.out.println("Коэффицент аннуетета: " + aCoefficent.toPlainString());

        //Ежемесячный платеж
        BigDecimal monthlyPayment = data.getAmount().multiply(aCoefficent);
        System.out.println("Ежемесячный платеж: " + monthlyPayment.toPlainString());
        Map<Integer, BigDecimal> monthPayments = new HashMap<>();
        int i = 1;
        while (i <= data.getDuration()){
            monthPayments.put(i, monthlyPayment);
            i++;
        }

        //Полная стоимость кредита
        BigDecimal fullAmount = monthlyPayment.multiply(new BigDecimal(data.getDuration()));
        System.out.println("Полная стоимость кредита: " + fullAmount.toPlainString());

        //Добавляем данные в калькуляцию
        CreditCalculation calculation = new CreditCalculation.CCBuilder(data.getId())
                .aCoefficent(aCoefficent)
                .monthPayments(monthPayments)
                .fullAmount(fullAmount)
                .creditData(data)
                .build();

        return calculation;
    }
}
