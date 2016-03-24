package triangles;

import java.util.ArrayList;

import engine.Attributes;

public class TriangleManager 
{
  ArrayList<TriangleObject> triangleList = new ArrayList<TriangleObject>();
  TriangleManager()
  {
    
  }
  
  public void createTriangles()
  {
    for(int i = 0; i < Attributes.numTriangles; i++)
    {
      TriangleObject a = new TriangleObject();
      triangleList.add(a);
    }
    
  }
  
  public void drawTriangles()
  {
    
  }
}
