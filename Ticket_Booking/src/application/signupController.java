package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class signupController implements Initializable {
	Connection conn=null;;
	ResultSet rs=null;
	Statement stm=null;
	@FXML
	private TextField tf1;
	@FXML
	private TextField tf2;
	@FXML
	private TextField tf3;
	@FXML
	private TextField tf4;
	@FXML
	private Button submit;
	@FXML
	private Button exit;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		 conn=SqliteConnection.connectdb();

	}
	public void exitAction(ActionEvent event)
	{
		try{
		((Node)(event.getSource())).getScene().getWindow().hide();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
	 public void submitAction(ActionEvent event)
	 {
		 try
		 {
		 String fname=tf1.getText();
		 String age=tf2.getText();
		 String user=tf3.getText();
		 String pass=tf4.getText();
		 stm=conn.createStatement();
		// stm.executeUpdate("insert into login values('xyz','hv','abc','abc')");
		 stm.executeUpdate("INSERT INTO login (fname,age,username,password)"
		           +"VALUES('"+fname+"','"+age+"','"+user+"','"+pass+"')");
		 Alert alert=new Alert(AlertType.INFORMATION);
         alert.setTitle("Registration Successful");
         alert.setContentText("You can Login now!");
          alert.showAndWait();
		 }
		 catch(Exception e)
		 {
           e.printStackTrace();
		 }
		}

	 }


