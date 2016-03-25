package gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import triangles.TriangleManager;
import triangles.TriangleObject;

public class Gui extends Application
{
  int pictureWidth;
  int pictureHeight;
  GraphicsContext gfx;
  @FXML Canvas canvas;
  TriangleManager triangleManager;
  
  @Override
  public void start(Stage primaryStage) throws Exception 
  {
    
    GuiControls controller = new GuiControls();
    triangleManager = new TriangleManager();
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
   
    gfx.setFill(Color.rgb(0, 0, 255, 0.5));
    gfx.fillPolygon(new double[]{0, pictureWidth, pictureWidth}, new double[]{0, pictureHeight, 0}, 3);
    
    gfx.setFill(Color.rgb(0, 255, 255, 0.5));
    gfx.fillPolygon(new double[]{0, 0, pictureWidth}, new double[]{0, pictureHeight, 0}, 3);
    
    triangleManager.initializeTriangles();
    drawTriangles();
    
    primaryStage.show();
    
  }
  
  public void drawTriangles()
  {
    
    for(TriangleObject triangleObject : triangleManager.triangleList)
    {
      gfx.setFill(Color.rgb(triangleObject.r, triangleObject.g, triangleObject.b, triangleObject.a));
      gfx.fillPolygon(triangleObject.x, triangleObject.y,3);
    }
  }
  
  public static void main(String[] args)
  {
    launch(args);
  }

}
