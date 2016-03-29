package triangles;

import java.util.Random;

import engine.Attributes;

public class TriangleObject 
{
  public double[] x = new double[3];
  public double[] y = new double[3];
  public int r, g, b;
  public double a;
  private Random rand = new Random();
  
  TriangleObject(int x1, int x2, int x3, int y1, int y2, int y3, int r, int g, int b, double a)
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
    this.x[0] = rand.nextInt(Attributes.imageWidth);
    this.x[1] = rand.nextInt(Attributes.imageWidth);
    this.x[2] = rand.nextInt(Attributes.imageWidth);
    this.y[0] = rand.nextInt(Attributes.imageHeight);
    this.y[1] = rand.nextInt(Attributes.imageHeight);
    this.y[2] = rand.nextInt(Attributes.imageHeight);
    this.r = rand.nextInt(255);
    this.g = rand.nextInt(255);;
    this.b = rand.nextInt(255);
    this.a = rand.nextDouble();
  }
}
