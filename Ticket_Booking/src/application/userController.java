package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class userController implements Initializable{

	Connection conn=null;
	ResultSet rs=null;
	Statement stm=null;
	public static String mname="";
	@FXML
	private Label l1,l2;
	@FXML
	public  Button moviesB,b1,b2,b3,b4,b5,b6,b7,b8,homeB,booking;
	@FXML
	public  AnchorPane PaneAnchor,homepane,bookpane;
	@FXML
	public TableColumn<Bookings,String> c1,c2,c3,c4,c5;
	@FXML
	public TableView<Bookings> table;
	final ObservableList<Bookings>data=FXCollections.observableArrayList();
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		 conn=SqliteConnection.connectdb();
	}

	public void getUser(String user)
	{
		l1.setText(user);
	}
	public void signout(ActionEvent event)
	{
		try{
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage=new Stage();
			FXMLLoader loader=new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/login.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
	}
		catch(Exception e)
		{e.printStackTrace();
		}

	}
	public void handle(ActionEvent ae)
	{
		try
		{
		if(ae.getSource()==moviesB)
		{
			PaneAnchor.toFront();
			PaneAnchor.setVisible(true);
			homepane.setVisible(false);
			bookpane.setVisible(false);
		}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void handlehome(ActionEvent ae)
	{
		try{
		if(ae.getSource()==homeB)
		{
			homepane.toFront();
			homepane.setVisible(true);
			PaneAnchor.setVisible(false);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void bookhome(ActionEvent ae)
	{
		try{
			if(ae.getSource()==booking)
			{
				bookpane.toFront();
				bookpane.setVisible(true);
				homepane.setVisible(false);
				PaneAnchor.setVisible(false);
			}
			try{
			String s="select * from bookings;";
			 stm=conn.createStatement();
			 rs=stm.executeQuery(s);
			 while(rs.next())
			 {
				 data.add(new Bookings(
						 rs.getString(1),
						 rs.getString(2),
						 rs.getString(3),
						 rs.getString(4),
						 rs.getString(5)
						 ));
			 }
			}
			catch(SQLException e)
			{
                  e.printStackTrace();
			}
			c1.setCellValueFactory(new PropertyValueFactory<>("movie_name"));
			c2.setCellValueFactory(new PropertyValueFactory<>("date"));
			c3.setCellValueFactory(new PropertyValueFactory<>("no_of_seats"));
			c4.setCellValueFactory(new PropertyValueFactory<>("venue"));
			c5.setCellValueFactory(new PropertyValueFactory<>("category"));
			
			table.setItems(null);
			table.setItems(data);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void onClick(ActionEvent event) throws IOException
	{

		if(event.getSource()==b1)
   	    {
   		 mname="Pari";
   	    }
		 if(event.getSource()==b2)
    	 {
    		 mname="HateStrory 4";
    	 }
		 if(event.getSource()==b3)
    	 {
    		 mname="Pacific Rim";
    	 }
		 if(event.getSource()==b4)
		 {
			 mname="Black Panther";
		 }
		 if(event.getSource()==b5)
    	 {
    		 mname="Raid";
    	 }
		 if(event.getSource()==b6)
		 {
			 mname="Padman";
		 }
		 if(event.getSource()==b7)
    	 {
    		 mname="Dawn of the planet of the apes";
    	 }
		 if(event.getSource()==b8)
    	 {
    		 mname="Baaghi2";
    	 }
        //System.out.println("movie:"+mname);
		Stage primaryStage=new Stage();
		FXMLLoader loader=new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/application/form.fxml").openStream());
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
    public static String name()
     {
    	 //System.out.println("m:"+mname);
    	 return mname;
     }

}
