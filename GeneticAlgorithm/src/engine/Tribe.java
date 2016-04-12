package engine;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

import core.Main;

public class Tribe implements Runnable 
{
  final ReentrantLock rl = new ReentrantLock();
  private Main main;
  private int numGenomes = 1;
  private int tribe_id;
  public ArrayList<Genome> genomeList = new ArrayList<Genome>();
  BufferedImage triangleImage = new BufferedImage(Attributes.imageWidth, Attributes.imageHeight, BufferedImage.TYPE_INT_ARGB);
  Graphics2D bigfx = triangleImage.createGraphics();
  Genome genome;
  CyclicBarrier barrier;
  
  /*
   =====================
   Added tribe id to asssociate
   a specific job with a tribe
   =====================
   */
  
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