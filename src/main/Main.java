package main;

import controller.CreditSum;
import java.math.BigDecimal;
import java.util.Scanner;

/*import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;*/
import model.DataIn;

public class Main /*extends Application*/ {

  /*  @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
        primaryStage.setTitle("Расчет кредита");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }*/


    public static void main(String[] args) {
        /*launch(args);
    }*/
        Scanner scPr = new Scanner(System.in);
        System.out.print("Введите стоимость товара:");
        BigDecimal scPrice = scPr.nextBigDecimal();
        Scanner scDp = new Scanner(System.in);
        System.out.print("Введите размер первоначального взноса:");
        BigDecimal scDownpayments = scDp.nextBigDecimal();
        DataIn data = new DataIn(scPrice, scDownpayments);



        CreditSum cs = new CreditSum(data);
    }
}
