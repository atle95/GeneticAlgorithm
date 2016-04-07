package engine;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

import gui.Main;

public class Tribe implements Runnable 
{
  BufferedImage triangleImage = new BufferedImage(Attributes.imageWidth, Attributes.imageHeight, BufferedImage.TYPE_INT_ARGB);
  Graphics2D bigfx = triangleImage.createGraphics();
  private Main main;
  Genome genome;
  private int numGenomes = 1;
  public ArrayList<Genome> genomeList = new ArrayList<Genome>();
  CyclicBarrier barrier;
  
  /*
   =====================
   Added tribe id to asssociate
   a specific job with a tribe
   =====================
   */
  private int tribe_id;
  
  public Tribe(CyclicBarrier barrier, Main main, int tribe_id)
  {
    this.barrier  = barrier;
    this.main     = main;
    this.tribe_id = tribe_id;
    
    bigfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
    
    for(int i = 0; i < numGenomes ; i++)
    {
      genome = new Genome(this.main);
      genomeList.add(genome);
    }
  }
  
  @Override
  public void run() 
  {
    int i = 0;
    while(i < 40000)
    {
      genome.mutateTriangle();
      //if(i % 100 == 0) main.drawCurImage(main.gfxR, SwingFXUtils.toFXImage(genome.bimg, null));
      i++;
    }
    System.out.println("done");
  }

}
