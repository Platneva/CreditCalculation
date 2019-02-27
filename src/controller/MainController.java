package controller;

import controller.XML.XMLReader;
import controller.XML.XMLWriter;
import controller.calculation.AnnuityCalc;
import controller.calculation.Calculator;
import controller.calculation.DifferrentCalc;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.CreditCalculation;
import model.CreditData;
import model.CreditType;
import model.MonthData;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.time.LocalDate;

/**
 * @author Yar
 */
public class MainController {

    private CreditData data;
    private Calculator calculator;
    private static Stage stage;
    private CreditCalculation calculation;
    private ObservableList<MonthData> tData;
    private FileChooser fileChooser = new FileChooser();

    @FXML
    private TextField creditSum;

    @FXML
    private Spinner<BigDecimal> percents;

    @FXML
    private DatePicker startDate;

    @FXML
    private Spinner<Integer> period;

    @FXML
    private RadioButton rBtn1;
    @FXML
    private RadioButton rBtn2;

    @FXML
    private Spinner<BigDecimal> firstPayment;

    @FXML
    private Spinner<BigDecimal> servicePercent;

    @FXML
    private Label fullCreditPrice;
    @FXML
    private Label payedPercents;
    @FXML
    private Label remainder;
    @FXML
    private Label paymentWOPercent;
    @FXML
    private Label percentWOPayment;
    @FXML
    private Label paymentsFF;

    @FXML
    private TableView<MonthData> tableView;
    @FXML
    private TableColumn<MonthData,Integer> column1;
    @FXML
    private TableColumn<MonthData, LocalDate> column2;
    @FXML
    private TableColumn<MonthData, BigDecimal> column3;
    @FXML
    private TableColumn<MonthData, BigDecimal> column4;
    @FXML
    private TableColumn<MonthData, BigDecimal> column5;
    @FXML
    private TableColumn<MonthData, BigDecimal> column6;
    @FXML
    private TableColumn<MonthData, BigDecimal> column7;

    @FXML
    private Button calc;
    @FXML
    private void calcAction(ActionEvent ae){
        System.out.println(creditSum.getText());
        System.out.println(firstPayment.getEditor().getText());

        buildData();

        if(rBtn1.isSelected() == true){
            calculator = new DifferrentCalc();
            calculation = calculator.calculate(data);
        } else {
            calculator = new AnnuityCalc();
            calculation = calculator.calculate(data);
        }

        fullCreditPrice.setText(calculation.getFullAmount().toPlainString());
        payedPercents.setText(calculation.getPercents().toPlainString());

        initTableData();
    }

    @FXML
    private Button exitBtn;
    @FXML
    private void exitAction(ActionEvent ae){
        if (stage != null){
            stage.close();
        }
    }

    @FXML
    private void openAction(ActionEvent ae){
        File file = fileChooser.showOpenDialog(stage);

        XMLReader reader = new XMLReader(file);
        data = reader.parse();

        creditSum.setText(data.getAmount().toPlainString());
        percents.getEditor().setText(data.getInterestRateYear().toPlainString());
        startDate.setValue(data.getStartDate());
        period.getEditor().setText(data.getDuration()+"");
        firstPayment.getEditor().setText(data.getFirstContribution().toPlainString());
        servicePercent.getEditor().setText(data.getCreditServicePercent().toPlainString());

        if(data.getType() == CreditType.DIFFERENTIATED){
            rBtn1.setSelected(true);
            calculator = new DifferrentCalc();
            calculation = calculator.calculate(data);
        } else {
            rBtn2.setSelected(true);
            calculator = new AnnuityCalc();
            calculation = calculator.calculate(data);
        }

        fullCreditPrice.setText(calculation.getFullAmount().toPlainString());
        payedPercents.setText(calculation.getPercents().toPlainString());

        initTableData();
    }
    @FXML
    private void saveAction(ActionEvent ae){
        File file = fileChooser.showSaveDialog(stage);
        buildData();
        XMLWriter.createDocument(data);
        File source = XMLWriter.createFile(file.getPath());

        if(file != null){
            try{
                Files.copy(source.toPath(), file.toPath());
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод вызываемый для инициализации контроллера
     */
    @FXML
    private void initialize(){
        percents.setEditable(true);
        SpinnerValueFactory<BigDecimal> valueFactory = new SpinnerValueFactory<BigDecimal>(){

            @Override
            public void decrement(int steps) {
                BigDecimal current = this.getValue();
                if (current.compareTo(BigDecimal.ZERO) > 0){
                    this.setValue(current = current.subtract(new BigDecimal(steps)));
                } else {
                    this.setValue(BigDecimal.ZERO);
                }

            }

            @Override
            public void increment(int steps) {
                BigDecimal current = this.getValue();
                if (current.compareTo(new BigDecimal("35"))<0){
                    this.setValue(current = current.add(new BigDecimal(steps)));
                } else {
                    this.setValue(new BigDecimal("35"));
                }

            }
        };
        valueFactory.setValue(BigDecimal.ZERO);
        percents.setValueFactory(valueFactory);

        startDate.setValue(LocalDate.now());

        SpinnerValueFactory<Integer> periodVFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50, 1);
        period.setValueFactory(periodVFactory);

        ToggleGroup toggleGroup = new ToggleGroup();
        rBtn1.setToggleGroup(toggleGroup);
        rBtn1.setSelected(true);
        rBtn2.setToggleGroup(toggleGroup);

        SpinnerValueFactory<BigDecimal> fpFactory = new SpinnerValueFactory<BigDecimal>() {
            @Override
            public void decrement(int steps) {
                BigDecimal current = this.getValue();
                if (current.compareTo(BigDecimal.ZERO) > 0){
                    steps = 10000;
                    this.setValue(current = current.subtract(new BigDecimal(steps)));
                } else {
                    this.setValue(BigDecimal.ZERO);
                }
            }

            @Override
            public void increment(int steps) {
                BigDecimal current = this.getValue();
                if (current.compareTo(new BigDecimal("50000000"))<0){
                    steps = 10000;
                        this.setValue(current = current.add(new BigDecimal(steps)));
                } else {
                    this.setValue(new BigDecimal("50000000"));
                }
            }
        };
        firstPayment.setValueFactory(fpFactory);
        firstPayment.getValueFactory().setValue(BigDecimal.ZERO);
        firstPayment.getValueFactory().increment(10000);
        firstPayment.getValueFactory().decrement(10000);
        firstPayment.setEditable(true);

        SpinnerValueFactory<BigDecimal> spFactory = new SpinnerValueFactory<BigDecimal>() {
            @Override
            public void decrement(int steps) {
                BigDecimal current = this.getValue();
                if (current.compareTo(BigDecimal.ZERO) > 0){
                    this.setValue(current = current.subtract(new BigDecimal(steps)));
                } else {
                    this.setValue(BigDecimal.ZERO);
                }
            }

            @Override
            public void increment(int steps) {
                BigDecimal current = this.getValue();
                if (current.compareTo(new BigDecimal("10"))<0){
                    this.setValue(current = current.add(new BigDecimal(steps)));
                } else {
                    this.setValue(new BigDecimal("10"));
                }
            }
        };
        servicePercent.setValueFactory(spFactory);
        servicePercent.getValueFactory().setValue(BigDecimal.ZERO);
        servicePercent.setEditable(true);

        tableView.setRowFactory(tv -> {
            TableRow<MonthData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(!row.isEmpty() && event.getButton() == MouseButton.PRIMARY){
                    MonthData clickedRow = row.getItem();
                    remainder.setText(clickedRow.getRemainder().toPlainString());
                    paymentWOPercent.setText(clickedRow.getMainPayment().toPlainString());
                    percentWOPayment.setText(clickedRow.getPercents().toPlainString());
                }
            });
            return row;
        });
    }

    /**
     * Строим модель исходных данных
     */
    private void buildData(){
        data = new CreditData.CreditBuilder(-1)
                .amount(new BigDecimal(creditSum.getText()))
                .interestRateYear(new BigDecimal(percents.getEditor().getText()))
                .duration(period.getValue())
                .startDate(startDate.getValue())
                .type(rBtn1.isSelected() == true ? CreditType.DIFFERENTIATED : CreditType.ANNUITY)
                .firstContribution(firstPayment.getEditor().getText().isEmpty() == true ? null : new BigDecimal(firstPayment.getEditor().getText()))
                .creditServicePercent(servicePercent.getEditor().getText().isEmpty() == true ? null : new BigDecimal(servicePercent.getEditor().getText()))
                .build();
    }

    public static void setStage(Stage val){
        stage = val;
    }

    private void initTableData(){
        tData = calculation.getMonthPayments();

        column1.setCellValueFactory(tData -> tData.getValue().monthProperty().asObject());
        column2.setCellValueFactory(param -> param.getValue().dateProperty());
        column3.setCellValueFactory(param -> param.getValue().remainderProperty());
        column4.setCellValueFactory(param -> param.getValue().mainPaymentProperty());
        column5.setCellValueFactory(param -> param.getValue().percentsProperty());
        column6.setCellValueFactory(param -> param.getValue().serviceProperty());
        column7.setCellValueFactory(param -> param.getValue().fullPaymentProperty());

        tableView.setItems(tData);
    }
}
