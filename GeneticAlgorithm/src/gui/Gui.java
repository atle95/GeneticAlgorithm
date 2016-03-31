package gui;

import core.FitnessCalculator;
import core.TriangleCanvas;
import engine.Attributes;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import triangles.TriangleManager;

public class Gui extends Application
{
  public AnimationTimer gameLoop;
  
  private GraphicsContext gfxR;
  private static GraphicsContext gfxL;
  private GraphicsContext gfxF;
  
  TriangleManager triangleManager;
  GuiControls controller;
  Image monalisa = new Image("File:GeneticAlgorithm/Resources/Images/monalisa.png");
  Image poppyfields = new Image("File:Resources/Images/poppyfields.png");
  Image greatwave = new Image("File:Resources/Images/greatwave.png");
  Image vangogh = new Image("File:Resources/Images/vangogh.png");
  Image mcescher = new Image("File:Resources/Images/mcescher.png");
  Scene scene;
  PixelReader reader;

  public boolean paused = true;
  
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
    gfxF = controller.getFitnessCanvas().getGraphicsContext2D();

    drawCurImage(monalisa);
    triangleManager.initializeTriangles();
    drawTriangles();
    drawCurImage(getSnapShot(controller.getCanvasRight(), 0, 10, 100, 100));
    primaryStage.show();
    FitnessCalculator.getPixelsFromOriginalImage();
    
    gameLoop = new MainGameLoop();
    gameLoop.start();
  }
  
  public void drawTriangles()
  {
    BlendMode temp = gfxR.getGlobalBlendMode();
    clearTriangles();
    setBlendMode(temp);
    for(int i = 0; i<Attributes.numTriangles;i++)
    {
      gfxR.setFill(Color.rgb(
          triangleManager.triangleList.get(i).r, 
          triangleManager.triangleList.get(i).g,
          triangleManager.triangleList.get(i).b,
          triangleManager.triangleList.get(i).a
          )
          );
      
//      System.err.println( "red   " + triangleManager.triangleList.get(i).r 
//                        + " green " + triangleManager.triangleList.get(i).g 
//                        + " blue  " + triangleManager.triangleList.get(i).b);
      
      gfxR.fillPolygon(triangleManager.triangleList.get(i).x, triangleManager.triangleList.get(i).y,3);
    }

    
    //TODO Creates an object containing the right-side canvas
    new TriangleCanvas(gfxR);
    FitnessCalculator.getPixelsFromRightCanvas();
    
  }
  
  public void drawCurImage(Image img)
  {
    gfxL.drawImage(img, 0, 0);
  }
  
  public void setBlendMode(BlendMode mode)
  {
    gfxR.setGlobalBlendMode(mode);
  }
  
  WritableImage getSnapShot(Canvas canvas, int x, int y, int w, int h)
  {
    SnapshotParameters parameters = new SnapshotParameters();
    parameters.setViewport(new Rectangle2D(x, y, w+x, h+y));
    WritableImage wi = new WritableImage(w, h);
    WritableImage snapshot = canvas.snapshot(parameters, wi);
    return snapshot;
  }
  
  WritableImage getSnapShot(Canvas canvas, int[] input)
  {
    SnapshotParameters parameters = new SnapshotParameters();
    parameters.setViewport(new Rectangle2D(input[0], input[1], input[2], input[3]));
    WritableImage wi = new WritableImage(input[2], input[3]);
    WritableImage snapshot = canvas.snapshot(parameters, wi);
    return snapshot;
  }
   
  public void clearTriangles()
  {
    gfxR.setGlobalBlendMode(BlendMode.SRC_OVER);
    gfxR.clearRect(0, 0, controller.getCanvasRight().getWidth(), controller.getCanvasRight().getHeight());
  }
  
  private class MainGameLoop extends AnimationTimer
  {

    /**
     * Call the appropriate method to update the attributes of the
     * entities in the game.
     */
    public void handle(long now)
    {
      if(!paused )
      {
        triangleManager.mutateTriangle();
        drawTriangles();
      }
      
    }
  }
  
  public static void main(String[] args)
  {
    launch(args);
  }

}
