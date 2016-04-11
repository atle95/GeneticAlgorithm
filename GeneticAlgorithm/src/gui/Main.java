package gui;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

import engine.Attributes;
import engine.Tribe;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
//import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 
 * @author Atle Olson
 *
 */
public class Main extends Application
{
  private static int numThreads = 1;

  public AnimationTimer gameLoop;
  
  public GraphicsContext gfxR;
  public GraphicsContext gfxL;
  public GraphicsContext gfxF;
  
  public GuiControls controller;
//  public FitnessCalculator fitCalc;
  ArrayList<Thread> threadList = new ArrayList<Thread>();
  
  public Boolean settingImage = false;
  
  public double greatestFitness = 0;
  
  Image monalisa    = new Image("File:Resources/Images/monalisa.png");
  Image poppyfields = new Image("File:Resources/Images/poppyfields.png");
  Image greatwave   = new Image("File:Resources/Images/greatwave.png");
  Image vangogh     = new Image("File:Resources/Images/vangogh.png");
  Image mcescher    = new Image("File:Resources/Images/mcescher.png");
  Image curImage = monalisa;
  private Image curGenome;
  Scene scene;
  PixelReader reader;
//  public Random random = new Random(Attributes.seed);
  public Random random = new Random();
  
  
  public boolean paused = true;
  
  public Main()
  {
    
  }
  
  @Override
  public void start(Stage primaryStage) throws Exception 
  {
    controller = new GuiControls(this);
//    fitCalc = new FitnessCalculator(this);
    scene = new Scene(controller);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Genetic Algorithm by Atle and Chris");
    gfxR = controller.getCanvasRight().getGraphicsContext2D();
    gfxL = controller.getCanvasLeft().getGraphicsContext2D();
    gfxF = controller.getFitnessCanvas().getGraphicsContext2D();

    drawCurImage(gfxL, monalisa);
//    genome.initializeTriangles();
//    drawCurImage(gfxR, SwingFXUtils.toFXImage(genome.bimg, null));
//    fitCalc.getOriginalImageFitness();
//    fitCalc.calculateFitnessOfMutation();

    
    initializeTribes();
    AnimationTimer gameLoop = new MainGameLoop();
    gameLoop.start();
    
    primaryStage.show();
  }
  
  public void drawCurImage(GraphicsContext fx, Image img)
  {
    fx.setFill(Color.WHITE);
    fx.fillRect(0, 0, img.getWidth(), img.getHeight());
    fx.drawImage(img, 0, 0);
  }
  
  void initializeTribes() 
  {
    final CyclicBarrier barrier = new CyclicBarrier(numThreads, new Runnable()
    {
      public void run()
      {
        //crossover goes here
      }
    });
    
    //  each tribe is initialized by a different job on the threads
    for(int i = 0; i < numThreads; i++)
    {
      new Thread(new Tribe(barrier, this, i)).start();
    }
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
  
  public static void main(String[] args)
  {
    launch(args);
  }

  public void setCurrFit(String string)
  {
    controller.currFit.setText(string);
  }

  public Image getCurGenome() {
    return curGenome;
  }

  public void setCurGenome(Image curGenome)
  {
    this.curGenome = curGenome;
  }

  class MainGameLoop extends AnimationTimer
  {
    public void handle(long now)
    {
      if(!settingImage)
      {
        drawCurImage(gfxR, curGenome);
      }
    }
  }
}
