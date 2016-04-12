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
//  private int lastPercentageFitness = 0;
  private int mutation = -1;
  private double adaptiveRate = 2;

//  private double temp_fitness;
  private static double best_fitness = 0;
  private static int index = 0;
  
  public static ArrayList<Double> fitnessPlot = new ArrayList<Double>();
  public static ArrayList<Integer> indexPlot = new ArrayList<Integer>();
  private double[] weightDistribution = new double[20];

  
  
  public Genome(){}
  
  /**
   * Genome Constructor
   * 
   * @param main
   *          The main object reference to access gui controls
   */
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
  
  /**
   * Initializes a number of triangles equal to Attributes.maxTriangles
   * 
   */
  public void initializeTriangles()
  {
    for(int i = 0; i < Attributes.maxTriangles; i++)
    {
      TriangleObject member = new TriangleObject(main.random);
      triangleList.add(member);
    }
  }
  
  /**
   * HillClimbing Logic
   * 
   */
  public synchronized void mutateTriangle()
  {
    main.numGenerations++;
    int currentTriangle = main.random.nextInt(triangleList.size());
    double sum = 0;
    double temp = main.random.nextDouble()*sum;
    double counter = 1;
    
    //Heuristic weighting logic
    for (double j : weightDistribution)
    { 
      sum += j;
    }
    for (int k = 0; k < weightDistribution.length; ++k)
    { 
      temp -= weightDistribution[k];
      if(temp <= 0.0d)
      { 
        mutation = k;
        break;
      }
    }
    
    //Checks Given genome before and after mutation
    double oldFitness = fitCalc.calculateFitnessOfMutation(this);
    triangleList.get(currentTriangle).mutate(mutation, counter);
    double newFitness = fitCalc.calculateFitnessOfMutation(this);
    
    double percentageDeltaFitness = Math.abs(oldFitness-newFitness)/main.searchSize;
    double percentageFitness = 100-newFitness/main.searchSize;
    
    if(Attributes.debug&&main.numGenerations == 1)
    {
      System.out.printf("%2.2f",percentageFitness);
    }
    if(oldFitness < newFitness)
    { //decrease probability of selecting again, and revert changes
      weightDistribution[mutation]*=(1-percentageDeltaFitness);
      counter/=adaptiveRate;
      if (triangleList.get(currentTriangle).lastMutation %2 == 0)
      {
        triangleList.get(currentTriangle).mutate(triangleList.get(currentTriangle).lastMutation+1, counter);
      }
      else
      {
        triangleList.get(currentTriangle).mutate(triangleList.get(currentTriangle).lastMutation-1, counter);
      }
    }
    else
    { //increase probability of selecting again, and repeat changes
      while (newFitness < oldFitness)
      { 
        if (percentageFitness > best_fitness)
        { best_fitness = percentageFitness;
          fitnessPlot.add(best_fitness);
        }
        if (index % 25 == 0)
        { 
          System.err.println("index " + index);
          indexPlot.add(index);
          index++;
        } 
        else index++;
        
        weightDistribution[mutation]*=(1+percentageDeltaFitness);
        counter*=adaptiveRate;
        
        oldFitness = newFitness;
        triangleList.get(currentTriangle).mutate(triangleList.get(currentTriangle).lastMutation, counter);
        newFitness = fitCalc.calculateFitnessOfMutation(this);
      }
      counter = 1;
      if(generationCount%10==0)
      {
        main.currFitness = percentageFitness;
        setMainImage();
        if(main.greatestFitness < percentageFitness)
        {
          main.greatestFitness = percentageFitness;
        }
      }
    }
  }

  /**
   * Updates Gui with The current genome population
   * 
   */
  public synchronized void setMainImage()
  {
    main.settingImage = true;
    main.setCurGenome(SwingFXUtils.toFXImage(getBufferedTriangle(this), null));
    main.settingImage = false;
  }
  
  /**
   * Get BufferedImage
   * 
   * @param genome
   *          The genome object to get a buffered image representation of
   * @return BufferedImage
   *          The buffered image represantation of the genome object
   */
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
  
  /**
   * Get Fitness
   * 
   * @return best_fitness
   *            The best fitness so far
   */
  public static double getFitness()
  {
    return best_fitness;
  }
 
  /**
   * Get Fitness Array
   * 
   * @return fitnessPlot
   */
  public static ArrayList<Double> getFitnessArray()
  {
    return fitnessPlot;
  }
 
  /**
   * Get Index
   * 
   * @return index
   */
  public static double getIndex()
  {
    return index;
  }
 
  public static ArrayList<Integer> getIndexArray()
  {
    return indexPlot;
  }
  
}