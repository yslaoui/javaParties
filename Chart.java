package javaParties;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.Map;

public class Chart extends Application {
    @Override
    public void start(Stage window) throws Exception {

        // Loading the data
        Map<String, Map<Integer, Double>> data = Csv.read("src/main/java/javaParties/partiesdata.tsv");

        // Axis
        NumberAxis xAxis = new NumberAxis("Year", 1968, 2008, 4);
        NumberAxis yAxis = new NumberAxis("Relative Support (%)", 0, 30, 5);

        // Chart
        LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
        for (String key: data.keySet()) {
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName(key);
            ObservableList<XYChart.Data<Number, Number>> list_series = series.getData();
            for (Map.Entry<Integer, Double> pair: data.get(key).entrySet()) {
                list_series.add(new XYChart.Data<Number, Number>(pair.getKey(), pair.getValue()));
            }
            chart.getData().addAll(series);
        }

        // Scene
        Scene scene = new Scene(chart);
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(Chart.class);
    }
}
