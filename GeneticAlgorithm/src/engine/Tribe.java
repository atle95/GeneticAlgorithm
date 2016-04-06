package engine;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.concurrent.CyclicBarrier;

import gui.Main;
import javafx.embed.swing.SwingFXUtils;

public class Tribe implements Runnable 
{
  BufferedImage triangleImage = new BufferedImage(Attributes.imageWidth, Attributes.imageHeight, BufferedImage.TYPE_INT_ARGB);
  Graphics2D bigfx = triangleImage.createGraphics();
  private Main main;
  Genome genome;
  
  public Tribe(CyclicBarrier barrier, Main main)
  {
    this.main = main;
    bigfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    genome = new Genome(this.main);
  }
  
  @Override
  public void run() 
  {
    int i = 0;
    while(i < 40000)
    {
      genome.mutateTriangle();
      main.drawCurImage(main.gfxR, SwingFXUtils.toFXImage(genome.bimg, null));
      i++;
    }
    System.out.println("done");
  }

}
