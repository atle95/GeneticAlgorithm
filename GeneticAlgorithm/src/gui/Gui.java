package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Gui extends Application
{

  @Override
  public void start(Stage primaryStage) throws Exception 
  {
//    GuiControls controller = new GuiControls();
//    Scene scene = new Scene(controller);
    Parent page = (Parent) FXMLLoader.load(Gui.class.getResource("gui.fxml"));
    Scene scene = new Scene(page);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Genetic Algorithm by Atle and Chris");
    primaryStage.show();
    
  }
  
  public static void main(String[] args)
  {
    launch(args);
  }

}
