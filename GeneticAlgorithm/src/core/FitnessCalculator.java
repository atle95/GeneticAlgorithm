package core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * The idea here is that pixels are going to be compared from 
 * the original image and then compared with the current
 * triangle combination per tribe.
 */
public class FitnessCalculator
{

  //TODO get the fitness of the image
  public void getOriginalImageFitness(){}
  
  //TODO Get the pixels
  public void getPixels() throws IOException
  {
    File file= new File("your_file.jpg");
    BufferedImage image = ImageIO.read(file);
    // Getting pixel color by position x and y
    
    int w = image.getWidth();
    int h = image.getHeight();
    System.out.println("width, height: " + w + ", " + h);
 
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        System.out.println("x,y: " + j + ", " + i);
        int pixel = image.getRGB(j, i);
       // printPixelARGB(pixel);
        System.out.println("");

    
    
    int clr=  image.getRGB(i,j); 
    int  red   = (clr & 0x00ff0000) >> 16;
    int  green = (clr & 0x0000ff00) >> 8;
    int  blue  =  clr & 0x000000ff;
//    System.out.println("Red Color value = "+ red);
//    System.out.println("Green Color value = "+ green);
//    System.out.println("Blue Color value = "+ blue);
      }
    }
  }
  
  //TODO calculate the fitness of the color
  public void calculateFitnessOfMutation(){}
  
}
