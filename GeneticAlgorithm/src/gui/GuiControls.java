package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class GuiControls extends BorderPane
{
  @FXML private Button button1, button2, button3, button4, button5, button6, button7, button8;
  @FXML private Button buttonL1, buttonL2, buttonL3, buttonL4, buttonL5;
  @FXML private Button monaLisaButton, poppyFieldsButton, greatWaveButton, vanGoghButton, mcEscherButton;
  @FXML private Canvas canvasLeft;
  @FXML private Canvas canvasRight;
  Image monalisa    = new Image("File:Resources/Images/monalisa.png");
  Image poppyfields = new Image("File:Resources/Images/poppyfields.png");
  Image greatwave   = new Image("File:Resources/Images/greatwave.png");
  Image vangogh     = new Image("File:Resources/Images/vangogh.png");
  Image mcescher    = new Image("File:Resources/Images/mcescher.png");
  GraphicsContext gfxL;
  double pictureWidth = 512;
  double pictureHeight = 512;
  Gui gui;
  
  public GuiControls(Gui gui) 
  {
    this.gui = gui;
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
    if(event.getSource() == monaLisaButton)
    {
      gui.drawCurImage(monalisa);
    }
    else if(event.getSource() == poppyFieldsButton)
    {
      gui.drawCurImage(poppyfields);
    }
    else if(event.getSource() == greatWaveButton)
    {
      gui.drawCurImage(greatwave);
    }
    else if(event.getSource() == vanGoghButton)
    {
      gui.drawCurImage(vangogh);
    }
    else if(event.getSource() == mcEscherButton)
    {
      gui.drawCurImage(mcescher);
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
