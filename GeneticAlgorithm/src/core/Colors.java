package core;

/**
 * 
 * @author Chris Sanchez
 * 
 * Colors constructs objects of rgb
 * values that are taken from the pixels 
 * of the source and destination images
 * (i.e. the Mona Lisa Image and the 
 * triangle images)
 * 
 */
public class Colors
{

  private int red;
  private int green;
  private int blue;

  public Colors(){}
  
  public Colors(int red, int green, int blue)
  {
    this.setRed(red);
    this.setGreen(green);
    this.setBlue(blue);
  }

  public int getRed()
  {
    return red;
  }

  public void setRed(int red)
  {
    this.red = red;
  }

  public int getGreen()
  {
    return green;
  }

  public void setGreen(int green)
  {
    this.green = green;
  }

  public int getBlue()
  {
    return blue;
  }

  public void setBlue(int blue)
  {
    this.blue = blue;
  }
  
}
