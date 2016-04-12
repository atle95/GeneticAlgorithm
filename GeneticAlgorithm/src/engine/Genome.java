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
  public BufferedImage bimg = new BufferedImage(Attributes.imageWidth, Attributes.imageHeight,
      BufferedImage.TYPE_INT_ARGB);
  Graphics2D bigfx = bimg.createGraphics();
  public double fitness;
  public FitnessCalculator fitCalc;
  public int generationCount = 0;
  private int lastPercentageFitness = 0;
  private int mutation = -1;
  private double adaptiveRate = 2;

  private double temp_fitness;
  private static double best_fitness = 0;
  private static int index = 0;
  
  public static ArrayList<Double> fitnessPlot = new ArrayList<Double>();
  public static ArrayList<Integer> indexPlot = new ArrayList<Integer>();
  private double[] weightDistribution = new double[20];

  
  
  public Genome(){}
  
  public Genome(Main main)
  {
    this.main = main;
    bigfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    initializeTriangles();
    main.setCurGenome(SwingFXUtils.toFXImage(this.bimg, null));
    fitCalc = new FitnessCalculator(this.main);
    for (int i =0; i<weightDistribution.length; i++)
    {
        weightDistribution[i] = 1.0;
    }

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

    main.numGenerations++;
    
    int currentTriangle = main.random.nextInt(triangleList.size());
    double sum = 0;
    for (double j : weightDistribution)
    {
        sum += j;
    }
    
    double temp = main.random.nextDouble()*sum;
    for (int k = 0; k < weightDistribution.length; ++k)
    {
        temp -= weightDistribution[k];
        if(temp <= 0.0d)
        {  mutation = k;
            break;
        }
    }
//    int mutation = main.random.nextInt(20);
    
    double percentageFitness = 0;
    double counter = 1;
    
    //Checks Given genome before and after mutation
    double oldFitness = fitCalc.calculateFitnessOfMutation(this);
    triangleList.get(currentTriangle).mutate(mutation, counter);
    double newFitness = fitCalc.calculateFitnessOfMutation(this);
    double percentageDeltaFitness = Math.abs(oldFitness-newFitness)/main.searchSize;

    percentageFitness = 100-newFitness/main.searchSize;
//  if (Attributes.debug && (generationCount%10==0))
//  { for(int i = 0; i<weightDistribution.length;i++)
//    { System.out.printf("[%2.2f]", weightDistribution[i]);
//    }
//  System.out.printf("%n");
////    System.out.printf("Mutating Triangle %3d, current fitness: %2.2f%% %n", currentTriangle, percentageFitness);
//  }
    if(oldFitness < newFitness)
    {
      //decrease probability of selecting again, and revert changes
      weightDistribution[mutation]*=(1-percentageDeltaFitness);
      
      
      
//      //This will be for the linegraph
//      if (percentageFitness > best_fitness)
//      {
//        best_fitness = percentageFitness;
//      }
//      if (index % 25 == 0)
//      {
//        System.err.println("index " + index);
//        setIndex(index);
//        setFitness(percentageFitness);
//        index++;
//        
//      } else index++;
      
      
      if (triangleList.get(currentTriangle).lastMutation %2 == 0)
      {
        triangleList.get(currentTriangle).mutate(triangleList.get(currentTriangle).lastMutation+1, counter);
      }
      else
      {
        triangleList.get(currentTriangle).mutate(triangleList.get(currentTriangle).lastMutation-1, counter);
      }
      counter/=adaptiveRate;
    }//End top level if statement
    
//    if(oldFitness == newFitness)
//    {
//      triangleList.remove(triangleList.get(i));
//      triangleList.add(new TriangleObject(main.random, i));
//    }
    while (newFitness < oldFitness)
    {
      
      //This will be for the linegraph
      if (percentageFitness > best_fitness)
      {
        best_fitness = percentageFitness;
        fitnessPlot.add(best_fitness);
      }
      if (index % 25 == 0)
      {
        System.err.println("index " + index);
//        setIndex(index);
//        setFitness(percentageFitness);
        indexPlot.add(index);
        index++;
        
      } else index++;
      
      weightDistribution[mutation]*=(1+percentageDeltaFitness);
      counter*=adaptiveRate;
      triangleList.get(currentTriangle).mutate(triangleList.get(currentTriangle).lastMutation, counter);
      
      oldFitness = newFitness;
      newFitness = fitCalc.calculateFitnessOfMutation(this);
    }
    counter = 1;
    if(generationCount%10==0)
    {
      //UNTESTED
      main.currFitness = percentageFitness;
      setMainImage();
      if(main.greatestFitness < percentageFitness)
      {
        main.greatestFitness = percentageFitness;
      }
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
  
  /*
  ============================
  Added getters and setters for 
  the current fitness
  ============================
  */
 public static double getFitness(){
   return best_fitness;
 }
 
 public static ArrayList<Double> getFitnessArray(){
   return fitnessPlot;
 }
 
 public static double getIndex(){
   return index;
 }
 
 public static ArrayList<Integer> getIndexArray(){
   return indexPlot;
 }
// public void setFitness(double temp_fitness){
//  // scores.add(temp_fitness);
////   new DrawGraph(scores);
//   this.temp_fitness = temp_fitness;
// }
// 
// public void setIndex(int index){
//   this.index = index;
// }
  
}