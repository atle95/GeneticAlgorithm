package triangles;

import java.util.ArrayList;
import java.util.Random;
import engine.Attributes;
import gui.Gui;

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
    triangleList.get(i).mutate(random.nextInt(20));
    int counter = 0;
    double newFitness = gui.fitCalc.calculateFitnessOfMutation();
    double oldFitness = gui.fitCalc.getFitness();
    System.out.printf("old fitness: %f new fitness: %f\n", oldFitness, newFitness);
    while (newFitness > oldFitness)
    {
      gui.fitCalc.setFitness(newFitness);
      oldFitness = newFitness;
      newFitness = gui.fitCalc.calculateFitnessOfMutation();
      counter++;
      System.out.printf("Mutating Triangle %d, %d times", i, counter);
      triangleList.get(i).mutate(triangleList.get(i).lastMutation);
    }
    counter = 0;
  }
  
  
  
}
