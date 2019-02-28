package controller.calculation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CreditCalculation;
import model.CreditData;
import model.MonthData;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Класс для расчета кредита по дифференцированной схеме
 * @author Yar
 */
public class DifferrentCalc extends Calculator {

    @Override
    public CreditCalculation calculate(CreditData data) {

        BigDecimal creditSum = data.getAmount();
        if (data.getFirstContribution() != null){
            creditSum = creditSum.subtract(data.getFirstContribution());
            System.out.println("Сумма кредита с первым взносом: " + creditSum.toPlainString());
        }

        //Основной платеж
        BigDecimal mainPaiment = creditSum.divide(new BigDecimal(data.getDuration()), 2, RoundingMode.HALF_UP);
        System.out.println("Основной платеж: " + mainPaiment.toPlainString());

        //Месячна процентная ставка
        BigDecimal monthInterestRate = data.getInterestRateYear().divide(new BigDecimal(1200), 3, RoundingMode.HALF_UP);

        ObservableList<MonthData> values = FXCollections.observableArrayList();
        BigDecimal remainder = BigDecimal.ZERO;
        //Начисленные проценты
        for (int i = 1; i <= data.getDuration(); i++){

            MonthData mData = new MonthData(-1);

            BigDecimal percents = mainPaiment.multiply(new BigDecimal(i-1));
            percents = creditSum.subtract(percents);
            percents = percents.multiply(monthInterestRate);
            BigDecimal percentsWMainSum = percents.add(mainPaiment);
            percentsWMainSum = percentsWMainSum.setScale(2, RoundingMode.HALF_UP);
            //Если есть процент за обслуживание в исходных данных
            BigDecimal servicePMoney = BigDecimal.ZERO;
            if (data.getCreditServicePercent() != null && data.getCreditServicePercent().compareTo(BigDecimal.ZERO)>0) {
                BigDecimal servicePercent = data.getCreditServicePercent().divide(new BigDecimal(100), 4, RoundingMode.HALF_UP);
                servicePMoney = mainPaiment.multiply(servicePercent);
                percents = percents.add(servicePMoney);
                System.out.println("Ежемесячный платеж с сервисным сбором " + percents.toPlainString());
            }
            percents = percents.setScale(2, RoundingMode.HALF_UP);
            System.out.println("Проценты: " + percents.toPlainString() + " за месяц: " + i);

            mData.setMonth(i);
            mData.setDate(data.getStartDate().plusMonths(i-1));
            mData.setRemainder(BigDecimal.ZERO);
            mData.setMainPayment(mainPaiment);
            mData.setPercents(percents);
            mData.setService(servicePMoney);
            mData.setFullPayment(percentsWMainSum);

            values.add(mData);
        }

        //Полная сумма кредита
        BigDecimal fullAmount = BigDecimal.ZERO;
        for (MonthData val : values){
            fullAmount = fullAmount.add(val.getFullPayment());
        }
        System.out.println("Полная сумма кредита: " + fullAmount.toPlainString());

        CreditCalculation calculation = new CreditCalculation.CCBuilder(data.getId())
                .monthPayments(values)
                .fullAmount(fullAmount)
                .percents(fullAmount.subtract(data.getAmount()))
                .creditData(data)
                .build();

        return calculation;
    }
}
