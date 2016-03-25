package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class GuiControls extends BorderPane
{
  @FXML private Button button1, button2, button3, button4, button5, button6, button7, button8;
  @FXML private Button buttonL1, buttonL2, buttonL3, buttonL4, buttonL5;
  @FXML private Canvas canvasLeft;
  @FXML private Canvas canvasRight;
  @FXML private ImageView vangogh;
  
  public GuiControls() 
  {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gui.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);
    try
    {
      fxmlLoader.load();            
    }
    catch (IOException exception)
    {
      throw new RuntimeException(exception);
    }
  }
  
  @FXML
  protected void button(ActionEvent event) 
  {
    if(event.getSource() == button1)
    {
      System.out.println("Button 1");
    }
    else if(event.getSource() == button2)
    {
      System.out.println("Button 2");
    }
    else if(event.getSource() == button3)
    {
      System.out.println("Button 3");
    }
    else if(event.getSource() == button4)
    {
      System.out.println("Button 4");
    }
    else if(event.getSource() == button5)
    {
      System.out.println("Button 5");
    }
    else if(event.getSource() == button6)
    {
      System.out.println("Button 6");
    }
    else if(event.getSource() == button7)
    {
      System.out.println("Button 7");
    }
    else if(event.getSource() == button8)
    {
      System.out.println("Button 8");
    }
  }
  
  @FXML
  protected void imageButton(ActionEvent event)
  {
    if(event.getSource() == buttonL1)
    {
      System.out.println("Left Button 1");
    }
    else if(event.getSource() == buttonL2)
    {
      System.out.println("Left Button 2");
    }
    else if(event.getSource() == buttonL3)
    {
      System.out.println("Left Button 3");
    }
    else if(event.getSource() == buttonL4)
    {
      System.out.println("Left Button 4");
    }
    else if(event.getSource() == buttonL5)
    {
      System.out.println("Left Button 5");
    }
    
  }
 
  @FXML
  protected void slider(ActionEvent event)
  {
    
  }
  
  public Canvas getCanvasRight()
  {
    return this.canvasRight;
  }

  public Canvas getCanvasLeft() 
  {
    return this.canvasLeft;
  }
}
