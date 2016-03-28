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
  
   static BufferedImage img = null;

  //TODO get the fitness of the image
  public void getOriginalImageFitness(){}
  
  //TODO Get the pixels
  public static void getPixels() throws IOException
  {
    img = ImageIO.read(new File("GeneticAlgorithm/Resources/Images/monalisa.png"));
    // Getting pixel color by position x and y
    
    int w = img.getWidth();
    int h = img.getHeight();
    System.out.println("width, height: " + w + ", " + h);
 
    for (int i = 0; i < h; i++)
    {
      for (int j = 0; j < w; j++)
      {
        int pixel = img.getRGB(j, i);
        
        System.out.println("");
        
        //Skip every fourth pixel
        if (i % 4 == 0)
        {
          int red = (pixel & 0x00ff0000) >> 16;
          int green = (pixel & 0x0000ff00) >> 8;
          int blue = pixel & 0x000000ff;
          System.out.println(red + " " + green + " " + blue);
        }
      }
    }
  }
  
  //TODO calculate the fitness of the color
  public void calculateFitnessOfMutation(){}
  
}
