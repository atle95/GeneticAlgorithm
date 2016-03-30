package core;

import javafx.scene.canvas.GraphicsContext;

/**
 * 
 * @author Experimental class, may not use later
 *
 */
public class TriangleCanvas
{
  private static GraphicsContext gfxR;

  public TriangleCanvas(){}
  
  public TriangleCanvas( GraphicsContext gfxR){
    this.setGfxR(gfxR);
  }

  public static GraphicsContext getGfxR()
  {
    return gfxR;
  }

  public static void setGfxR(GraphicsContext gfxR)
  {
    TriangleCanvas.gfxR = gfxR;
  }
}
