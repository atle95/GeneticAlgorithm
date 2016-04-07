package core;

/**
 * 
 * @author Chris Sanchez
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
