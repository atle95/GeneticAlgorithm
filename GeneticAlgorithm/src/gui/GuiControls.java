package gui;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.Attributes;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author Atle Olson
 *
 */
public class GuiControls extends BorderPane
{
  @FXML private Button button1, button2, button3, button4, button5, button6, button7, button8;
  @FXML private Button monaLisaButton, poppyFieldsButton, greatWaveButton, vanGoghButton, mcEscherButton;
  @FXML private Canvas canvasLeft, canvasRight, fitnessCanvas;
  @FXML private Label currFit, fitPerSec, totalPop, totalHill, genPerSec, avgGenSec, totalRun;
  @FXML Button srcOver, srcAtop, add, multiply, screen, overlay, darken, lighten, colorDodge, colorBurn, hardLight, softLight, difference, exclusion, redBlend, blueBlend, greenBlend, clear, mutate;
  @FXML Slider numTrianglesSlider, currentTriangleSlider, slider3;
  private Image monalisa    = new Image("File:Resources/Images/monalisa.png");
  private Image poppyfields = new Image("File:Resources/Images/poppyfields.png");
  private Image greatwave   = new Image("File:Resources/Images/greatwave.png");
  private Image vangogh     = new Image("File:Resources/Images/vangogh.png");
  private Image mcescher    = new Image("File:Resources/Images/mcescher.png");
  private Gui gui;
  
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
    numTrianglesSlider.valueProperty().addListener(new ChangeListener<Number>()
    {
      public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) 
      {
        System.out.printf("Num Triangles: %d\n", new_val.intValue());
        Attributes.numTriangles = new_val.intValue();
        gui.drawCurImage(gui.gfxR, SwingFXUtils.toFXImage(gui.getBufferedTriangle(gui.triangleManager), null));
      }
    });
    numTrianglesSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>()
    {
      public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) 
      {
        if (!new_val) 
        {
          System.out.println("done");
          gui.drawCurImage(gui.gfxR, SwingFXUtils.toFXImage(gui.getBufferedTriangle(gui.triangleManager), null));
        }
      }
    });
    currentTriangleSlider.valueProperty().addListener(new ChangeListener<Number>()
    {
      public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) 
      {
        System.out.printf("Num Triangles: %d\n", new_val.intValue());
        Attributes.currentTriangle = new_val.intValue();
//        gui.drawTriangles();
      }
    });
    currentTriangleSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>()
    {
      public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) 
      {
        if (!new_val) 
        {
          System.out.println("done");
          gui.drawCurImage(gui.gfxR, SwingFXUtils.toFXImage(gui.getBufferedTriangle(gui.triangleManager), null));
        }
      }
    });
  
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
      gui.setCurImage(monalisa);
    }
    else if(event.getSource() == poppyFieldsButton)
    {
      gui.setCurImage(poppyfields);
    }
    else if(event.getSource() == greatWaveButton)
    {
      gui.setCurImage(greatwave);
    }
    else if(event.getSource() == vanGoghButton)
    {
      gui.setCurImage(vangogh);
    }
    else if(event.getSource() == mcEscherButton)
    {
      gui.setCurImage(mcescher);
    }
  }

  @FXML
  protected void blendModeButton(ActionEvent event)
  {
    Button btn = (Button) event.getSource();
    String id = btn.getId();
    //System.out.println(id);
    switch(id)
    {
    case "srcOver": gui.setBlendMode(BlendMode.SRC_OVER);
    break;
    case "srcAtop": gui.setBlendMode(BlendMode.SRC_ATOP);
    break;
    case "srcAdd": gui.setBlendMode(BlendMode.ADD);
    break;
    case "multiply": gui.setBlendMode(BlendMode.MULTIPLY);
    break;
    case "screen": gui.setBlendMode(BlendMode.SCREEN);
    break;
    case "overlay": gui.setBlendMode(BlendMode.OVERLAY);
    break;
    case "darken": gui.setBlendMode(BlendMode.DARKEN);
    break;
    case "lighten": gui.setBlendMode(BlendMode.LIGHTEN);
    break;
    case "colorDodge": gui.setBlendMode(BlendMode.COLOR_DODGE);
    break;
    case "colorBurn": gui.setBlendMode(BlendMode.COLOR_BURN);
    break;
    case "hardLight": gui.setBlendMode(BlendMode.HARD_LIGHT);
    break;
    case "softLight": gui.setBlendMode(BlendMode.SOFT_LIGHT);
    break;
    case "difference": gui.setBlendMode(BlendMode.EXCLUSION);
    break;
    case "exclusion": gui.setBlendMode(BlendMode.EXCLUSION);
    break;
    case "redBlend": gui.setBlendMode(BlendMode.RED);
    break;
    case "blueBlend": gui.setBlendMode(BlendMode.BLUE);
    break;
    case "greenBlend": gui.setBlendMode(BlendMode.GREEN);
    break;
    }
    gui.getBufferedTriangle(gui.triangleManager);
  }
  
  @FXML
  protected void clearButton()
  {
    gui.clearTriangles();
  }
  
  @FXML
  protected void mutateButton()
  {
//    gui.paused = !gui.paused;
    for(;;)
    {
      gui.triangleManager.mutateTriangle();
      gui.drawCurImage(gui.gfxR, SwingFXUtils.toFXImage(gui.getBufferedTriangle(gui.triangleManager), null));
    }
  }

  @FXML
  protected void saveButton()
  {
    File file = new File("atle.png");
    try 
    {
      ImageIO.write(SwingFXUtils.fromFXImage(gui.getSnapShot(getFitnessCanvas(), 0,0,Attributes.imageWidth,Attributes.imageHeight), null), "png", file);
    } catch (IOException e) 
    {
      e.printStackTrace();
    }
  }
  
  @FXML
  protected void slider(DragEvent event)
  {
    if(event.equals(DragEvent.DRAG_OVER))
    {
      System.out.println("Mouse Released");
    }
  }
  
  public Canvas getCanvasRight()
  {
    return this.canvasRight;
  }

  public Canvas getCanvasLeft() 
  {
    return this.canvasLeft;
  }

  public Canvas getFitnessCanvas()
  {
    return this.fitnessCanvas;
  }
}
