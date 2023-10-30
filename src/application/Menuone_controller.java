package application;
import javafx.scene.control.ButtonBar;

import javafx.event.ActionEvent;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.Font;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;

import Model.Categorie;
import Model.Crineau;
import Model.DesktopPlaner;
import Model.Jour;
import Model.Priorite;
import Model.Tache;
import Model.*;
import Model.TacheSimple;
public class Menuone_controller implements Initializable {
	   
	private static DesktopPlaner application;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public static void setapp(DesktopPlaner a) {
		application = a;
		
	}
	
	

	  ZonedDateTime dateFocus;
	    ZonedDateTime today;

	    @FXML
	    private Text year;

	    @FXML
	    private Text month;

	    @FXML
	    private FlowPane calendar;

	    @Override
	    public void initialize(URL url, ResourceBundle resourceBundle) {
	        dateFocus = ZonedDateTime.now();
	        today = ZonedDateTime.now();
	        drawCalendar();
	    }

	    @FXML
	    void backOneMonth(ActionEvent event) {
	        dateFocus = dateFocus.minusMonths(1);
	        calendar.getChildren().clear();
	        drawCalendar();
	    }

	    @FXML
	    void forwardOneMonth(ActionEvent event) {
	        dateFocus = dateFocus.plusMonths(1);
	        calendar.getChildren().clear();
	        drawCalendar();
	    }
	    private void showDialog(DesktopPlaner application) {
	        // Création des champs de saisie
	        TextField taskNameField = new TextField();
	        Spinner<Integer> durationSpinner = new Spinner<>(1, Integer.MAX_VALUE, 1);
	        ChoiceBox<Priorite> priorityChoiceBox = new ChoiceBox<>();
	        priorityChoiceBox.getItems().addAll(Priorite.values());

	        ChoiceBox<Categorie> categoryChoiceBox = new ChoiceBox<>();
	        categoryChoiceBox.getItems().addAll(Categorie.values());
	        ColorPicker colorPicker = new ColorPicker();
	        DatePicker deadlineDatePicker = new DatePicker();
	        TextField periodicityField = new TextField();

	        // Création des étiquettes
	        Label taskNameLabel = new Label("Nom de la tâche :");
	        Label durationLabel = new Label("Durée :");
	        Label priorityLabel = new Label("Priorité :");
	        Label categoryLabel = new Label("Catégorie :");
	        Label colorLabel = new Label("Couleur :");
	        Label deadlineLabel = new Label("Date limite :");
	        Label periodicityLabel = new Label("Périodicité :");

	        // Création des boutons
	        Button applyButton = new Button("Appliquer");
	        Button cancelButton = new Button("Annuler");

	        // Création de la grille pour organiser les composants
	        GridPane gridPane = new GridPane();
	        gridPane.setPadding(new Insets(10));
	        gridPane.setVgap(10);
	        gridPane.setHgap(10);

	        // Placement des composants dans la grille
	        gridPane.add(taskNameLabel, 0, 0);
	        gridPane.add(taskNameField, 1, 0);
	        gridPane.add(durationLabel, 0, 1);
	        gridPane.add(durationSpinner, 1, 1);
	        gridPane.add(priorityLabel, 0, 2);
	        gridPane.add(priorityChoiceBox, 1, 2);
	        gridPane.add(categoryLabel, 0, 3);
	        gridPane.add(categoryChoiceBox, 1, 3);
	        gridPane.add(colorLabel, 0, 4);
	        gridPane.add(colorPicker, 1, 4);
	        gridPane.add(deadlineLabel, 0, 5);
	        gridPane.add(deadlineDatePicker, 1, 5);
	        gridPane.add(periodicityLabel, 0, 6);
	        gridPane.add(periodicityField, 1, 6);

	        // Création de la boîte de dialogue
	        Dialog<Void> dialog = new Dialog<>();
	        dialog.setTitle("Ajouter une tâche");
	        dialog.setResizable(false);

	        // Configuration des boutons de la boîte de dialogue
	        ButtonType applyButtonType = new ButtonType("Appliquer", ButtonBar.ButtonData.APPLY);
	        ButtonType cancelButtonType = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
	        dialog.getDialogPane().getButtonTypes().addAll(applyButtonType, cancelButtonType);

	        // Personnalisation des boutons
	        Button applyButtonControl = (Button) dialog.getDialogPane().lookupButton(applyButtonType);
	        Button cancelButtonControl = (Button) dialog.getDialogPane().lookupButton(cancelButtonType);
	        applyButtonControl.setStyle("-fx-background-color: #191A43; -fx-text-fill: white;");
	        cancelButtonControl.setStyle("-fx-background-color: #191A43; -fx-text-fill: white;");

	        // Personnalisation de la boîte de dialogue
	        dialog.getDialogPane().setPrefSize(400, 300);
	        dialog.getDialogPane().setStyle("-fx-background-color: #FBEDEC;");
	        dialog.getDialogPane().getStyleClass().add("dialog-pane");

	        // Configuration du contenu de la boîte de dialogue
	        VBox content = new VBox(gridPane);
	        content.setSpacing(10);
	        content.setPadding(new Insets(20));
	        dialog.getDialogPane().setContent(content);

	        // Récupération des résultats lorsque le bouton "Appliquer" est cliqué
	        dialog.setResultConverter(dialogButton -> {
	            if (dialogButton == applyButtonType) {
	                String taskName = taskNameField.getText();
	                Duration duration = Duration.ofMinutes(durationSpinner.getValue());
	                Priorite priority = priorityChoiceBox.getValue();
	                Categorie category = categoryChoiceBox.getValue();
	                Color color = colorPicker.getValue();
	                LocalDate deadline = deadlineDatePicker.getValue();
	                int periodicity = Integer.parseInt(periodicityField.getText());

	                // Faites quelque chose avec les valeurs récupérées
	                Tache tache = application.identifierTache(taskName, duration, deadline, priority, color, category, periodicity);
	                if (tache instanceof TacheSimple) {
	                    System.out.println("C'est une tâche simple");
	                    application.autoPlanifierTacheSimple((TacheSimple) tache);
	                } else {
	                    System.out.println("C'est une tâche décomposable");
	                    application.autoPlanifierTacheDecomposable((TacheComplex) tache);
	                    application.getUtilisateurActuel().AjouterTacheComplexe((TacheComplex) tache);
	                }
	            }
	            return null;
	        });

	        // Affichage de la boîte de dialogue
	        dialog.showAndWait();
	    }
	    
	    public void Planifierauto(){
	    	showDialog(application);
	    }
	    public static void showDialogError(String message, Tache tache) {
	    	
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Erreur dans la planification de la tâche '" + tache.getNom() + "'");
	        dialogStage.initModality(Modality.APPLICATION_MODAL);
	        dialogStage.setWidth(800);
	        dialogStage.setHeight(400);

	        // Label pour afficher le message
	        Label messageLabel = new Label();
	        messageLabel.setText(message);
	        messageLabel.setTextFill(Color.RED);
	        messageLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

	        // Bouton D'accord
	        Button okButton = new Button("D'accord");
	        okButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

	        // Gestionnaire d'événements pour le bouton
	        okButton.setOnAction(e -> {
	            dialogStage.close(); // Ferme la boîte de dialogue lorsque le bouton D'accord est cliqué
	        });

	        // Création du conteneur HBox pour le bouton
	        HBox buttonBox = new HBox(10);
	        buttonBox.setAlignment(Pos.CENTER);
	        buttonBox.setPadding(new Insets(10));
	        buttonBox.getChildren().add(okButton);

	        // Création du conteneur BorderPane
	        BorderPane borderPane = new BorderPane();
	        borderPane.setPadding(new Insets(10));
	        borderPane.setCenter(messageLabel);
	        borderPane.setBottom(buttonBox);

	        // Création de la scène et ajout du BorderPane
	        Scene scene = new Scene(borderPane);
	        dialogStage.setScene(scene);
	        dialogStage.showAndWait(); // Affiche la boîte de dialogue et attend qu'elle se ferme
	    }

	    public static int showDialogSimple(String message, Tache tache) {
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Propositions pour planifier la tâche");
	        dialogStage.initModality(Modality.APPLICATION_MODAL);
	        
	        dialogStage.setWidth(800);
	        dialogStage.setHeight(400);

	        // Label pour afficher le texte statique
	        Label staticLabel = new Label("Voici une proposition pour planifier la tâche " + tache.getNom());
	        staticLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

	        // Label pour afficher le texte vide
	        Label emptyLabel = new Label();
	        emptyLabel.setText(message);
	        emptyLabel.setStyle("-fx-font-size: 12px;");

	        // Boutons Confirmer, Autre et Annuler
	        Button confirmerButton = new Button("Confirmer");
	        Button autreButton = new Button("Autre");
	        Button annulerButton = new Button("Annuler");

	        // Appliquer un style d'écriture personnalisé
	        confirmerButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
	        autreButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
	        annulerButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

	        final int[] choix = {0};

	        // Gestionnaire d'événements pour les boutons
	        confirmerButton.setOnAction(e -> {
	            // Logique pour le bouton Confirmer
	            // Ajoutez votre code ici
	            choix[0] = 1;
	            dialogStage.close();
	        });

	        autreButton.setOnAction(e -> {
	            // Logique pour le bouton Autre
	            // Ajoutez votre code ici
	            choix[0] = 0;
	            dialogStage.close();
	        });

	        annulerButton.setOnAction(e -> {
	            // Logique pour le bouton Annuler
	            // Ajoutez votre code ici
	            dialogStage.close(); // Ferme la boîte de dialogue lorsque le bouton Annuler est cliqué
	        });

	        // Création du conteneur HBox pour les boutons
	        HBox buttonBox = new HBox(10);
	        buttonBox.setAlignment(Pos.CENTER);
	        buttonBox.setPadding(new Insets(10));
	        buttonBox.getChildren().addAll(confirmerButton, autreButton, annulerButton);

	        // Création du conteneur BorderPane
	        BorderPane borderPane = new BorderPane();
	        borderPane.setPadding(new Insets(10));
	        borderPane.setTop(staticLabel);
	        borderPane.setCenter(emptyLabel);
	        borderPane.setBottom(buttonBox);

	        // Création de la scène et ajout du BorderPane
	        Scene scene = new Scene(borderPane);
	        dialogStage.setScene(scene);
	        dialogStage.showAndWait(); // Affiche la boîte de dialogue et attend qu'elle se ferme

	        return choix[0];
	    }
	    public static int showDialogComplex(List<String> messages , Tache tache) {
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Propositions pour planifier la tâche");
	        dialogStage.initModality(Modality.APPLICATION_MODAL);
	        dialogStage.setWidth(800);
	        dialogStage.setHeight(400);
	        int[] choix = {0};

	        // Label pour afficher le texte statique
	        Label staticLabel = new Label("Voici une proposition pour planifier la tâche "+tache.getNom());
	        String multiLineText = "";
	        for (String message : messages) {
	            multiLineText = multiLineText + message + "\n";
	            System.out.println("----" + message);
	        }
	        Label label = new Label(multiLineText);
	        label.setWrapText(true);

	        VBox root = new VBox(label);
	        root.setPadding(new Insets(10));

	        // Boutons Confirmer, Autre et Annuler
	        Button confirmerButton = new Button("Confirmer");
	        Button autreButton = new Button("Autre");
	        Button annulerButton = new Button("Annuler");

	        // Appliquer un style d'écriture personnalisé
	        confirmerButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
	        autreButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
	        annulerButton.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

	        // Gestionnaire d'événements pour les boutons
	        confirmerButton.setOnAction(e -> {
	            choix[0] = 1;
	            dialogStage.close();
	        });

	        autreButton.setOnAction(e -> {
	            choix[0] = 0;
	            dialogStage.close();
	        });

	        annulerButton.setOnAction(e -> {
	            dialogStage.close();
	        });

	        // Création du conteneur HBox pour les boutons
	        HBox buttonBox = new HBox(10);
	        buttonBox.setAlignment(Pos.CENTER);
	        buttonBox.setPadding(new Insets(10));
	        buttonBox.getChildren().addAll(confirmerButton, autreButton, annulerButton);

	        // Création du conteneur BorderPane
	        BorderPane borderPane = new BorderPane();
	        borderPane.setPadding(new Insets(10));
	        borderPane.setTop(staticLabel);
	        borderPane.setCenter(root);
	        borderPane.setBottom(buttonBox);

	        // Création de la scène et ajout du BorderPane
	        Scene scene = new Scene(borderPane);
	        dialogStage.setScene(scene);
	        dialogStage.showAndWait(); // Affiche la boîte de dialogue et attend qu'elle se ferme

	        return choix[0];
	    }
	    
	    
	    
	    private void drawCalendar(){
	        year.setText(String.valueOf(dateFocus.getYear()));
	        month.setText(String.valueOf(dateFocus.getMonth()));

	        calendar.getChildren().clear();
	        double calendarWidth = calendar.getPrefWidth();
	        double calendarHeight = calendar.getPrefHeight();
	        double strokeWidth = 1;
	        double spacingH = calendar.getHgap();
	        double spacingV = calendar.getVgap();

	        //List of activities for a given month


	        int monthMaxDate = dateFocus.getMonth().maxLength();
	        //Check for leap year
	        if(dateFocus.getYear() % 4 != 0 && monthMaxDate == 29){
	            monthMaxDate = 28;
	        }
	        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1,0,0,0,0,dateFocus.getZone()).getDayOfWeek().getValue();

	        for (int i = 0; i < 6; i++) {
	            for (int j = 0; j < 7; j++) {
	                StackPane stackPane = new StackPane();

	                Rectangle rectangle = new Rectangle();
	                
	                rectangle.setFill(Color.TRANSPARENT);
	                rectangle.setStroke(Color.GRAY);
	                //rectangle.setStroke(Color.BLACK);
	                rectangle.setStrokeWidth(strokeWidth);
	                double rectangleWidth =(calendarWidth/7) - strokeWidth - spacingH;
	                rectangle.setWidth(rectangleWidth);
	                double rectangleHeight = (calendarHeight/6) - strokeWidth - spacingV;
	                rectangle.setHeight(rectangleHeight);
	           

	                stackPane.getChildren().add(rectangle);

	                int calculatedDate = (j+1)+(7*i);
	                LocalDate dateActuel;
	                if(calculatedDate > dateOffset){
	                    int currentDate = calculatedDate - dateOffset;
	                    if(currentDate <= monthMaxDate){
	                        Text date = new Text(String.valueOf(currentDate));
	                        double textTranslationY = - (rectangleHeight / 2) * 0.75;
	                        date.setTranslateY(textTranslationY);
	                        stackPane.getChildren().add(date);
	                        dateActuel=LocalDate.of(dateFocus.getYear(),dateFocus.getMonthValue(),currentDate);
	                        if (dateActuel.isAfter(application.getUtilisateurActuel().getCalendrier().getDateDebut()) ||dateActuel.equals(application.getUtilisateurActuel().getCalendrier().getDateDebut()) ) {
	                        	LocalDate dateFin = application.getUtilisateurActuel().getCalendrier().getDateDebut().plusDays(application.getUtilisateurActuel().getCalendrier().getPeriode()+1);
	                        	//Duration duration = Duration.ofDays(application.getUtilisateurActuel().getCalendrier().getPeriode());
	                        	//dateFin = dateFin.plusDays(duration.toDays());
	                        	if(dateActuel.isBefore(dateFin)) {
	                        		//rectangle.setStroke(Color.ORANGE);
	                        		rectangle.setFill(Color.valueOf("#FB9B90"));
	                        		rectangle.setOnMouseClicked(event -> {
	    	    	                	if(currentDate <= monthMaxDate){
	    	    	                		
	    	    	                	}
	    	    	                    // Handle click event here
	    	    	                    // You can access the rectangle or its properties if needed
	    	    	
	    	    	                    LocalDate date2 = LocalDate.of(dateFocus.getYear(),dateFocus.getMonthValue(),currentDate);
	    	    	                    Jour jourj = new Jour();
	    	    	                    for(Jour jour : application.getUtilisateurActuel().getCalendrier().getJours()) {
	    	    	                    	if (date2.compareTo(jour.getDate()) == 0) {
	    	    	                    		System.out.println("----- "+jour.getDate());
	    	    	                    		jourj=jour;
	    	    	                    	}
	    	    	                    }
	    	    	                    TreeSet<Crineau> crineaux = jourj.getCrineaux() ;
	    	    	                    
	    	    	                    Daypage_controller.setcrn(crineaux, application , jourj);
	    	    	                    
	    								try {
	    									Parent root = FXMLLoader.load(getClass().getResource("Daypage.fxml"));
	    									stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    		    	        			scene = new Scene(root);
	    		    	        			stage.setWidth(1536.0);
	    		    	        			stage.setHeight(816.0);
	    		    	        			stage.setScene(scene);
	    		    	        			stage.show();
	    								} catch (IOException e) {
	    									// TODO Auto-generated catch block
	    									e.printStackTrace();
	    								}
	    	    	        			
	    	    	                    
	    	    	                });
	                        	}
	                        }
	                    }
	                    if(today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate){
	                        rectangle.setStroke(Color.BLUE);
	                    }
	                   
	                }
	                
	                if(calculatedDate > dateOffset){
	                    int currentDate = calculatedDate - dateOffset;
	                    //System.out.print("---------"+currentDate);
	               
	                calendar.getChildren().add(stackPane);
	            }
	        }
	    }

}
	    
//--------------------------------------------------------3fssa jdiida -----------------------------------------7
	    public static int showDialogDetectNumber() {

	        Stage dialogStage = new Stage();
	        dialogStage.initModality(Modality.APPLICATION_MODAL);
	        dialogStage.setTitle("Entrez le nombre de tâches que vous souhaitez planifier");
	        dialogStage.setResizable(false);

	        // Création du champ de saisie de nombre entier
	        TextField inputField = new TextField();
	        inputField.setPromptText("Entrez un nombre entier");

	        // Création du bouton Confirmer
	        Button confirmButton = new Button("Confirmer");
	        confirmButton.setDefaultButton(true);
	        final int[] choix = {0};
	        confirmButton.setOnAction(e -> {
	            String input = inputField.getText();
	            try {
	                int numberOfTasks = Integer.parseInt(input);
	                choix[0] = numberOfTasks;
	                System.out.println("Nombre de tâches saisi : " + numberOfTasks);
	                dialogStage.close();
	            } catch (NumberFormatException ex) {
	                System.out.println("Saisie invalide. Veuillez entrer un nombre entier.");
	            }
	        });

	        // Création de la mise en page de la boîte de dialogue
	        VBox vbox = new VBox(10);
	        vbox.setPadding(new Insets(10));
	        vbox.setStyle("-fx-background-color: #FBEDEC;");
	        vbox.getChildren().addAll(new Label("Entrez le nombre de tâches :"), inputField, confirmButton);

	        Scene dialogScene = new Scene(vbox, 300, 150);
	        dialogStage.setScene(dialogScene);
	        dialogStage.showAndWait();
	        return choix[0];
	    }
private void showDialogEnsemble(PriorityQueue<Tache> queue) {
	    	
	        // Création des champs de saisie
	        TextField taskNameField = new TextField();
	        Spinner<Integer> durationSpinner = new Spinner<>(1, Integer.MAX_VALUE, 1);
	        ChoiceBox<Priorite> priorityChoiceBox = new ChoiceBox<>();
	        priorityChoiceBox.getItems().addAll(Priorite.values());
	       
	        ChoiceBox<Categorie> categoryChoiceBox = new ChoiceBox<>();
	        categoryChoiceBox.getItems().addAll(Categorie.values());
	        ColorPicker colorPicker = new ColorPicker();
	        DatePicker deadlineDatePicker = new DatePicker();
	        TextField periodicityField = new TextField();

	        // Création des étiquettes
	        Label taskNameLabel = new Label("Nom de la tâche :");
	        Label durationLabel = new Label("Durée :");
	        Label priorityLabel = new Label("Priorité :");
	        Label categoryLabel = new Label("Catégorie :");
	        Label colorLabel = new Label("Couleur :");
	        Label deadlineLabel = new Label("Date limite :");
	        Label periodicityLabel = new Label("Périodicité :");

	        // Création des boutons
	        Button applyButton = new Button("Appliquer");
	        Button cancelButton = new Button("Annuler");

	        // Création de la grille pour organiser les composants
	        GridPane gridPane = new GridPane();
	        gridPane.setPadding(new Insets(10));
	        gridPane.setVgap(10);
	        gridPane.setHgap(10);

	        applyButton.setStyle("-fx-background-color: #191A43; -fx-text-fill: white;");
	        cancelButton.setStyle("-fx-background-color: #191A43; -fx-text-fill: white;");

	     
	        // Placement des composants dans la grille
	        gridPane.add(taskNameLabel, 0, 0);
	        gridPane.add(taskNameField, 1, 0);
	        gridPane.add(durationLabel, 0, 1);
	        gridPane.add(durationSpinner, 1, 1);
	        gridPane.add(priorityLabel, 0, 2);
	        gridPane.add(priorityChoiceBox, 1, 2);
	        gridPane.add(categoryLabel, 0, 3);
	        gridPane.add(categoryChoiceBox, 1, 3);
	        gridPane.add(colorLabel, 0, 4);
	        gridPane.add(colorPicker, 1, 4);
	        gridPane.add(deadlineLabel, 0, 5);
	        gridPane.add(deadlineDatePicker, 1, 5);
	        gridPane.add(periodicityLabel, 0, 6);
	        gridPane.add(periodicityField, 1, 6);
	        // Création de la boîte de dialogue
	        Dialog<ButtonType> dialog = new Dialog<>();
	        dialog.setTitle("Ajouter une tâche");
	        dialog.initModality(Modality.WINDOW_MODAL);
	       
	        dialog.setResizable(false);
	        // Personnalisation de la boîte de dialogue
	   
	        dialog.getDialogPane().setStyle("-fx-background-color: #FBEDEC;");
	        dialog.getDialogPane().getStyleClass().add("dialog-pane");

	        // Configuration des boutons de la boîte de dialogue
	        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
	        dialog.getDialogPane().lookupButton(ButtonType.APPLY).disableProperty().bind(
	                taskNameField.textProperty().isEmpty()
	                        .or(priorityChoiceBox.valueProperty().isNull())
	        );

	        // Placement de la grille dans le contenu de la boîte de dialogue
	        VBox content = new VBox(gridPane);
	        dialog.getDialogPane().setContent(content);

	        // Récupération des résultats lorsque le bouton "Appliquer" est cliqué
	        dialog.setResultConverter(dialogButton -> {
	            if (dialogButton == ButtonType.APPLY) {
	                String taskName = taskNameField.getText();
	                Duration duration = Duration.ofMinutes(durationSpinner.getValue());
	                Priorite priority = priorityChoiceBox.getValue();
	                Categorie category = categoryChoiceBox.getValue();
	                Color color = colorPicker.getValue();
	                LocalDate deadline = deadlineDatePicker.getValue();
	                int periodicity = Integer.parseInt(periodicityField.getText());
	                // Faites quelque chose avec les valeurs récupérées
	                Tache tache = application.identifierTache(taskName, duration, deadline , priority , color , category , periodicity );
	                queue.add(tache);
	            }
	            return null;
	        });

	        // Affichage de la boîte de dialogue
	        dialog.showAndWait();
	    }
	    
	    
	    
	    public void AjouterEnsembleTaches() {
	PriorityQueue<Tache> queue = new PriorityQueue<>(Comparator.comparing(Tache::getDeadline)
            .thenComparing((t1, t2) -> t2.getPriorite().compareTo(t1.getPriorite())));
	int nbr = showDialogDetectNumber();
	for (int j = 0; j < nbr; j++) {
		showDialogEnsemble(queue);
	}
	 while (!queue.isEmpty()) {
         Tache task = queue.poll();
         //System.out.println(task.getNom() + " - Priorité : " + task.getPriorite() + " - deadline : "
           //      + task.getDeadline());
         if (task instanceof TacheSimple)
        	 application.autoPlanifierTacheSimple((TacheSimple) task);
         else { 
        	 application.autoPlanifierTacheDecomposable((TacheComplex) task);
        	 application.getUtilisateurActuel().AjouterTacheComplexe((TacheComplex) task);
        	 };
     }
}	    
	    
	    
//--------------------------------------------------------3fssa jdiida -----------------------------------------
	    
	    private void showDialogProjet(Projet p) {

	        // Création des champs de saisie
	        TextField taskNameField = new TextField();
	        Spinner<Integer> durationSpinner = new Spinner<>(1, Integer.MAX_VALUE, 1);
	        ChoiceBox<Priorite> priorityChoiceBox = new ChoiceBox<>();
	        priorityChoiceBox.getItems().addAll(Priorite.values());
	        ChoiceBox<Categorie> categoryChoiceBox = new ChoiceBox<>();
	        categoryChoiceBox.getItems().addAll(Categorie.values());
	        DatePicker deadlineDatePicker = new DatePicker();
	        TextField periodicityField = new TextField();

	        // Création des étiquettes
	        Label taskNameLabel = new Label("Nom de la tâche :");
	        Label durationLabel = new Label("Durée :");
	        Label priorityLabel = new Label("Priorité :");
	        Label categoryLabel = new Label("Catégorie :");
	        Label deadlineLabel = new Label("Date limite :");
	        Label periodicityLabel = new Label("Périodicité :");

	        // Création des boutons
	        Button applyButton = new Button("Appliquer");
	        Button cancelButton = new Button("Annuler");

	        // Création de la grille pour organiser les composants
	        GridPane gridPane = new GridPane();
	        gridPane.setPadding(new Insets(10));
	        gridPane.setVgap(10);
	        gridPane.setHgap(10);

	        // Placement des composants dans la grille
	        gridPane.add(taskNameLabel, 0, 0);
	        gridPane.add(taskNameField, 1, 0);
	        gridPane.add(durationLabel, 0, 1);
	        gridPane.add(durationSpinner, 1, 1);
	        gridPane.add(priorityLabel, 0, 2);
	        gridPane.add(priorityChoiceBox, 1, 2);
	        gridPane.add(categoryLabel, 0, 3);
	        gridPane.add(categoryChoiceBox, 1, 3);
	        gridPane.add(deadlineLabel, 0, 5);
	        gridPane.add(deadlineDatePicker, 1, 5);
	        gridPane.add(periodicityLabel, 0, 6);
	        gridPane.add(periodicityField, 1, 6);

	        // Création de la boîte de dialogue
	        Dialog<ButtonType> dialog = new Dialog<>();
	        dialog.setTitle("Ajouter une tâche");
	        dialog.initModality(Modality.WINDOW_MODAL);
	        dialog.setResizable(false);

	        // Configuration des boutons de la boîte de dialogue
	        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
	        dialog.getDialogPane().lookupButton(ButtonType.APPLY).disableProperty().bind(
	                taskNameField.textProperty().isEmpty()
	                        .or(priorityChoiceBox.valueProperty().isNull())
	        );

	        // Placement de la grille dans le contenu de la boîte de dialogue
	        VBox content = new VBox(gridPane);
	        dialog.getDialogPane().setContent(content);

	        // Modifier le style de la boîte de dialogue
	        dialog.getDialogPane().setStyle("-fx-background-color: #FBEDEC;");

	        // Modifier le style du bouton "Appliquer"
	        applyButton.setStyle("-fx-background-color: #191A43; -fx-text-fill: white;");

	        // Augmenter la taille du texte
	        taskNameLabel.setStyle("-fx-font-size: 14pt;");
	        durationLabel.setStyle("-fx-font-size: 14pt;");
	        priorityLabel.setStyle("-fx-font-size: 14pt;");
	        categoryLabel.setStyle("-fx-font-size: 14pt;");
	        deadlineLabel.setStyle("-fx-font-size: 14pt;");
	        periodicityLabel.setStyle("-fx-font-size: 14pt;");

	        // Récupération des résultats lorsque le bouton "Appliquer" est cliqué
	        dialog.setResultConverter(dialogButton -> {
	            if (dialogButton == ButtonType.APPLY) {
	                String taskName = taskNameField.getText();
	                Duration duration = Duration.ofMinutes(durationSpinner.getValue());
	                Priorite priority = priorityChoiceBox.getValue();
	                Categorie category = categoryChoiceBox.getValue();
	                LocalDate deadline = deadlineDatePicker.getValue();
	                int periodicity = Integer.parseInt(periodicityField.getText());
	                // Faites quelque chose avec les valeurs récupérées
	                Tache tache = application.identifierTache(p.getNom() + " - " + taskName, duration, deadline, priority, Color.valueOf(p.getColor()), category, periodicity);
	                p.ajouterTache(tache);
	            }
	            return null;
	        });

	        // Affichage de la boîte de dialogue
	        dialog.showAndWait();
	    }	    
	    
	    
	    public void CreerProjet()
	    {
	    	showDialogProject();
	    	
	    }
	    
	    public void showDialogProject() {
	        Stage dialogStage = new Stage();
	        dialogStage.initModality(Modality.APPLICATION_MODAL);
	        dialogStage.setTitle("Créer un nouveau projet");
	        dialogStage.setResizable(false);

	        // Création des champs de saisie
	        TextField nameField = new TextField();
	        nameField.setPromptText("Nom du projet");

	        TextArea descriptionField = new TextArea();
	        descriptionField.setPromptText("Description du projet");
	        ColorPicker colorPicker = new ColorPicker();


	        TextField tasksField = new TextField();
	        tasksField.setPromptText("Nombre de tâches");
	        final String projectName[]= {""};
	        String projectDescription[] = {""};
	        int numberOfTasks[] = {0};
	        // Création du bouton Confirmer
	        Button confirmButton = new Button("Confirmer");
	        confirmButton.setDefaultButton(true);
	        confirmButton.setOnAction(e -> {
	        	Color color = colorPicker.getValue();
	            projectName[0] = nameField.getText();
	            projectDescription[0] = descriptionField.getText();
	            try {
	                numberOfTasks [0]= Integer.parseInt(tasksField.getText());
	                Projet projet = new Projet(projectName[0] ,projectDescription[0] , numberOfTasks [0], convertColorToString(color) );
	                for(int i=0 ; i<  numberOfTasks [0] ; i++) {
	                showDialogProjet(projet);
	                }
                //affichage de projet
	                for (Tache tache : projet.getTaches()) {
	                	System.out.print("-----"+tache.getNom());
	                	if (tache instanceof TacheComplex) application.autoPlanifierTacheDecomposable((TacheComplex)tache);
	                	else application.autoPlanifierTacheSimple((TacheSimple)tache);
	                }
	                dialogStage.close();
	            } catch (NumberFormatException ex) {
	                System.out.println("Veuillez entrer un nombre entier pour le nombre de tâches.");
	            }
	        });
	        

	        // Création de la grille pour organiser les composants
	        GridPane gridPane = new GridPane();
	        gridPane.setPadding(new Insets(10));
	        gridPane.setHgap(10);
	        gridPane.setVgap(10);
	        Label colorLabel = new Label("Couleur :");
	        gridPane.add(new Label("Nom du projet:"), 0, 0);
	        gridPane.add(nameField, 1, 0);
	        gridPane.add(new Label("Description du projet:"), 0, 1);
	        gridPane.add(descriptionField, 1, 1);
	        gridPane.add(new Label("Nombre de tâches:"), 0, 2);
	        gridPane.add(tasksField, 1, 2);
	        gridPane.add(colorLabel, 0, 3);
	        gridPane.add(colorPicker, 1, 3);
	        gridPane.add(confirmButton, 0, 3, 2, 1);
	        
	        Scene dialogScene = new Scene(gridPane);
	        dialogStage.setScene(dialogScene);

	        dialogStage.showAndWait();
	    }
	    
	    public  String convertColorToString(Color color) {
	        // Obtenir les composantes RVB
	        int red = (int) (color.getRed() * 255);
	        int green = (int) (color.getGreen() * 255);
	        int blue = (int) (color.getBlue() * 255);

	        // Créer la représentation en chaîne de caractères
	        String colorString = String.format("#%02X%02X%02X", red, green, blue);

	        return colorString;
	    }

	    
	    
//--------------------------------------------------------3fssa jdiida -----------------------------------------
	    
	    public void afficherBadge() {
	    	application.getUtilisateurActuel().setBadge();
	    	System.out.println("le bedge  li 3ndek homa --- : "+application.getUtilisateurActuel().getBadge());
	    	String msg="";
	    	if(application.getUtilisateurActuel().getBadge() ==null) msg = "Malheureusement vous n'avez pas de badges. ";
	    	if(application.getUtilisateurActuel().getBadge() ==Badge.GOOD) msg = "vous avez un badge Good";
	    	if(application.getUtilisateurActuel().getBadge() ==Badge.VERY_GOOD) msg = "vous avez les deux badges Good et VeryGood";
	    	if(application.getUtilisateurActuel().getBadge() ==Badge.EXCELLENT) msg = "vous avez les trois badges Good, VeryGood et Excellent";
	    	showDialogBadge(msg);
	    }


	    
	    
	    public void showDialogBadge(String message) {
	        Stage primaryStage = new Stage();
	        primaryStage.initModality(Modality.APPLICATION_MODAL);
	        primaryStage.setTitle("Les badges que vous avez eu");

	        // Création du label vide
	        Label label = new Label();
	        label.setText(message);

	        // Création du bouton "D'accord" pour fermer le dialogue
	        Button button = new Button("D'accord");
	        button.setStyle("-fx-background-color: #191A43; -fx-text-fill: white;");

	        // Augmenter la taille du texte
	        label.setStyle("-fx-font-size: 14pt;");

	        // Augmenter la taille du dialogue
	        primaryStage.setWidth(900.0);
			primaryStage.setHeight(400.0);

	        button.setOnAction(e -> primaryStage.close());

	        // Création de la disposition et ajout des éléments
	        VBox vbox = new VBox(10);
	        vbox.setAlignment(Pos.CENTER);
	        vbox.getChildren().addAll(label, button);

	        // Création de la scène et ajout à la fenêtre du dialogue
	        Scene scene = new Scene(vbox);
	        scene.getRoot().setStyle("-fx-background-color: #FBEDEC;");
	        primaryStage.setScene(scene);

	        // Affichage du dialogue
	        primaryStage.showAndWait();
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    //*********************************************
	    public void deconnecter(ActionEvent event) throws IOException {
	    	
	    	application.setUtilisateur(null);
	    	
	    	Parent root = FXMLLoader.load(getClass().getResource("Loginpage.fxml"));
	    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	scene = new Scene(root);
	    	scene.getStylesheets().add(getClass().getResource("Firstpage.css").toExternalForm());
	    	stage.setWidth(1536.0);
	    	stage.setHeight(816.0);
	    	stage.setScene(scene);
	    	stage.show();
	    	
	    }
	    public static int showDialogDetectNumber2() {
	        Stage dialogStage = new Stage();
	        dialogStage.initModality(Modality.APPLICATION_MODAL);
	        dialogStage.setTitle("Entrez le nombre de jour pour elater votre planning");
	        dialogStage.setResizable(false);

	        // Création du champ de saisie de nombre entier
	        TextField inputField = new TextField();
	        inputField.setPromptText("Entrez un nombre entier");

	        // Création du bouton Confirmer
	        Button confirmButton = new Button("Confirmer");
	        confirmButton.setDefaultButton(true);
	        final int[] choix = {0};
	        confirmButton.setOnAction(e -> {
	            String input = inputField.getText();
	            try {
	                int numberOfTasks = Integer.parseInt(input);
	                choix[0] = numberOfTasks;
	                System.out.println("Nombre de jour à ajouter : " + numberOfTasks);
	                dialogStage.close();
	            } catch (NumberFormatException ex) {
	                System.out.println("Saisie invalide. Veuillez entrer un nombre entier.");
	            }
	        });

	        // Création de la mise en page de la boîte de dialogue
	        VBox vbox = new VBox(10);
	        vbox.setPadding(new Insets(10));
	        vbox.setStyle("-fx-background-color: #FBEDEC;");
	        vbox.getChildren().addAll(new Label("Entrez le nombre de tâches :"), inputField, confirmButton);

	        Scene dialogScene = new Scene(vbox, 300, 150);
	        dialogStage.setScene(dialogScene);
	        dialogStage.showAndWait();
	        return choix[0];
	    }
	    
	    public void elaterPeriode() {
	    	application.getUtilisateurActuel().getCalendrier().elater(showDialogDetectNumber2());
	    	drawCalendar();
	    	
	    }
	    //*********************************************
	    

}



