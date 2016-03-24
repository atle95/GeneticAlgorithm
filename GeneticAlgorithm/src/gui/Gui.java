package gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Gui extends Application
{
  GraphicsContext gfx;
  @FXML Canvas canvas;

  @Override
  public void start(Stage primaryStage) throws Exception 
  {
    
    GuiControls controller = new GuiControls();
    Scene scene = new Scene(controller);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Genetic Algorithm by Atle and Chris");
    gfx = controller.getCanvas().getGraphicsContext2D();
   
    gfx.setFill(Color.rgb(0, 0, 255, 1.0));
    gfx.fillPolygon(new double[]{0, 200, 200}, new double[]{0, 200, 0}, 3);
    
    gfx.setFill(Color.rgb(255, 255, 0, 0.3));
    gfx.fillPolygon(new double[]{0, 0, 200}, new double[]{0, 200, 0}, 3);
    
    
    
    primaryStage.show();
    
  }
  
  public static void main(String[] args)
  {
    launch(args);
  }

}
