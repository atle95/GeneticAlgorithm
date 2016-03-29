package triangles;

import java.util.ArrayList;
import java.util.Random;

import engine.Attributes;

public class TriangleManager 
{
  public ArrayList<TriangleObject> triangleList = new ArrayList<TriangleObject>();
  Random r = new Random();
  
  public TriangleManager()
  {
    
  }
  
  public void initializeTriangles()
  {
    for(int i = 0; i < Attributes.numTriangles; i++)
    {
      TriangleObject a = new TriangleObject();
      triangleList.add(a);
    }
  }
  
  
}
