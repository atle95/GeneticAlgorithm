package engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import core.FitnessCalculator;
import core.Main;
import javafx.embed.swing.SwingFXUtils;


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
  public double fitness;
  public FitnessCalculator fitCalc;
  public int generationCount = 0;
  
  
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
    if (Attributes.debug)
    {
      double temp = 100-newFitness/93394396;
      System.out.printf("Mutating Triangle %3d, current fitness: %2.2f%% %n", i, temp);
    }
    if(  oldFitness < newFitness)
    {
      if (triangleList.get(i).lastMutation %2 == 0)
      {
        triangleList.get(i).mutate(triangleList.get(i).lastMutation+1, counter);
      }
      else
      {
        triangleList.get(i).mutate(triangleList.get(i).lastMutation-1, counter);
      }
    }
//    if(oldFitness == newFitness)
//    {
//      triangleList.remove(triangleList.get(i));
//      triangleList.add(new TriangleObject(main.random, i));
//    }
    while (newFitness < oldFitness)
    {
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
  
  
}
