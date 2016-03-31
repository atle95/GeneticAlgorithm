package triangles;

import java.util.ArrayList;
import java.util.Random;
import engine.Attributes;

/**
 * 
 * @author Atle Olson
 *
 */
public class TriangleManager 
{
  public ArrayList<TriangleObject> triangleList = new ArrayList<TriangleObject>();
  Random random = new Random(Attributes.seed);
  
  public TriangleManager()
  {
    
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
    for(int i = 0; i < Attributes.numTriangles; i++)
    {
      triangleList.get(i).mutate(random.nextInt(19));
    }
    
  }
  
  
  
}
