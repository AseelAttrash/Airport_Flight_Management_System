package Boundry;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Vector;

import Control.CustomerOrderControl;
import Control.reportController;
import Entity.Customer;
import Entity.Flight;
import Entity.FlightTicket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ReportController implements Initializable{

    @FXML
    private TableColumn<ReportElemnts, String> EveningClmn;

    @FXML
    private TableColumn<ReportElemnts, String> NightClmn;

    @FXML
    private TableColumn<ReportElemnts, String> NoonClmn;

    
    @FXML
    private Button Btn;
    
    @FXML
    private TableColumn<ReportElemnts, String> PassportClmn;

    @FXML
    private TableColumn<ReportElemnts, String> firstNameClmn;

    @FXML
    private TableColumn<ReportElemnts, String> lastNameClmn;

    @FXML
    private TableColumn<ReportElemnts, String> morningClmn;

    @FXML
    private TableView<ReportElemnts> tableCustomer;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<ReportElemnts>data=FXCollections.observableArrayList();
		for(ReportElemnts c: reportController.getInstace().getReport()) {
    		data.add(c);
	}    	EveningClmn.setCellValueFactory(new PropertyValueFactory<>("evening"));
    	NightClmn.setCellValueFactory(new PropertyValueFactory<ReportElemnts, String>("night"));
    	NoonClmn.setCellValueFactory(new PropertyValueFactory<ReportElemnts, String>("noon"));
    	PassportClmn.setCellValueFactory(new PropertyValueFactory<ReportElemnts, String>("passport"));
    	firstNameClmn.setCellValueFactory(new PropertyValueFactory<ReportElemnts, String>("firstName"));
    	lastNameClmn.setCellValueFactory(new PropertyValueFactory<ReportElemnts, String>("LastName"));
    	morningClmn.setCellValueFactory(new PropertyValueFactory<ReportElemnts, String>("morning"));

    	tableCustomer.setItems(data);	
	}
    @FXML
    void btNT(ActionEvent event) {
    	if(tableCustomer.getSelectionModel().getSelectedIndex()==-1)
    	{
    		Feedback.message("error", "Please choose a customer");
    		return;
    	}
    	Customer cus = CustomerOrderControl.getInstace().getCusById(tableCustomer.getSelectionModel().getSelectedItem().getPassport());
    	ArrayList<FlightTicket> ft1  = CustomerOrderControl.getInstace().handleFTickets(cus);
    	UpdateCustomerController.c = cus;
    	UpdateCustomerController.ft = ft1;
    	
      	Stage st= new Stage();
    			try {
    				Parent root = FXMLLoader.load(getClass().getResource("UpdateOrders.fxml"));
    				Scene scene = new Scene(root);
    				st.setScene(scene);
    				st.setResizable(false);
    				st.showAndWait();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    }

}
