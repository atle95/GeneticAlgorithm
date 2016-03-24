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
  int pictureWidth;
  int pictureHeight;
  GraphicsContext gfx;
  @FXML Canvas canvas;

  @Override
  public void start(Stage primaryStage) throws Exception 
  {
    
    GuiControls controller = new GuiControls();
    Scene scene = new Scene(controller);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Genetic Algorithm by Atle and Chris");
    pictureWidth = (int) controller.getCanvas().getWidth();
    pictureHeight = (int) controller.getCanvas().getHeight();
    gfx = controller.getCanvas().getGraphicsContext2D();
    
    gfx.setFill(Color.BLACK);
    gfx.fillRect(0,0,pictureWidth,pictureHeight);
    gfx.setFill(Color.WHITE);
    gfx.fillRect(1, 1, pictureWidth-2, pictureHeight-2);
   
    gfx.setFill(Color.rgb(0, 0, 255, 1.0));
    gfx.fillPolygon(new double[]{0, pictureWidth, pictureWidth}, new double[]{0, pictureHeight, 0}, 3);
    
    gfx.setFill(Color.rgb(255, 255, 0, 0.3));
    gfx.fillPolygon(new double[]{0, 0, pictureWidth}, new double[]{0, pictureHeight, 0}, 3);
    
    
    
    primaryStage.show();
    
  }
  
  public static void main(String[] args)
  {
    launch(args);
  }

}
