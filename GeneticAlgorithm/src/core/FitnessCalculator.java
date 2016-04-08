package core;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import engine.Attributes;
import engine.Genome;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.File;
import gui.Main;

/**
 * 
 * @author Chris Sanchez
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
//  private double error;

  public FitnessCalculator()
  {
    
  }

  public FitnessCalculator(Main gui)
  {
    this.main = gui;
  }


  // TODO get the fitness of the image
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

  // TODO Get the pixels of original image
  public void getPixelsFromOriginalImage() throws IOException
  {
    img = ImageIO.read(new File("Resources/Images/monalisa.png"));

    main.drawCurImage(main.gfxL, SwingFXUtils.toFXImage(img, null));
    int w = img.getWidth();
    int h = img.getHeight();

    //System.out.println("width, height: " + w + ", " + h);

    for (int i = 0; i < w; i++)
    {
      for (int j = 0; j < h; j++)
      {
        int pixel = img.getRGB(i, j);

      //  sourcePixels[i][j] = pixel;

        int red   = (pixel & 0x00ff0000) >> 16;
        int green = (pixel & 0x0000ff00) >> 8;
        int blue  = pixel & 0x000000ff;

        Colors color1 = new Colors(red, green, blue);
        
        sourcePixels[i][j] = color1;

        // System.out.println(red + " " + green + " " + blue);
      }
    }
  }

  /**
   * 
   * @param triangleImage
   */
  public void getPixelsFromRightCanvas(BufferedImage triangleImage)
  {

    int red,green,blue,pixel;

    for (int i = 0; i < width; i++)
    {
      for (int j = 0; j < height; j++)
      {
       pixel = triangleImage.getRGB(i, j);
       
       red   = (pixel & 0x00ff0000) >> 16;
       green = (pixel & 0x0000ff00) >> 8;
       blue  = pixel & 0x000000ff;
       
       Colors color2 = new Colors(red, green, blue);
       
       triangleCanvas[i][j] = color2;

       // System.out.println(red + " " + green + " " + blue);
      }
    }
  }
  
/**
 * 
 * @return - fitness
 */
  public double calculateFitnessOfMutation(Genome genome)
  {
    double error = 0;
    try 
    {
      getPixelsFromOriginalImage();
      getPixelsFromRightCanvas(genome.getBufferedTriangle(genome));
    } catch (IOException e) 
    {
      e.printStackTrace();
    }
    PixelWriter pixWriter = this.main.gfxF.getPixelWriter();
    
    for (int x = 0; x < width; x++)
    {
      for (int y = 0; y < height; y++)
      {

        Colors c1 = triangleCanvas[x][y];
        Colors c2 = sourcePixels[x][y];

        double pixelError = GetColorFitness(c1, c2);

        error += pixelError;
        int drawError = (int) Math.sqrt(pixelError);
//        int drawError = (int) ((255/195075) * (pixelError));
        if(drawError < 255)
        {
          //pixWriter.setColor(x,y,Color.rgb(drawError,drawError,drawError));
        }
        else
        {
          //pixWriter.setColor(x,y,Color.rgb(255,0,0));
          //System.out.printf("%d\n",drawError);
        }
      }
     // fitness = 1 - error / (width * height);
    }
    //System.out.println(error);
    return error;  
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

  public double getFitness() {
    return fitness;
  }

  public void setFitness(double fitness) {
    this.fitness = fitness;
  }

}