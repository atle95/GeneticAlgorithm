package engine;

import java.util.Arrays;
import java.util.Random;

/**
 * 
 * @author Atle Olson
 *
 */
public class TriangleObject 
{
  public double[] x = new double[3];
  public double[] y = new double[3];
  public byte[] c = new byte[4];
  public int lastMutation;
  public int magnitude = 1;
  private double opacityMagnitude = 0.001;
  
  TriangleObject(int x1, int x2, int x3, int y1, int y2, int y3, byte r, byte g, byte b, byte a)
  {
    this.x[0] = x1;
    this.x[1] = x2;
    this.x[2] = x3;
    this.y[0] = y1;
    this.y[1] = y2;
    this.y[2] = y3;
    this.c[0] = r;
    this.c[1] = g;
    this.c[2] = b;
    this.c[3] = a;
  }

  TriangleObject(Random rand)
  {
    this.x[0] = rand.nextInt(Attributes.imageWidth);
    this.x[1] = rand.nextInt(Attributes.imageWidth);
    this.x[2] = rand.nextInt(Attributes.imageWidth);
    this.y[0] = rand.nextInt(Attributes.imageHeight);
    this.y[1] = rand.nextInt(Attributes.imageHeight);
    this.y[2] = rand.nextInt(Attributes.imageHeight);
    rand.nextBytes(c);
  }
  
  public void mutate(int mutation, double iteration)
  {
    switch(mutation)
    {
      case 0: if(this.x[0]+magnitude*iteration<=Attributes.imageWidth) this.x[0]+=magnitude*iteration;
            else this.x[0]-=magnitude*iteration;
      break;
      case 1: if(this.x[1]+magnitude*iteration<=Attributes.imageWidth) this.x[1]+=magnitude*iteration;
            else this.x[1]-=magnitude*iteration;
      break;
      case 2: if(this.x[2]+magnitude*iteration<=Attributes.imageWidth) this.x[2]+=magnitude*iteration;
            else this.x[2]-=magnitude*iteration;
      break;
      case 3: if(this.x[0]-magnitude*iteration>=0) this.x[0]-=magnitude*iteration;
            else this.x[0]+=magnitude*iteration;
      break;
      case 4: if(this.x[1]-magnitude*iteration>=0) this.x[1]-=magnitude*iteration;
            else this.x[1]+=magnitude*iteration;
      break;
      case 5: if(this.x[2]-magnitude*iteration>=0) this.x[2]-=magnitude*iteration;
            else this.x[2]+=magnitude*iteration;
      break;
      case 6: if(this.y[0]+magnitude*iteration<=Attributes.imageHeight) this.y[0]+=magnitude*iteration;
            else this.y[0]-=magnitude*iteration;
      break;
      case 7: if(this.y[1]+magnitude*iteration<=Attributes.imageHeight) this.y[1]+=magnitude*iteration;
            else this.y[1]-=magnitude*iteration;
      break;
      case 8: if(this.y[2]+magnitude*iteration<=Attributes.imageHeight) this.y[2]+=magnitude*iteration;
            else this.y[2]-=magnitude;
      break;
      case 9: if(this.y[0]-magnitude*iteration>=0) this.y[0]-=magnitude*iteration;
            else this.y[0]+=magnitude*iteration;
      break;
      case 10: if(this.y[1]-magnitude*iteration>=0) this.y[1]-=magnitude*iteration;
             else this.y[1]+=magnitude;
      break;
      case 11: if(this.y[2]-magnitude*iteration>=0) this.y[2]-=magnitude*iteration;
             else this.y[2]+=magnitude*iteration;
      break;
      case 12: if(this.c[0]+magnitude*iteration<=255) this.c[0]+=magnitude*iteration;
             else this.c[0]-=magnitude*iteration;
      break;
      case 13: if(this.c[1]+magnitude*iteration<=255) this.c[1]+=magnitude*iteration;
             else this.c[1]+=magnitude;
      break;
      case 14: if(this.c[2]+magnitude*iteration<=255) this.c[2]+=magnitude*iteration;
             else this.c[2]-=magnitude*iteration;
      break;
      case 15: if(this.c[0]-magnitude*iteration>=0) this.c[0]-=magnitude*iteration;
             else this.c[0]+=magnitude*iteration;
      break;
      case 16: if(this.c[1]-magnitude*iteration>=0) this.c[1]-=magnitude*iteration;
             else this.c[1]+=magnitude*iteration;
      break;
      case 17: if(this.c[2]-magnitude>=0) this.c[2]-=magnitude*iteration;
             else this.c[2]+=magnitude*iteration;
      break;
      case 18: if(this.c[3]-opacityMagnitude*iteration>=0.0) this.c[3]-=opacityMagnitude*iteration;
             else this.c[3]+=opacityMagnitude*iteration;
      break;
      case 19: if(this.c[3]+opacityMagnitude*iteration<=1.0) this.c[3]+=opacityMagnitude*iteration;
             else this.c[3]-=opacityMagnitude*iteration;
      break;
    }
//    if(this.c[0]>255 || this.c[0]<0 ||this.c[1]>255 || this.c[1]<0 ||this.c[2]>255 || this.c[2]<0)
//    {
//      System.out.printf("x[0]%03.0f x[1]%03.0f x[2]%03.0f y[0]%03.0f y[1]%03.0f y[2]%03.0f r%03d g%03d b%03d a%f\n", this.x[0], this.x[1], this.x[2], this.y[0], this.y[1], this.y[2], this.c[0], this.c[1], this.c[2], this.c[3]);
//    }
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
