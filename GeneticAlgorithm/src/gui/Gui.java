package gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Gui extends Application
{
  GraphicsContext gfx;
  @FXML Canvas canvas;

  @Override
  public void start(Stage primaryStage) throws Exception 
  {
    
    GuiControls controller = new GuiControls();
    Scene scene = new Scene(controller);
//    BorderPane page = (BorderPane) FXMLLoader.load(Gui.class.getResource("gui.fxml"));
//    gfx = canvas.getGraphicsContext2D();
//    gfx = (GraphicsContext) ( ((VBox) page.getRight()).getChildren().get(1)).getGraphicsContext2D();
//    Scene scene = new Scene(page);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Genetic Algorithm by Atle and Chris");
    primaryStage.show();
    
  }
  
  public static void main(String[] args)
  {
    launch(args);
  }

}
