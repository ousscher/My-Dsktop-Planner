package application;
import java.io.*;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Firstpage_controller {
	
		private Stage stage;
		private Scene scene;
		private Parent root;
		public static DesktopPlaner application;
		
		public  static DesktopPlaner getapp() {
			return application;
		}
		
		public  static void setapp(DesktopPlaner app) {
			application = app;
		}
		
		
	
		
	public void start(ActionEvent event) throws IOException, ClassNotFoundException {
		String filePath = "src/application.ser";
		 if (fileExists(filePath)) {
	            try {
	                FileInputStream fileIn = new FileInputStream(filePath);
	                ObjectInputStream in = new ObjectInputStream(fileIn);
	                application = (DesktopPlaner) in.readObject();
	                in.close();
	                fileIn.close();
	            } catch (IOException | ClassNotFoundException e) {
	                e.printStackTrace();
	            }
	        } else {
	            application = new DesktopPlaner();
	        }

		
         Loginpage_controller.setapp(application);
         Signpage_controller.setappp(application);
         System.out.println(application);
         System.out.println("Application instance restored successfully.");
	
	
	
	
	Parent root = FXMLLoader.load(getClass().getResource("Loginpage.fxml"));
	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	scene.getStylesheets().add(getClass().getResource("Firstpage.css").toExternalForm());
	stage.setWidth(1536.0);
	stage.setHeight(816.0);
	stage.setScene(scene);
	stage.show();
		
	
		
	}


	  private static boolean fileExists(String filePath) {
	        File file = new File(filePath);
	        return file.exists();
	    }
	

}
