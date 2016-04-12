package core;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

import engine.Attributes;
import engine.Genome;
import engine.Tribe;
import gui.GuiControls;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
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
  public ArrayList<Thread> threadList = new ArrayList<Thread>();
  public double searchSize = Math.sqrt(3)*255*Attributes.imageWidth*Attributes.imageHeight;
  public Random random = new Random(Attributes.seed);
  
  public double greatestFitness = 0;
  public double currFitness = 0;
  public int numGenerations = 0;
  
  public Boolean settingImage = false;
  Image monalisa    = new Image("File:GeneticAlgorithm/Resources/Images/monalisa.png");
  Image poppyfields = new Image("File:Resources/Images/poppyfields.png");
  Image greatwave   = new Image("File:Resources/Images/greatwave.png");
  Image vangogh     = new Image("File:Resources/Images/vangogh.png");
  Image mcescher    = new Image("File:Resources/Images/mcescher.png");
  Image curImage = monalisa;
  private Image curGenome;
  Scene scene;
  Scene scene2;
  PixelReader reader;
//  public Random random = new Random();
  
  
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
    
    Group root = new Group();
    scene2 = new Scene(root, 700, 700, Color.LIGHTGREEN);
    
    primaryStage.setScene(scene);
    
    //primaryStage.setScene(scene2);
    
    primaryStage.setTitle("Genetic Algorithm by Atle and Chris");
    gfxR = controller.getCanvasRight().getGraphicsContext2D();
    gfxL = controller.getCanvasLeft().getGraphicsContext2D();
    gfxF = controller.getFitnessCanvas().getGraphicsContext2D();

    drawCurImage(gfxL, monalisa);
//    genome.initializeTriangles();
//    fitCalc.getOriginalImageFitness();
//    fitCalc.calculateFitnessOfMutation();
    initializeTribes();
    AnimationTimer gameLoop = new MainGameLoop();
    gameLoop.start();
    
    /*
     ================================
     Added this to make a button that 
     shows another stage
     ================================
     */
    Button btn = new Button();
    btn.setLayoutX(20);
    btn.setLayoutY(20);
    btn.setText("Show Graph");
    btn.setOnAction(new EventHandler<ActionEvent>() {

        public void handle(ActionEvent event) {
            new CreateStage();
            primaryStage.toFront();

        }
    });
    root.getChildren().add(btn);
    
    primaryStage.show();
    
    
  }
  
  public void drawCurImage(GraphicsContext fx, Image img)
  {
    fx.setFill(Color.WHITE);
    fx.fillRect(0, 0, img.getWidth(), img.getHeight());
    fx.drawImage(img, 0, 0);
  }
  
  public void initializeTribes() 
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
      Thread e = new Thread(new Tribe(barrier, this, i));
      threadList.add(e);
      e.start();
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
  
  public WritableImage getSnapShot(Canvas canvas)
  {
    SnapshotParameters parameters = new SnapshotParameters();
    parameters.setViewport(new Rectangle2D(0, 0, Attributes.imageWidth, Attributes.imageHeight));
    WritableImage wi = new WritableImage(Attributes.imageWidth, Attributes.imageHeight);
    WritableImage snapshot = canvas.snapshot(parameters, wi);
    return snapshot;
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

  class MainGameLoop extends AnimationTimer
  {
    public void handle(long now)
    {
      if(!settingImage)
      {
        drawCurImage(gfxR, curGenome);
        controller.updateLabels();
      }
    }
  }
}
