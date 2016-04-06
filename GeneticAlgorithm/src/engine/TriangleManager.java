package engine;

import java.util.ArrayList;
import java.util.Random;

import gui.Gui;
import javafx.embed.swing.SwingFXUtils;

/**
 * 
 * @author Atle Olson
 *
 */
public class TriangleManager 
{
  public ArrayList<TriangleObject> triangleList = new ArrayList<TriangleObject>();
  Random random = new Random();
  Gui gui;
  
  public TriangleManager(Gui gui)
  {
    this.gui = gui;
  }
  
  public void initializeTriangles()
  {
    for(int i = 0; i < Attributes.maxTriangles; i++)
    {
      TriangleObject member = new TriangleObject(random);
      triangleList.add(member);
    }
  }
  
  public void mutateTriangle()
  {
    int i = random.nextInt(triangleList.size());
    int mutation = random.nextInt(20);
    double oldFitness = gui.fitCalc.calculateFitnessOfMutation();
    //System.out.printf("mutation: %d: ", mutation);
    triangleList.get(i).mutate(mutation);
    double newFitness = gui.fitCalc.calculateFitnessOfMutation();
    int counter = 0;
    //System.out.printf("old fitness: %f new fitness: %f\n", oldFitness, newFitness);
    while (newFitness < oldFitness)
    {
//      if (counter == 0)
//      {
//        System.out.printf("Mutating Triangle %d, current fitness: %f \n", i, newFitness);
//      }
      //System.out.printf("iteration: %d \r",counter);
      counter++;
      triangleList.get(i).mutate(triangleList.get(i).lastMutation);
      System.out.printf("delta Fitness %f \n", oldFitness-newFitness);
      oldFitness = newFitness;
      newFitness = gui.fitCalc.calculateFitnessOfMutation();
    }
    if(counter > 0)
    {
            
      //System.out.printf("Number of iterations: %d \n", counter);
    }
    counter = 0;
  }
  
  
  
}
