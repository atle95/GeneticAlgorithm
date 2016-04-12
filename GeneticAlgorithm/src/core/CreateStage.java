package core;

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

    @SuppressWarnings("rawtypes")
    XYChart.Series series = new XYChart.Series();
    series.setName("Fitness Progression");
    // populating the series with data
    
//    for(Double d: Genome.getFitnessArray()){
//    //  System.err.println("index " +Genome.getIndex()+ " fitness " +Genome.getFitness());
//    series.getData().add(new XYChart.Data(Genome.getIndex(), Genome.getFitnessArray().get(index)));
//    }
    for(int i = 0; i < Genome.getFitnessArray().size(); i++){
    //  System.err.println("index " +Genome.getIndex()+ " fitness " +Genome.getFitness());
    series.getData().add(new XYChart.Data(Genome.getIndexArray().get(i), Genome.getFitnessArray().get(i)));
    }


    // scene2 = new Scene(lineChart,800,600);
    lineChart.getData().add(series);
    stage.setScene(new Scene(lineChart, 800, 600));
    stage.show();
  }
}