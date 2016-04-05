package core;

import java.awt.image.BufferedImage;
import triangles.TriangleManager;
import javax.imageio.ImageIO;

import engine.Attributes;

import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;
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
  private Gui gui;
  
  int width = Attributes.imageWidth;
  int height = Attributes.imageHeight;
  
  static TriangleManager tm = new TriangleManager();
  static BufferedImage img = null;
  static Image image = null;
  WritableImage img2 = null;
  private double error;
  private double fitness;

  public FitnessCalculator()
  {
  }

  public FitnessCalculator(Gui gui)
  {
    this.gui = gui;
  }


  // TODO get the fitness of the image
  public void getOriginalImageFitness()
  {
  }

  // TODO Get the pixels of original image
  public static void getPixelsFromOriginalImage() throws IOException
  {
    img = ImageIO.read(new File("GeneticAlgorithm/Resources/Images/monalisa.png"));

    int w = img.getWidth();
    int h = img.getHeight();
    int[][] sourcePixels = new int[w][h];

    System.out.println("width, height: " + w + ", " + h);

    for (int i = 0; i < w; i++)
    {
      for (int j = 0; j < h; j++)
      {
        int pixel = img.getRGB(i, j);

        sourcePixels[i][j] = pixel;

        int red = (pixel & 0x00ff0000) >> 16;
        int green = (pixel & 0x0000ff00) >> 8;
        int blue = pixel & 0x000000ff;

        Colors color1 = new Colors(red, green, blue);

        // System.out.println(red + " " + green + " " + blue);
      }
    }
  }

  // TODO Get pixels of image from right side (triangles)
  public void getPixelsFromRightCanvas()
  {

    Gui g = new Gui();

    int[][] rightCanvas = new int[width][height];

    img2 = g.getSnapShot(g.controller.getCanvasRight(), 0, 10, 100, 100);

    for (int i = 0; i < width; i++)
    {
      for (int j = 0; j < height; j++)
      {
        gui.drawCurImage(gui.gfxR, image);
        // TODO - figure out how to get the pixels from the right panel
        // save these values in 2d array newImagePixels[][]
        
      }
    }
  }
  
  // TODO calculate the fitness of the color
  public double calculateFitnessOfMutation()
  {
    // double height = TriangleCanvas.getGfxR().getCanvas().getHeight();
    for (int x = 0; x < width; x++)
    {
      for (int y = 0; y < height; y++)
      {
        // get pixels from each 2d array
        
        // Color c1 = rightCanvas[x][y]
        // Color c2 = sourcePixels[x][y]
        
        //double pixelError = GetColorFitness(c1, c2);
        
        // error += pixelError
      }
      fitness = 1 - error / (width * width);
    }
    return fitness;  
  }
  
  /**
   * 
   * Sum up the difference between the pixel values of the source image and new image
   */
  public double GetColorFitness(Colors c1, Colors c2)
  {
      double r = c1.getRed()   - c2.getRed();
      double g = c1.getGreen() - c2.getGreen();
      double b = c1.getBlue()  - c2.getBlue();

      return r*r + g*g + b*b;
  }

}
