package core;
/*
 * Credit for this code: http://docs.oracle.com/javafx/2/charts/line-chart.htm
 */

import engine.Genome;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 * 
 * @author Chris Sanchez
 * 
 * This class creates a separate JavaFX
 * stage that displays the Increase in fitness
 * over time
 */
public class CreateStage {

  Scene scene2;
  @SuppressWarnings({ "unchecked", "rawtypes", "unused" })
  
  public CreateStage() {
    Genome g = new Genome();
    Stage stage = new Stage();

    stage.setTitle("Fitness Map");
   
    // defining the axes
    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Time");
    
    // creating the chart
    final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

    lineChart.setTitle("Fitness Progression");
    // defining a series

    XYChart.Series series = new XYChart.Series();
    series.setName("Fitness Progression");

    for(int i = 0; i < Genome.getFitnessArray().size(); i++){
    series.getData().add(new XYChart.Data(Genome.getIndexArray().get(i), Genome.getFitnessArray().get(i)));
    }

    lineChart.getData().add(series);
    stage.setScene(new Scene(lineChart, 800, 600));
    stage.show();
  }
}