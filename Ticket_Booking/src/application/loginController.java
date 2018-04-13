package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class loginController implements Initializable
{
	Connection conn=null;
	ResultSet rs=null;
	Statement stm=null;
   @FXML
   private Label isConnected;

   @FXML
   private TextField txtusername;

   @FXML
   private TextField txtpassword;

   @FXML
   private Button exit;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 conn=SqliteConnection.connectdb();
	     try {
			if(!conn.isClosed())
			 {
				 isConnected.setText("Connected");
			 }
			 else
			 {
				 isConnected.setText("Not Connected");
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Login (ActionEvent event)
	{
		try{
		if(isLogin(txtusername.getText(),txtpassword.getText()))
		{
	    	isConnected.setText("valid");
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage=new Stage();
			FXMLLoader loader=new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/user.fxml").openStream());
			userController userContro=(userController)loader.getController();
			userContro.getUser(txtusername.getText());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		else
		{

			isConnected.setText("invalid");
			Alert alert=new Alert(AlertType.ERROR);
	         alert.setTitle("Invalid");
	         alert.setContentText("Username and password does not match");
	         alert.showAndWait();
		}
		}catch(SQLException e)
		{
			isConnected.setText("invalid");
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void closelogin(ActionEvent event)
	{
		try{
		((Node)(event.getSource())).getScene().getWindow().hide();
		}
		catch(Exception e)
		{
		}
		}
	public void signupAction(ActionEvent event) throws IOException
	{
		Stage primaryStage=new Stage();
		FXMLLoader loader=new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/application/signup.fxml").openStream());
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public boolean isLogin(String user,String pass) throws SQLException
	{
		try
		{
	    stm=conn.createStatement();
		String s="Select * from login where username='"+user+"' and password ='"+pass+"'";
		rs=stm.executeQuery(s);
		if(rs.next())
		{
			return true;
		}
		else
		{
			return false;
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}

	}
	}

