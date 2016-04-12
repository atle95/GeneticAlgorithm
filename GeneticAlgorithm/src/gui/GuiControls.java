package gui;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import core.Main;
import engine.Attributes;
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
  @FXML private Button monaLisaButton, poppyFieldsButton, greatWaveButton, vanGoghButton, mcEscherButton, Graph;
  @FXML private Canvas canvasLeft, canvasRight, fitnessCanvas;
  @FXML public Label currFit, fitPerSec, totalPop, totalHill, genPerSec, avgGenSec, totalRun;
  @FXML Button srcOver, srcAtop, add, multiply, screen, overlay, darken, lighten, colorDodge, colorBurn, hardLight, softLight, difference, exclusion, redBlend, blueBlend, greenBlend, clear, mutate;
  @FXML Slider numTrianglesSlider, currentTriangleSlider, slider3;
  private Image monalisa    = new Image("File:Resources/Images/monalisa.png");
  private Image poppyfields = new Image("File:Resources/Images/poppyfields.png");
  private Image greatwave   = new Image("File:Resources/Images/greatwave.png");
  private Image vangogh     = new Image("File:Resources/Images/vangogh.png");
  private Image mcescher    = new Image("File:Resources/Images/mcescher.png");
  private Main main;
  
  public GuiControls(Main main) 
  {
    this.main = main;
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
//    numTrianglesSlider.valueProperty().addListener(new ChangeListener<Number>()
//    {
//      public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) 
//      {
//        System.out.printf("Num Triangles: %d\n", new_val.intValue());
//        Attributes.numTriangles = new_val.intValue();
//        //main.drawCurImage(main.gfxR, SwingFXUtils.toFXImage(main.tribe.genome.bimg, null));
//      }
//    });
//    numTrianglesSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>()
//    {
//      public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) 
//      {
//        if (!new_val) 
//        {
//          System.out.println("done");
//          //main.drawCurImage(main.gfxR, SwingFXUtils.toFXImage(main.genome.bimg, null));
//        }
//      }
//    });
//    currentTriangleSlider.valueProperty().addListener(new ChangeListener<Number>()
//    {
//      public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) 
//      {
//        System.out.printf("Num Triangles: %d\n", new_val.intValue());
//        Attributes.currentTriangle = new_val.intValue();
////        gui.drawTriangles();
//      }
//    });
//    currentTriangleSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>()
//    {
//      public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) 
//      {
//        if (!new_val) 
//        {
//          System.out.println("done");
//          //main.drawCurImage(main.gfxR, SwingFXUtils.toFXImage(main.genome.bimg, null));
//        }
//      }
//    });
    
          
  }
  
  public void updateLabels() 
  {
    double input = 0;
    setCurrFit(main.currFitness);
    setFitPerSec(input);
    setTotalPop(input);
    setTotalHill(input);
    setGenPerSec(input);
    setAvgGenSec(input);
    setTotalRun(input);
  }
  public void setCurrFit(double input)
  {
    currFit.setText(String.format("Current Fitness %2.2f%%", input));
  }
  public void setFitPerSec(double input)
  {
    fitPerSec.setText(String.format("Current Fitness Per Second %2.2f%%", input));
  } 
  public void setTotalPop(double input)
  {
    totalPop.setText(String.format("Total population: %2.2f%%", input));
  }
  public void setTotalHill(double input)
  {
    totalHill.setText(String.format("Total hill climb: %2.2f%%", input));
  }
  public void setGenPerSec(double input)
  {
    genPerSec.setText(String.format("Total Generations Per Second: %2.2f%%", input));
  }
  public void setAvgGenSec(double input)
  {
    avgGenSec.setText(String.format("Average Generations Per Second: %2.2f%%", input));
  }
  public void setTotalRun(double input)
  {
    totalRun.setText(String.format("Total Runtime: %2.2f%%", input));
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
    else if(event.getSource() == Graph)
    {
      System.out.println("Graph");
    }
  }
    
  @FXML
  protected void imageButton(ActionEvent event)
  {
    if(event.getSource() == monaLisaButton)
    {
      main.setCurImage(monalisa);
    }
    else if(event.getSource() == poppyFieldsButton)
    {
      main.setCurImage(poppyfields);
    }
    else if(event.getSource() == greatWaveButton)
    {
      main.setCurImage(greatwave);
    }
    else if(event.getSource() == vanGoghButton)
    {
      main.setCurImage(vangogh);
    }
    else if(event.getSource() == mcEscherButton)
    {
      main.setCurImage(mcescher);
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
    case "srcOver": main.setBlendMode(BlendMode.SRC_OVER);
    break;
    case "srcAtop": main.setBlendMode(BlendMode.SRC_ATOP);
    break;
    case "srcAdd": main.setBlendMode(BlendMode.ADD);
    break;
    case "multiply": main.setBlendMode(BlendMode.MULTIPLY);
    break;
    case "screen": main.setBlendMode(BlendMode.SCREEN);
    break;
    case "overlay": main.setBlendMode(BlendMode.OVERLAY);
    break;
    case "darken": main.setBlendMode(BlendMode.DARKEN);
    break;
    case "lighten": main.setBlendMode(BlendMode.LIGHTEN);
    break;
    case "colorDodge": main.setBlendMode(BlendMode.COLOR_DODGE);
    break;
    case "colorBurn": main.setBlendMode(BlendMode.COLOR_BURN);
    break;
    case "hardLight": main.setBlendMode(BlendMode.HARD_LIGHT);
    break;
    case "softLight": main.setBlendMode(BlendMode.SOFT_LIGHT);
    break;
    case "difference": main.setBlendMode(BlendMode.EXCLUSION);
    break;
    case "exclusion": main.setBlendMode(BlendMode.EXCLUSION);
    break;
    case "redBlend": main.setBlendMode(BlendMode.RED);
    break;
    case "blueBlend": main.setBlendMode(BlendMode.BLUE);
    break;
    case "greenBlend": main.setBlendMode(BlendMode.GREEN);
    break;
    }
  }
  
  @FXML
  protected void clearButton()
  {
//    gui.clearTriangles();
  }
  
  @FXML
  protected void mutateButton()
  {
//    main.paused= !main.paused;
    main.initializeTribes();
  }

  @FXML
  public void saveButton()
  {
//    Formatter formatter = new Formatter();
    String dirName = String.format("%02.0f", (double) Attributes.seed);
    String name = String.format("%02.0f_%2.2f.png",(double) Attributes.seed, main.currFitness);
    File dir = new File (dirName);
    File file = new File (dir, name);
    
    try 
    {
      ImageIO.write(SwingFXUtils.fromFXImage(main.getSnapShot(getCanvasRight()), null), "png", file);
    } 
    catch (IOException e) 
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