package Boundry;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Feedback {

		public static void message(String title,String content) {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(title);
			alert.setHeaderText(content);
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

			stage.setAlwaysOnTop(true);
			alert.show();

		

	}
}
