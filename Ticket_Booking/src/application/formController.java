package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;

public class formController implements Initializable {
	Connection conn=null;
	ResultSet rs=null;
	Statement stm=null;
	 String name="";
	@FXML
	private TextField tickets;
	@FXML
	private ListView<String> venue;
	@FXML
	private Button submit,cancel;
	@FXML
	private RadioButton rb1,rb2,rb3;
	@FXML
	private CheckBox cb1;
	@FXML
	private DatePicker dp;
	@FXML
	private Button b1,b2,b3,b4,b5,b6,b7,b8;

    ObservableList<String> list=FXCollections.observableArrayList("Big Cinemas","Inox","Cinepolis");
	public void initialize(URL arg0, ResourceBundle arg1) {
		 conn=SqliteConnection.connectdb();
		// TODO Auto-generated method stub

          venue.setItems(list);
          venue.focusModelProperty();
	}
	public void cancelAction(ActionEvent event)
	{
		try{
		((Node)(event.getSource())).getScene().getWindow().hide();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}

     public void formAction(ActionEvent event)
     {
    	try{
    		name=userController.name();
    		//System.out.println("name"+name);
    	 stm=conn.createStatement();
    	 String msg="";

    	 String nooftic=tickets.getText();


    	 ObservableList<String> names;
    	 names=venue.getSelectionModel().getSelectedItems();


    	 String seat="";
    	 if(rb1.isSelected())
    	 {
    		 seat="Platinum";
    	 }
    	 else if(rb2.isSelected())
    	 {
    		 seat="Gold";
    	 }
    	 else
    		 seat="Silver";

    	 int pop=0;
         if(cb1.isSelected())
         {
        	 pop=1;
         }

         LocalDate ld=dp.getValue();
         stm.executeUpdate("insert into bookings(movie_name,date,no_of_seats,venue,category)"
        		 +"values('"+name+"','"+ld+"','"+nooftic+"','"+names+"','"+seat+"')");
         msg=nooftic+"\ttickets booked for the movie\t"+name+" on\t"+ld+"\tat\t"+names+"\nType:"+seat+"\t";
         if(pop==1)
         {
        	 msg+="\nPopcorn added\nThank you";
         }

         Alert alert=new Alert(AlertType.INFORMATION);
         alert.setTitle("Booking Successful");
         alert.setContentText(msg);
         alert.showAndWait();
    	 }
    	 catch(Exception e)
    	 {
    		 Alert alert=new Alert(AlertType.ERROR);
             alert.setTitle("error");
             alert.setContentText("enter all details");
             alert.showAndWait();
    		 e.printStackTrace();
    	 }
    }

}
