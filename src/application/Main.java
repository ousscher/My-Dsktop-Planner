package application;
import javafx.geometry.Rectangle2D;

import javafx.stage.Screen;
import java.io.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import Model.*;


public class Main extends Application {
	private static DesktopPlaner application= Firstpage_controller.getapp();
	 
	@Override
	
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Firstpage.fxml"));
			Scene scene1 = new Scene(root,400,400);
			scene1.getStylesheets().add(getClass().getResource("Firstpage.css").toExternalForm());
			primaryStage.setScene(scene1);
			primaryStage.setWidth(1536.0);
			primaryStage.setHeight(816.0);
			//primaryStage.setFullScreen(true);
			primaryStage.show();
			primaryStage.setOnCloseRequest(event ->Loginpage_controller.saveApplicationInstance());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	 
}
