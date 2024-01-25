package Boundry;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Control.CustomerOrderControl;
import Entity.Customer;
import Entity.Flight;
import Entity.FlightTicket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class UpdateCustomerController implements Initializable{
public static	Customer c = null;
public static ArrayList<FlightTicket>ft ;
    @FXML
    private ListView<FlightTicket> ListTickets;
    @FXML
    private Button updateCustomerBtn;

    @FXML
    private Label email;

    @FXML
    private ListView<Flight> listRecomnnended;
    @FXML
    private Button recomndsBtn;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		email.setText(c.getEmail());
		ObservableList<FlightTicket> kk=FXCollections.observableArrayList();
    	for(FlightTicket d : ft) {
    		kk.add(d);
    	}
    	ListTickets.setItems(kk);

		
	}

    @FXML
    void recomndsAction(ActionEvent event) {
    	if(ListTickets.getSelectionModel().getSelectedIndex() ==-1) {
    		Feedback.message("error", "Choose a flight Ticket!");
    		return;
    	}
    	FlightTicket f = ListTickets.getSelectionModel().getSelectedItem();
    	ObservableList<Flight> cd3=FXCollections.observableArrayList(CustomerOrderControl.getInstace().recFlights(f));
    	listRecomnnended.setItems(cd3);
    	
    }
    @FXML
    void updateCustomerAction(ActionEvent event) {
    	if(ListTickets.getSelectionModel().getSelectedIndex() ==-1) {
    		Feedback.message("error", "Choose a flight Ticket!");
    		return;
    	}
    	String id = ListTickets.getSelectionModel().getSelectedItem().getId();
    	CustomerOrderControl.getInstace().updateFlightTicket(id, true);
		Feedback.message("updated!", "Flight Ticket has been updated and the recommendation has been sent to the customer email!");

    }

}
