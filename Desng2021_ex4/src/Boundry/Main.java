
package Boundry;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	public static Stage PrimaryStage1=null;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {
			//login page which starts with
			Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			PrimaryStage1=primaryStage;
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	public static void main(String[]args) {
		launch(args);
	}
}
