package controller.calculation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CreditCalculation;
import model.CreditData;
import model.MonthData;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Класс для расчета кредита по аннуитетной схеме
 * @author Yar
 */
public class AnnuityCalc extends Calculator {
    @Override
    public CreditCalculation calculate(CreditData data) {

        BigDecimal creditSum = data.getAmount();
        if (data.getFirstContribution() != null && data.getFirstContribution().compareTo(BigDecimal.ZERO)>0){
            creditSum = creditSum.subtract(data.getFirstContribution());
            System.out.println("Сумма кредита с первым взносом: " + creditSum.toPlainString());
        }

        //Коэффицент аннуитета
        BigDecimal monthInterestRate = data.getInterestRateYear().divide(new BigDecimal(1200), 5, RoundingMode.HALF_UP);
        System.out.println("Месячная процентная ставка: " + monthInterestRate.toPlainString());

        BigDecimal a = BigDecimal.ONE.add(monthInterestRate);
        BigDecimal b = BigDecimal.ONE.add(monthInterestRate);
        //Возводим BigDecimal в степень кустарным методом
        for (int i = 0; i < data.getDuration(); i++){
            b = b.multiply(a, new MathContext(4, RoundingMode.HALF_UP));
        }
        BigDecimal c = monthInterestRate.multiply(b, new MathContext(4, RoundingMode.HALF_UP));

        BigDecimal d = b.subtract(BigDecimal.ONE);
        BigDecimal aCoefficent = c.divide(d, 3, RoundingMode.HALF_UP);
        System.out.println("Коэффицент аннуетета: " + aCoefficent.toPlainString());

        //Основная сумма без процентов
        BigDecimal cleanPayment = creditSum.divide(new BigDecimal(data.getDuration()), 2, RoundingMode.HALF_UP);

        //Ежемесячный платеж
        BigDecimal monthlyPayment = creditSum.multiply(aCoefficent);
        monthlyPayment.setScale(2, RoundingMode.HALF_UP);
        System.out.println("Ежемесячный платеж: " + monthlyPayment.toPlainString());

        //Если есть процент за обслуживание в исходных данных
        BigDecimal servicePercent = BigDecimal.ZERO;
        BigDecimal servicePercentMoney = BigDecimal.ZERO;
        if (data.getCreditServicePercent() != null && data.getCreditServicePercent().compareTo(BigDecimal.ZERO)>0) {
            servicePercent = data.getCreditServicePercent().divide(new BigDecimal(100), 4, RoundingMode.HALF_UP);
            servicePercentMoney = monthlyPayment.multiply(servicePercent);
            monthlyPayment = monthlyPayment.add(servicePercentMoney);
            monthlyPayment.setScale(2, RoundingMode.HALF_UP);
            System.out.println("Ежемесячный платеж с сервисным сбором" + monthlyPayment.toPlainString());
        }

        //Полная стоимость кредита
        BigDecimal fullAmount = monthlyPayment.multiply(new BigDecimal(data.getDuration()));
        System.out.println("Полная стоимость кредита: " + fullAmount.toPlainString());



        //Проценты в месяц
        BigDecimal percPerMonth = monthlyPayment.subtract(cleanPayment);

        //Основная сумма
        BigDecimal sumPerMonth = monthlyPayment.subtract(percPerMonth);
        sumPerMonth = sumPerMonth.setScale(2, RoundingMode.HALF_UP);

        //Остаток от кредита
        BigDecimal remainder = BigDecimal.ZERO;

        ObservableList<MonthData> monthPayments = FXCollections.observableArrayList();
        for (int i = 1; i <= data.getDuration(); i++){
            //Остаток от кредита
            remainder = remainder.add(monthlyPayment);
            BigDecimal fRemainder = fullAmount.subtract(remainder);
            fRemainder = fRemainder.setScale(2, RoundingMode.HALF_UP);

            MonthData mData = new MonthData(-1);
            mData.setMonth(i);
            mData.setFullPayment(monthlyPayment);
            mData.setDate(data.getStartDate().plusMonths(i-1));
            mData.setMainPayment(cleanPayment);
            mData.setRemainder(fRemainder);
            mData.setPercents(percPerMonth);
            mData.setService(servicePercentMoney);

            monthPayments.add(mData);
        }

        if (data.getFirstContribution() != null && data.getFirstContribution().compareTo(BigDecimal.ZERO)>0){
            fullAmount = fullAmount.add(data.getFirstContribution());
        }

        //Все проценты в деньгах
        BigDecimal percents = fullAmount.subtract(data.getAmount());

        //Добавляем данные в калькуляцию
        CreditCalculation calculation = new CreditCalculation.CCBuilder(data.getId())
                .aCoefficent(aCoefficent)
                .monthPayments(monthPayments)
                .percents(percents)
                .fullAmount(fullAmount)
                .creditData(data)
                .build();

        return calculation;
    }
}
