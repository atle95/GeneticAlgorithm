package engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gui.Main;
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
  Main main;
  public BufferedImage bimg = new BufferedImage(Attributes.imageWidth, Attributes.imageHeight, BufferedImage.TYPE_INT_ARGB);
  Graphics2D bigfx = bimg.createGraphics();
  
  public Genome(Main main)
  {
    this.main = main;
    bigfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    initializeTriangles();
  }
  
  public void initializeTriangles()
  {
    for(int i = 0; i < Attributes.maxTriangles; i++)
    {
      TriangleObject member = new TriangleObject(main.random);
      triangleList.add(member);
    }
  }
  
  public void mutateTriangle()
  {
    int i = main.random.nextInt(triangleList.size());
    int mutation = main.random.nextInt(20);
    double oldFitness = main.fitCalc.calculateFitnessOfMutation();
    //main.setCurrFit("current Fitness: "+ oldFitness);
    //if (Attributes.debug) System.out.printf("mutation: %3d: ", mutation);
    triangleList.get(i).mutate(mutation);
    double newFitness = main.fitCalc.calculateFitnessOfMutation();
    int counter = 0;
    //if (Attributes.debug) System.out.printf("old fitness: %f new fitness: %f\n", oldFitness, newFitness);
    while (newFitness < oldFitness)
    {
      if (counter == 0 && Attributes.debug)
      {
        System.out.printf("Mutating Triangle %d, current fitness: %f \n", i, newFitness);
      }
      //if (Attributes.debug) System.out.printf("iteration: %d \r",counter);
      counter++;
      triangleList.get(i).mutate(triangleList.get(i).lastMutation);
      if (Attributes.debug) System.out.printf("delta Fitness %f \n", oldFitness-newFitness);
      oldFitness = newFitness;
      newFitness = main.fitCalc.calculateFitnessOfMutation();
    }
    if(counter > 0)
    {
      if (Attributes.debug) System.out.printf("Number of iterations: %d \n", counter);
    }
    counter = 0;
  }
  
  public BufferedImage getBufferedTriangle(Genome genome)
  { 
    for(int i = 0; i<Attributes.numTriangles; i++)
    {
      bigfx.setPaint(new Color(
          genome.triangleList.get(i).c[0] & 0xFF,
          genome.triangleList.get(i).c[1] & 0xFF,
          genome.triangleList.get(i).c[2] & 0xFF,
          genome.triangleList.get(i).c[3] & 0xFF
          
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
}
