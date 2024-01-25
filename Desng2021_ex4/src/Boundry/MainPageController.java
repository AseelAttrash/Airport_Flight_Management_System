package Boundry;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainPageController {

	 @FXML
	    private Button CustomerReportBtn;

	    @FXML
	    private Button UpdatesFromReportBtn;
;
	    @FXML
	    void CustomerReportAction(ActionEvent event) {
	    	Stage st= new Stage();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("report.fxml"));
				Scene scene = new Scene(root);
				st.setScene(scene);
				st.setResizable(false);
				st.showAndWait();
			} catch (Exception e) {
				e.printStackTrace();
			}

	    }


    @FXML
    void UpdatesFromReportAction(ActionEvent event) {
     	try {
			Parent root = FXMLLoader.load(getClass().getResource("JsonReport.fxml"));
		
			Scene scene = new Scene(root);
			Main.PrimaryStage1.setScene(scene);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }

}
