package core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.Attributes;
import engine.Genome;
import javafx.scene.image.Image;

/**
 * 
 * @author Chris Sanchez
 * This class reads in the rgb values of
 * The source image (ex. Mona Lisa) and compares
 * it to the generated triangle image. When the
 * corresponding pixels are similar in color 
 * value, a high fitness percentage is 
 * calculated, otherwise a low fitness value
 * is calculated. This process helps during
 * hill climbing because the genetic algorithm 
 * needs to know if a mutation is improving the 
 * results, or not.
 *
 */
public class FitnessCalculator
{
  private Main main;
  
  int width = Attributes.imageWidth;
  int height = Attributes.imageHeight;
  
  private Colors[][] triangleCanvas = new Colors[width][height];
  private Colors[][] sourcePixels = new Colors[width][height];

  
  static BufferedImage imageRight = null;
  static BufferedImage img = null;
  static Image image = null;
  private double fitness;

  public FitnessCalculator()
  {
    
  }

  public FitnessCalculator(Main gui)
  {
    this.main = gui;
  }

  /**
   * Calls the helper methods that read in the pixel data
   * from the images
   */
  public void getOriginalImageFitness(Genome genome)
  {
    try
    {
      getPixelsFromOriginalImage();
      getPixelsFromRightCanvas(genome.getBufferedTriangle(genome));
    } 
    catch (IOException e) 
    {
    }
  }

  /**
   * 
   * @throws IOException
   * This method reads in the pixels from the source image.
   * The red, green, blue values are then passed to the 
   * Colors constructor to make a Colors object that will 
   * then be used to compare the fitness
   */
  public void getPixelsFromOriginalImage() throws IOException
  {
    
    try 
    {
      img = ImageIO.read(new File("Resources/Images/monalisa.png"));

      int w = img.getWidth();
      int h = img.getHeight();

      for (int i = 0; i < w; i++)
      {
        for (int j = 0; j < h; j++)
        {
            int pixel = img.getRGB(i, j);

            int red = (pixel & 0x00ff0000) >> 16;
            int green = (pixel & 0x0000ff00) >> 8;
            int blue = pixel & 0x000000ff;

            Colors color1 = new Colors(red, green, blue);

            sourcePixels[i][j] = color1;
          }
        }
    } 
    catch (IOException e) 
    {
      e.printStackTrace();
    }
  }

  /**
   * 
   * @param triangleImage
   * This method reads the pixels from the generated triangle 
   * image
   */
  public void getPixelsFromRightCanvas(BufferedImage triangleImage)
  {

    int red,green,blue,pixel;

    for (int i = 0; i < width; i++)
    {
      for (int j = 0; j < height; j++)
      {
          pixel = triangleImage.getRGB(i, j);

          red = (pixel & 0x00ff0000) >> 16;
          green = (pixel & 0x0000ff00) >> 8;
          blue = pixel & 0x000000ff;

          Colors color2 = new Colors(red, green, blue);

          triangleCanvas[i][j] = color2;
      }
    }
  }
  
/**
 * 
 * @return - fitness
 * This method maps the rgb values from the images to
 * a two-dimensional array. These values are used to 
 * construct color objects which are then used as 
 * components for comparing the fitness
 */
  public double calculateFitnessOfMutation(Genome genome)
  {
    double error = 0;
    try 
    {
      getPixelsFromOriginalImage();
      getPixelsFromRightCanvas(genome.getBufferedTriangle(genome));
    
    for (int x = 0; x < width; x++)
    {
      for (int y = 0; y < height; y++)
      {

        Colors c1 = triangleCanvas[x][y];
        Colors c2 = sourcePixels[x][y];

        double pixelError = GetColorFitness(c1, c2);

        error += pixelError;
      }
    }
    } catch (IOException e) 
    {
      e.printStackTrace();
    }
    return error;  
  }
  
  /**
   * 
   * Sum up the difference between the pixel values of the source image and new image
   * 
   */
  public double GetColorFitness(Colors c1, Colors c2)
  {
      double r = c1.getRed()   - c2.getRed();
      double g = c1.getGreen() - c2.getGreen();
      double b = c1.getBlue()  - c2.getBlue();

      return r*r + g*g + b*b;
  }

  /**
   * 
   * @return fitness
   */
  public double getFitness() {
    return fitness;
  }

  /**
   * 
   * @param fitness
   */
  public void setFitness(double fitness) {
    this.fitness = fitness;
  }
}