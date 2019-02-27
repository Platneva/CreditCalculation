package controller.calculation;

import model.CreditCalculation;
import model.CreditData;

/**
 * @author Yar
 */
public abstract class Calculator {

    public abstract CreditCalculation calculate(CreditData data);
}
