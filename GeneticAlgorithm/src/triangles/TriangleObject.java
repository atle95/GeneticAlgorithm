package triangles;

import java.util.Arrays;
import java.util.Random;

import engine.Attributes;

public class TriangleObject 
{
  public double[] x = new double[3];
  public double[] y = new double[3];
  public int r, g, b;
  public double a;
  public int lastMutation;
  public int magnitude = 1;
  
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

  TriangleObject(Random rand)
  {
    this.x[0] = rand.nextInt(Attributes.imageWidth);
    this.x[1] = rand.nextInt(Attributes.imageWidth);
    this.x[2] = rand.nextInt(Attributes.imageWidth);
    this.y[0] = rand.nextInt(Attributes.imageHeight);
    this.y[1] = rand.nextInt(Attributes.imageHeight);
    this.y[2] = rand.nextInt(Attributes.imageHeight);
    this.r = rand.nextInt(255);
    this.g = rand.nextInt(255);
    this.b = rand.nextInt(255);
    this.a = rand.nextDouble();
  }
  
  public void mutate(int mutation)
  {
    switch(mutation)
    {
      case 0: if(this.x[0]+magnitude<Attributes.imageWidth) this.x[0]+=magnitude;
            else this.x[0]-=magnitude;
      break;
      case 1: if(this.x[1]+magnitude<Attributes.imageWidth) this.x[1]+=magnitude;
            else this.x[1]-=magnitude;
      break;
      case 2: if(this.x[2]+magnitude<Attributes.imageWidth) this.x[2]+=magnitude;
            else this.x[2]-=magnitude;
      break;
      case 3: if(this.x[0]-magnitude>0) this.x[0]-=magnitude;
            else this.x[0]+=magnitude;
      break;
      case 4: if(this.x[1]-magnitude>0) this.x[1]-=magnitude;
            else this.x[1]+=magnitude;
      break;
      case 5: if(this.x[2]-magnitude>0) this.x[2]-=magnitude;
            else this.x[2]+=magnitude;
      break;
      case 6: if(this.y[0]+magnitude<Attributes.imageHeight) this.y[0]+=magnitude;
            else this.y[0]-=magnitude;
      break;
      case 7: if(this.y[1]+magnitude<Attributes.imageHeight) this.y[1]+=magnitude;
            else this.y[1]-=magnitude;
      break;
      case 8: if(this.y[2]+magnitude<Attributes.imageHeight) this.y[2]+=magnitude;
            else this.y[2]-=magnitude;
      break;
      case 9: if(this.y[0]-magnitude>0) this.y[0]-=magnitude;
            else this.y[0]+=magnitude;
      break;
      case 10: if(this.y[1]-magnitude>0) this.y[1]-=magnitude;
             else this.y[1]+=magnitude;
      break;
      case 11: if(this.y[2]-magnitude>0) this.y[2]-=magnitude;
             else this.y[2]+=magnitude;
      break;
      case 12: if(this.r+magnitude<255) this.r+=magnitude;
             else this.r-=magnitude;
      break;
      case 13: if(this.g+magnitude<255) this.g+=magnitude;
             else this.r+=magnitude;
      break;
      case 14: if(this.b+magnitude<255) this.b+=magnitude;
             else this.b-=magnitude;
      break;
      case 15: if(this.r-magnitude>0) this.r-=magnitude;
             else this.r+=magnitude;
      break;
      case 16: if(this.g-magnitude>0) this.g-=magnitude;
             else this.g+=magnitude;
      break;
      case 17: if(this.b-magnitude>0) this.b-=magnitude;
             else this.b+=magnitude;
      break;
      case 18: if(this.a-0.01>0.0) this.a-=0.01;
             else this.a+=0.01;
      break;
      case 19: if(this.a+0.01<1.0) this.a+=0.01;
             else this.a-=0.01;
      break;
    }
//    if(this.r>=255 || this.r<0 ||this.g>=255 || this.g<0 ||this.b>=255 || this.b<0)
//    {
//      System.out.printf("x[0]%03.0f x[1]%03.0f x[2]%03.0f y[0]%03.0f y[1]%03.0f y[2]%03.0f r%03d g%03d b%03d a%f\n", this.x[0], this.x[1], this.x[2], this.y[0], this.y[1], this.y[2], this.r, this.g, this.b, this.a);
//    }
    if(this.r > 255)
    {
      this.r = 255;
    }
    lastMutation = mutation;
  }
  
  int[] getTriangleBoundingBox()
  {
    double[] tempX = this.x;
    double[] tempY = this.y;
    Arrays.sort(tempX);
    Arrays.sort(tempY);
    int[] output = {(int) tempX[0], (int) tempY[0], (int) tempX[2], (int) tempY[2]};
    return output;
  }
}
