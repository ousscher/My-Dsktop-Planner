package application;

import java.io.IOException;
import java.time.LocalDate;

import Model.DesktopPlaner;
import Model.Jour;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class fixplaning_controller {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	  private static DesktopPlaner application;
	  @FXML
	  private  DatePicker datePicker1;
	  @FXML
	  private DatePicker datePicker2;
	  
	  @FXML
	  private TextField nbrMinimaleParJour;
	  
	  
	  
	  public static void setappp(DesktopPlaner a) {
      	application = a;
      }
		
	  public void createPlaning(ActionEvent click) throws IOException {
		
		LocalDate date1 = datePicker1.getValue();
		LocalDate date2 = datePicker2.getValue();
		Jour.nbrMinimaleParJour = Integer.parseInt(nbrMinimaleParJour.getText());
  if(date1 != null && date2 !=null) {
		application.creerCalendrier(date1, date2);
		Menuone_controller.setapp(application);
		
		Parent root = FXMLLoader.load(getClass().getResource("Menuonepage.fxml"));
		stage = (Stage)((Node)click.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setWidth(1536.0);
		stage.setHeight(816.0);
		stage.setScene(scene);
		stage.show();
		  }
		
		
		
		  
	  }
		
	  
}
