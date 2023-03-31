package cz.spsmb.demo;

import cz.spsmb.demo.csv.CsvFormatter;
import cz.spsmb.demo.csv.FormatterHolder;
import cz.spsmb.demo.csv.PercentageFormatter;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main extends Application {

    NumberAxis xAxis;
    NumberAxis yAxis;
    LineChart lineChart;
    VBox vBox;

    @Override
    public void start(Stage stage) {
        stage.setTitle("JavaFX App");

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Table Files", "*.csv"));

        Label label = new Label(" ");
        TextArea textArea = new TextArea();

        FormatterHolder formatterHolder = new FormatterHolder();

        Button button = new Button("Select File");
        button.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(stage);
            if (file.exists()) {
                label.setText("Failed");
                try {
                    Scanner sc = new Scanner(file);
                    sc.nextLine();
                    while (sc.hasNextLine()) {
                        String[] strings = sc.nextLine().split(";");
                        formatterHolder.filterOption(new CsvFormatter(strings[1], strings[2], Double.parseDouble(strings[3])));
                    }
                    formatterHolder.setPercentages();
                    System.out.println(formatterHolder.getPercentageFormatters());
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < formatterHolder.getPercentageFormatters().size(); i++) {
                        PercentageFormatter formatter = formatterHolder.getPercentageFormatters().get(i);
                        builder.append(formatter.getName()).append(" - 1: ").append(formatter.gerPercentageOf(1)).append("\n");
                    }
                    label.setText(file.getName());
                    textArea.setText(builder.toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    label.setText(ex.getMessage());
                }
            }
            vBox.getChildren().add(showGraph(1, 2));
        });

        xAxis = new NumberAxis(1, 9, 1);
        xAxis.setLabel("Values");
        yAxis = new NumberAxis(0, 100, 1);
        yAxis.setLabel("Percentage");

        lineChart = new LineChart(xAxis, yAxis);


        /*ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Grapefruit", 13),
                        new PieChart.Data("Oranges", 25),
                        new PieChart.Data("Plums", 10),
                        new PieChart.Data("Pears", 22),
                        new PieChart.Data("Apples", 30));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Imported Fruits");*/

        vBox = new VBox(button, label, textArea);
        vBox.setAlignment(Pos.CENTER);

        vBox.getChildren().add(lineChart);
        Scene scene = new Scene(vBox, 960, 600);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public Pane showGraph(int firstNum, double percentage) {
        XYChart.Series series = new XYChart.Series();
        series.setName("Podvody");

        for (int i = 0; i < 10; i++) {
            series.getData().add(new XYChart.Data<>(firstNum, percentage));
        }

        lineChart.getData().add(series);
        return new Pane();
    }
}