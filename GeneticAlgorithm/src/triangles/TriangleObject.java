package triangles;

public class TriangleObject 
{
  int[] x, y;
  int r, g, b, a;
  
  TriangleObject(int x1, int y1, int x2, int y2, int x3, int y3, int r, int g, int b, int a)
  {
    
    this.x[0] = x1;
    this.x[1] = x2;
    this.x[2] = x3;
    this.y[0] = y1;
    this.y[1] = y2;
    this.y[2] = y3;
    this.r = r;
    this.g = g;
    this.b = b;
    this.a = a;
  }

  TriangleObject()
  {
    
  }
}
