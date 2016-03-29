package gui;

import core.FitnessCalculator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import triangles.TriangleManager;
import triangles.TriangleObject;

public class Gui extends Application
{
  GraphicsContext gfxR;
  static GraphicsContext gfxL;
  GraphicsContext gfxF;
  TriangleManager triangleManager;
  GuiControls controller;
  Image monalisa = new Image("File:GeneticAlgorithm/Resources/Images/monalisa.png");
  Image poppyfields = new Image("File:Resources/Images/poppyfields.png");
  Image greatwave = new Image("File:Resources/Images/greatwave.png");
  Image vangogh = new Image("File:Resources/Images/vangogh.png");
  Image mcescher = new Image("File:Resources/Images/mcescher.png");
  Scene scene;
  
  public Gui()
  {
    
  }
  
  @Override
  public void start(Stage primaryStage) throws Exception 
  {
    controller = new GuiControls(this);
    triangleManager = new TriangleManager();
    scene = new Scene(controller);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Genetic Algorithm by Atle and Chris");
    gfxR = controller.getCanvasRight().getGraphicsContext2D();
    gfxL = controller.getCanvasLeft().getGraphicsContext2D();
    
    drawCurImage(monalisa);
    triangleManager.initializeTriangles();
    drawTriangles();
    
    primaryStage.show();
    FitnessCalculator.getPixels();
  }
  
  public void drawTriangles()
  {
    BlendMode temp = gfxR.getGlobalBlendMode();
    clearTriangles();
    setBlendMode(temp);
    for(TriangleObject triangleObject : triangleManager.triangleList)
    {
      gfxR.setFill(Color.rgb(triangleObject.r, triangleObject.g, triangleObject.b, triangleObject.a));
      gfxR.fillPolygon(triangleObject.x, triangleObject.y,3);
    }
  }
  
  public void drawCurImage(Image img)
  {
    gfxL.drawImage(img, 0, 0);
  }
  
  public void setBlendMode(BlendMode mode)
  {
    gfxR.setGlobalBlendMode(mode);
  }
  
  WritableImage getSnapShot()
  {
    return controller.getCanvasRight().snapshot(null, null);
  }
  
  public void clearTriangles()
  {
    gfxR.setGlobalBlendMode(BlendMode.SRC_OVER);
    gfxR.clearRect(0, 0, controller.getCanvasRight().getWidth(), controller.getCanvasRight().getHeight());
  }
  
  public static void main(String[] args)
  {
    launch(args);
  }

}
