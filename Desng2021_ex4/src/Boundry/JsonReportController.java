package Boundry;

import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

import Control.JsonController;
import Entity.Flight;
import Entity.Seat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class JsonReportController {
    static HashMap<Flight , ArrayList<Seat>> Seats;
    @FXML
    private Button backBtn;

    @FXML
    private Button json;

    @FXML
    private Button report;

    @FXML
    private Button seats;
    @FXML
    private ListView<Flight> List;
    @FXML
    void backAction(ActionEvent event) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
		
			Scene scene = new Scene(root);
			Main.PrimaryStage1.setScene(scene);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }

    @FXML
    void jsonaction(ActionEvent event) {
    	HashMap<Flight,ArrayList<Seat>> importedFlights= JsonController.getInstace().importJsonFile("json/flights.json");
    	ArrayList<Flight> list = new ArrayList<>(importedFlights.keySet());
    	ObservableList<Flight> cd=FXCollections.observableArrayList();
    	cd.addAll(list);
 
    	List.setItems(cd);
    	Seats = new HashMap<>(importedFlights);
    }

    @FXML
    void reportaction(ActionEvent event) {

    }

    @FXML
    void seatsAction(ActionEvent event) {
    	if(List.getSelectionModel().getSelectedIndex()==-1)
    	{
    		Feedback.message("Error","Please choose a flight to get the seats!");
    		return;
    	}
    	Stage windowSeats = new Stage();
    	windowSeats.initModality(Modality.APPLICATION_MODAL);
    	windowSeats.setTitle("seats in flight");
    	windowSeats.setMinWidth(300);
    	windowSeats.setMinHeight(300);
    	windowSeats.setHeight(600);
    	windowSeats.setWidth(500);	
    	ObservableList<Seat> cd3=FXCollections.observableArrayList(Seats.get(List.getSelectionModel().getSelectedItem()));
    	ListView<Seat>seatInF = new ListView<>();
    	seatInF.setItems(cd3);
    	VBox layout = new VBox(1);
    	layout.getChildren().add(seatInF);
    	layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		windowSeats.setScene(scene);
		windowSeats.showAndWait();
    	
    }


}
