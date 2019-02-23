package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.CreditData;
import model.CreditType;
import model.calculation.AnnuityCalc;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        CreditData data = new CreditData.CreditBuilder(5)
                .amount(new BigDecimal(200000))
                .duration(24)
                .interestRateYear(new BigDecimal("15.7"))
                .type(CreditType.ANNUITY)
                .startDate(LocalDate.now())
                .build();

        AnnuityCalc calc = new AnnuityCalc();

        calc.calculate(data);
        Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
        primaryStage.setTitle("Расчет кредита");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
