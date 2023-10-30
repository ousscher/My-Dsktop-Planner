package application;
import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Model.*;
public class Signpage_controller  {
	@FXML
		private Stage stage;
		private Scene scene;
		private Parent root;
		@FXML
	    TextField pseudosign;
        private static DesktopPlaner application= Firstpage_controller.getapp();
		
        public  static DesktopPlaner getapp2() {
			return application;
		}
        public static void setappp(DesktopPlaner a) {
        	application = a;
        }
		
		

	public void continusign(ActionEvent click) throws IOException {
		
		String psudo = pseudosign.getText();
		System.out.println(psudo);
		application.signup(psudo);
		application.login(psudo);
		application.afficherUtilisateurs();
		Firstpage_controller.setapp(application);
		Loginpage_controller.setapp(application);
		fixplaning_controller.setappp(application);
		//System.out.print(false);
		
		
		
		 Parent root = FXMLLoader.load(getClass().getResource("fixplaning.fxml"));
			stage = (Stage)((Node)click.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setWidth(1536.0);
			stage.setHeight(816.0);
			stage.setScene(scene);
			stage.show();
		}
		
	
	
public void goback(ActionEvent event2) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("Loginpage.fxml"));
		
		stage = (Stage)((Node)event2.getSource()).getScene().getWindow();  
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("Firstpage.css").toExternalForm());
		stage.setWidth(1536);
		stage.setHeight(816);
		stage.setScene(scene);
		stage.show();
		
	}
	

}


