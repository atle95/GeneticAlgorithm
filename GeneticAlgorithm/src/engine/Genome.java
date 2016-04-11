package engine;

import gui.DrawGraph;
import gui.Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import core.FitnessCalculator;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;


/**
 * 
 * @author Atle Olson
 *
 */
public class Genome
{
  public ArrayList<TriangleObject> triangleList = new ArrayList<TriangleObject>();
  ArrayList<Double> scores = new ArrayList<Double>();
  
  Main main;
  public BufferedImage bimg = new BufferedImage(Attributes.imageWidth, Attributes.imageHeight, BufferedImage.TYPE_INT_ARGB);
  Graphics2D bigfx = bimg.createGraphics();
  private double temp_fitness;
  public FitnessCalculator fitCalc;
  public int generationCount = 0;
  
  int index = 0;
  
  public Genome(){}
  
  public Genome(Main main)
  {
    this.main = main;
    bigfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    initializeTriangles();
    main.setCurGenome(SwingFXUtils.toFXImage(this.bimg, null));
    fitCalc = new FitnessCalculator(this.main);
  }
  
  public void initializeTriangles()
  {
    for(int i = 0; i < Attributes.maxTriangles; i++)
    {
      TriangleObject member = new TriangleObject(main.random, i);
      triangleList.add(member);
    }
  }
  
  public synchronized void mutateTriangle()
  {
    generationCount++;
    int i = main.random.nextInt(triangleList.size());
    int mutation = main.random.nextInt(20);
    double oldFitness = fitCalc.calculateFitnessOfMutation(this);
    double counter = 1;
    triangleList.get(i).mutate(mutation, counter);
    double newFitness = fitCalc.calculateFitnessOfMutation(this);
    if( newFitness < oldFitness)
    {
      triangleList.get(i).mutate(triangleList.get(i).lastMutation, counter);
    }
    while (newFitness < oldFitness)
    {
      if (counter == 1 && Attributes.debug)
      {
        temp_fitness = 100 - newFitness / 93394396;
        System.out.printf("Mutating Triangle %3d, current fitness: %2.2f%% %n", i, temp_fitness);
        if (index % 25 == 0)
        {
          System.err.println("index " + index);
         // setFitness(temp_fitness);

          scores.add(temp_fitness);
          DrawGraph.createAndShowGui(scores);
          index++;
          
        } else index++;
      }
      counter+=0.01;
      triangleList.get(i).mutate(triangleList.get(i).lastMutation, counter);
      //if (Attributes.debug) System.out.printf("delta Fitness %f \n", oldFitness-newFitness);
      oldFitness = newFitness;
      newFitness = fitCalc.calculateFitnessOfMutation(this);
    }
    counter = 1;
    if(generationCount%10==0)
    {
      setMainImage();
    }
  }
  public synchronized void setMainImage()
  {
    main.settingImage = true;
    main.setCurGenome(SwingFXUtils.toFXImage(getBufferedTriangle(this), null));
    main.settingImage = false;
  }
  
  public BufferedImage getBufferedTriangle(Genome genome)
  { 
    bigfx.setPaint(Color.WHITE);
    bigfx.fillRect(0, 0, bimg.getWidth(), bimg.getHeight());
    for(int i = 0; i<Attributes.numTriangles; i++)
    {
      bigfx.setPaint(new Color(
          (int) genome.triangleList.get(i).c[0] & 0xFF,
          (int) genome.triangleList.get(i).c[1] & 0xFF,
          (int) genome.triangleList.get(i).c[2] & 0xFF,
          (int) genome.triangleList.get(i).c[3] & 0xFF
          
          ));
      int[] tempX = new int[3];
      int[] tempY = new int[3];
      for(int j = 0; j<genome.triangleList.get(i).x.length; j++)
      {
        tempX[j] = (int) genome.triangleList.get(i).x[j];
        tempY[j] = (int) genome.triangleList.get(i).y[j];
      }
      bigfx.fillPolygon(tempX, tempY, 3);
    }
    return bimg;
  }
  
  public void clearTriangles()
  {
    //gfxR.setGlobalBlendMode(BlendMode.SRC_OVER);
    main.gfxR.clearRect(0, 0, main.controller.getCanvasRight().getWidth(), main.controller.getCanvasRight().getHeight());
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
  
  /*
   ============================
   Added getters and setters for 
   the current fitness
   ============================
   */
  public ArrayList getFitness(){
    return scores;
  }
  public void setFitness(double temp_fitness){
   // this.temp_fitness = temp_fitness;
   // ArrayList<Double> scores = new ArrayList<Double>();
    scores.add(temp_fitness);
    new DrawGraph(scores);
   // DrawGraph.createAndShowGui();
   // System.err.println("scores " + scores);
  }
}
