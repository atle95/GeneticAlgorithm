package engine;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import gui.Gui;
import javafx.embed.swing.SwingFXUtils;

public class Tribe implements Runnable 
{
  BufferedImage triangleImage = new BufferedImage(Attributes.imageWidth, Attributes.imageHeight, BufferedImage.TYPE_INT_ARGB);
  Graphics2D bigfx = triangleImage.createGraphics();
  private Gui gui;
  
  public Tribe(Gui gui)
  {
    this.gui = gui;
    bigfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
  }
  
  @Override
  public void run() 
  {
    //gui.clearTriangles();
    while(!gui.paused)
    {
      gui.triangleManager.mutateTriangle();
     // gui.drawCurImage(gui.gfxR, SwingFXUtils.toFXImage(gui.bimg, null));
    }
  }

}
