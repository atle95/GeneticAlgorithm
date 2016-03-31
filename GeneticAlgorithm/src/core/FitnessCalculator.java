package core;

import java.awt.image.BufferedImage;
import triangles.TriangleManager;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import gui.Gui;

/*
 * The idea here is that pixels are going to be compared from 
 * the original image and then compared with the current
 * triangle combination per tribe.
 */
/**
 * 
 * @author Chris Sanchez
 *
 */
public class FitnessCalculator
{

  static Gui gui = new Gui();
  static TriangleManager tm = new TriangleManager();
  static BufferedImage img = null;

  
   
   //TODO get the fitness of the image
  public void getOriginalImageFitness(){}
  
  
  
  //TODO Get the pixels of original image
  public static void getPixelsFromOriginalImage() throws IOException
  {
    img = ImageIO.read(new File("Resources/Images/monalisa.png"));
    
    int w = img.getWidth();
    int h = img.getHeight();
    int [][] sourcePixels = new int [w][h];
    
    System.out.println("width, height: " + w + ", " + h);
 
    for (int i = 0; i < w; i++)
    {
      for (int j = 0; j < h; j++)
      {
        int pixel = img.getRGB(i, j);

        sourcePixels[i][j] = pixel;

        int red   = (pixel & 0x00ff0000) >> 16;
        int green = (pixel & 0x0000ff00) >> 8;
        int blue  = pixel & 0x000000ff;
        
        Colors color1 = new Colors(red,green,blue);
         
        // System.out.println(red + " " + green + " " + blue);
      }
    }
  }
  

  //TODO Get pixels of image from right side (triangles)
  public static void getPixelsFromRightCanvas()
  {
    int width = (int) TriangleCanvas.getGfxR().getCanvas().getWidth();
    int height = (int) TriangleCanvas.getGfxR().getCanvas().getHeight();
    
    for (int i = 0; i < width; i++)
    {
      for (int j = 0; j < height; j++)
      {
        // TODO - figure out how to get the pixels from the right panel
      }
    }
  }
  
  
  //TODO calculate the fitness of the color
  public static void calculateFitnessOfMutation(){
   // double height = TriangleCanvas.getGfxR().getCanvas().getHeight();
  }
  
  
  
  
  public static void mutationTest(){
    for(;;)
    {
      tm.mutateTriangle();
      gui.drawTriangles();
    }
  }
}
