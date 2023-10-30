package application;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class Loginpage_controller implements Initializable {
	
		private Stage stage;
		private Scene scene;
		private Parent root;
		private static DesktopPlaner application;
		public static void setapp(DesktopPlaner app)
		{
			application = app;
		}
		@FXML
		
		
		HashSet<Utilisateur> utilisateurs = application.getUtilisateurs();
		 @FXML
		    private ChoiceBox<String> psudos;
		
		 
		 @Override
		 public void initialize(URL arg0, ResourceBundle arg1) {
			 
			  Iterator<Utilisateur> iterable = utilisateurs.iterator();
		        while (iterable.hasNext()) {
		            
		            psudos.getItems().add(iterable.next().getPsuedo());
		        }
		     
		 }
		 
		public void connecter(ActionEvent event) throws IOException {
			
			
			
			String selectedPseudo = psudos.getValue();
			 if (selectedPseudo != null) {
				 application.login(selectedPseudo);
				 if(application.getUtilisateurActuel().getCalendrier()==null) {
					 fixplaning_controller.setappp(application);
						//System.out.print(false);
						
						 
						
						 Parent root = FXMLLoader.load(getClass().getResource("fixplaning.fxml"));
							stage = (Stage)((Node)event.getSource()).getScene().getWindow();
							scene = new Scene(root);
							stage.setWidth(1536.0);
							stage.setHeight(816.0);
							stage.setScene(scene);
							stage.show();
				 }
				 else {Menuone_controller.setapp(application);
				 
					
					Parent root = FXMLLoader.load(getClass().getResource("Menuonepage.fxml"));
					
					stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("Firstpage.css").toExternalForm());
					stage.setWidth(1536.0);
					stage.setHeight(816.0);
					stage.setScene(scene);
					stage.show();
				 }
				 
			        }else {
			        	System.out.println("Select Psudo");
			        }
		
			
			
		}	
		
		
	public void sign(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("Signpage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("Firstpage.css").toExternalForm());
		stage.setWidth(1536);
		stage.setHeight(816);
		stage.setScene(scene);
		stage.show();
		
	}

	 public static void saveApplicationInstance() {
	        try {
	        	application.afficherUtilisateurs();
	            FileOutputStream fileOut = new FileOutputStream("src/application.ser");
	            ObjectOutputStream out = new ObjectOutputStream(fileOut);
	            out.writeObject(application);
	            out.close();
	            fileOut.close();
	            System.out.println("Application instance saved successfully.");
	        } catch
	        (FileNotFoundException
	        		e) {
	        		e.printStackTrace();
	        		} catch (IOException e)
	        		{
	        		e.printStackTrace();
	        		}
	    }

}
