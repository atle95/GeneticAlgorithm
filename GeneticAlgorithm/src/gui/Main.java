package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import core.FitnessCalculator;
import engine.Attributes;
import engine.Genome;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
//import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 
 * @author Atle Olson
 *
 */
public class Main extends Application
{
  public AnimationTimer gameLoop;
  
  public GraphicsContext gfxR;
  public GraphicsContext gfxL;
  public GraphicsContext gfxF;
  
  public Genome triangleManager;
  public GuiControls controller;
  public FitnessCalculator fitCalc;
  
  Image monalisa    = new Image("File:Resources/Images/monalisa.png");
  Image poppyfields = new Image("File:Resources/Images/poppyfields.png");
  Image greatwave   = new Image("File:Resources/Images/greatwave.png");
  Image vangogh     = new Image("File:Resources/Images/vangogh.png");
  Image mcescher    = new Image("File:Resources/Images/mcescher.png");
  Image curImage = monalisa;
  Scene scene;
  PixelReader reader;
  public BufferedImage bimg = new BufferedImage(Attributes.imageWidth, Attributes.imageHeight, BufferedImage.TYPE_INT_ARGB);
  Graphics2D bigfx = bimg.createGraphics();
  
  
  public boolean paused = true;
  
  public Main()
  {
    
  }
  
  @Override
  public void start(Stage primaryStage) throws Exception 
  {
    controller = new GuiControls(this);
    triangleManager = new Genome(this);
    fitCalc = new FitnessCalculator(this);
    scene = new Scene(controller);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Genetic Algorithm by Atle and Chris");
    gfxR = controller.getCanvasRight().getGraphicsContext2D();
    gfxL = controller.getCanvasLeft().getGraphicsContext2D();
    gfxF = controller.getFitnessCanvas().getGraphicsContext2D();

    bigfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    drawCurImage(gfxL, monalisa);
    triangleManager.initializeTriangles();
    drawCurImage(gfxR, SwingFXUtils.toFXImage(bimg, null));
    drawCurImage(gfxL, getSnapShot(controller.getCanvasRight(), 200, 200, 100, 100));
    fitCalc.getOriginalImageFitness();
    fitCalc.calculateFitnessOfMutation();
    primaryStage.show();
    
    
  }
  
  public void drawCurImage(GraphicsContext fx, Image img)
  {
    fx.drawImage(img, 0, 0);
  }

  public void setCurImage(Image img)
  {
    this.curImage = img;
    Attributes.imageHeight = (int) curImage.getHeight();
    Attributes.imageWidth  = (int) curImage.getHeight();
    drawCurImage(gfxL, curImage);
    //reinitialize fitness calculator stuff;
  }

  public Image getCurImage()
  {
    return this.curImage;
  }
  
  public void setBlendMode(BlendMode mode)
  {
    gfxR.setGlobalBlendMode(mode);
  }
  
  public BufferedImage getBufferedTriangle(Genome triManag)
  { 
    clearTriangles();
    for(int i = 0; i<Attributes.numTriangles; i++)
    {
      bigfx.setPaint(new Color(
          triManag.triangleList.get(i).c[0] & 0xFF,
          triManag.triangleList.get(i).c[1] & 0xFF,
          triManag.triangleList.get(i).c[2] & 0xFF,
          triManag.triangleList.get(i).c[3] & 0xFF
          
          ));
      int[] tempX = new int[3];
      int[] tempY = new int[3];
      for(int j = 0; j<triManag.triangleList.get(i).x.length; j++)
      {
        tempX[j] = (int) triManag.triangleList.get(i).x[j];
        tempY[j] = (int) triManag.triangleList.get(i).y[j];
      }
      bigfx.fillPolygon(tempX, tempY, 3);
    }
    return bimg;
  }
  
  public WritableImage getSnapShot(Canvas canvas, int x, int y, int w, int h)
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
    //gfxR.setGlobalBlendMode(BlendMode.SRC_OVER);
    gfxR.clearRect(0, 0, controller.getCanvasRight().getWidth(), controller.getCanvasRight().getHeight());
  }
  
  public static void main(String[] args)
  {
    launch(args);
  }

}
