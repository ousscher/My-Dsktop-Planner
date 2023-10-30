package application;

import java.io.IOException;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.TreeSet;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import Model.Categorie;
import Model.Couleur;
import Model.Crineau;
import Model.DesktopPlaner;
import Model.Jour;
import Model.Priorite;
import Model.*;
import javafx.scene.text.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class Daypage_controller implements Initializable {
	
	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root;
    @FXML
    private FlowPane Daypane;
    
    private static DesktopPlaner application;
    @FXML
    private Button gobackh ;
   
    private static Jour jour ;
    @FXML
    Label labelDate ;
    
    @FXML 
    Button addcreneau;
    @FXML
    private ScrollPane scrlpn;
    
    private static TreeSet<Crineau> daycrineaux;
     
    public static void setcrn(TreeSet<Crineau> e , DesktopPlaner d , Jour p) {
    	daycrineaux = e;
    	application = d;
    	jour = p;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	scrlpn.setPickOnBounds(false);
    	Daypane.setPickOnBounds(false);
    	String staticText ="Le Jour : ";

    	String labelText = staticText + jour.getDate().toString();
    	labelDate.setText(labelText);
    	drawDay();
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
    public static void showDialogEtat(Crineau c ) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle("Modifier l'état de la tâche");
        dialogStage.setResizable(false);

        // Création du ChoiceBox avec les éléments de l'énumération Etat
        ChoiceBox<Etat> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(Etat.values());

        // Création du bouton Confirmer
        Button confirmButton = new Button("Confirmer");
        confirmButton.setDefaultButton(true);
        confirmButton.setOnAction(e -> {
            Etat selectedEtat = choiceBox.getValue();
            if (selectedEtat == Etat.COMPLETED) {
            	jour.setTachesAccomplis( jour.getTachesAccomplis()+1);
            	System.out.println ("------"+ jour.getTachesAccomplis() );
            }
            System.out.println("État sélectionné : " + selectedEtat);
            c.getTache().setEtat(selectedEtat);
            dialogStage.close();

        });

        // Création de la mise en page de la boîte de dialogue
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(new Label("Sélectionnez l'état de la tâche :"), choiceBox, confirmButton);
        
        Scene dialogScene = new Scene(vbox);
        dialogStage.setScene(dialogScene);

        // Assurez-vous de fermer correctement la boîte de dialogue lorsque la fenêtre principale est fermée
        dialogStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

        dialogStage.showAndWait();
        
    }

    private void drawDay() {
    	scrlpn.setPickOnBounds(false);
    	Daypane.setPickOnBounds(false);
    	Daypane.getChildren().clear();
        double calendarWidth = Daypane.getPrefWidth();
        double calendarHeight = Daypane.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = Daypane.getHgap();
        double spacingV = Daypane.getVgap();
       
        double rectangleWidth =  1000;
        double rectangleHeight = 120.0;
        double spacing = 10.0;
        Daypane.setHgap(spacing); // Espacement horizontal
        Daypane.setVgap(spacing); 

        for (Crineau crineau : daycrineaux) {
            Rectangle rectangle = new Rectangle(rectangleWidth, rectangleHeight);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(Color.LIGHTGRAY);
            
            if( crineau.getTache()!=null) {
            rectangle.setFill(Color.valueOf( crineau.getTache().getCouleur()));
            }
            
            rectangle.setOnMouseClicked(event -> {
                System.out.println("clicked ! ! ! ! ! ! ");
            });
            
            // Create and configure the Text node
            
            Text leftText = new Text(crineau.getHeureDebut() + "-----" + crineau.getHeureFin());
            leftText.setTextAlignment(TextAlignment.LEFT);
            leftText.setFill(Color.BLACK);
            leftText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            Text centerText = new Text("         ");
            Text txt = new Text("");
            if (! crineau.getEstDisponible()) {
            	txt = new Text("Deadline = "+crineau.getTache().getDeadline());
            }
            Text rightText = (crineau.getEstDisponible()? new Text("(creneau vide)") :new  Text(crineau.getTache().getNom()));
            Text text = new Text("") ;
            if (crineau.getTache()!=null)
            text = new Text(crineau.getTache().getEtat().toString());
            rightText.setTextAlignment(TextAlignment.LEFT);
            rightText.setFill(Color.BLACK);
            rightText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            Text t = new Text("\n");
            VBox vbox1 = new VBox(t, leftText , rightText , text , txt);
            // Add the Text node to the StackPane
            HBox hbox = new HBox( centerText , vbox1 );
            Insets insets = new Insets(0, 10, 10, 10); // Espacement à gauche (10 pixels dans cet exemple)
            HBox.setMargin(vbox1, insets);
            hbox.setAlignment(Pos.CENTER_LEFT);
            StackPane stackPane;
         
            if (crineau.getEstDisponible()) {
            Button addtsk = new Button();
            addtsk.setText("Ajouter une tache");
            addtsk.setAlignment(Pos.BOTTOM_RIGHT);
            addtsk.setStyle("-fx-background-color: #191A43; -fx-text-fill: white;");
            addtsk.setOnMouseClicked((e)->ajouterTache(crineau));
             stackPane = new StackPane(rectangle, hbox, addtsk);
            }else 
            {
            	Button rmtsk = new Button();
                rmtsk.setText("Modifier etat la tache");
                rmtsk.setAlignment(Pos.BOTTOM_RIGHT);
            	 stackPane = new StackPane(rectangle, hbox , rmtsk);
            	 rmtsk.setStyle("-fx-background-color: #191A43; -fx-text-fill: white;");
            	 
            	 rmtsk.setOnMouseClicked((e)->showDialogEtat(crineau));
            	
            }
            Daypane.getChildren().add(stackPane);
            
   
            
            Daypane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                Node target = event.getPickResult().getIntersectedNode();
                if (target != null && target.equals(rectangle)) {
                    // Click event occurred on the rectangle
                    event.consume(); // Prevent the event from reaching other components
                }
            });
            
      
        }
    }
    
    
public void goback(ActionEvent event2) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("Menuonepage.fxml"));
		
		stage = (Stage)((Node)event2.getSource()).getScene().getWindow();  
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("Firstpage.css").toExternalForm());
		stage.setWidth(1536);
		stage.setHeight(816);
		stage.setScene(scene);
		stage.show();
		
	}

private void showDialog() {
    // Création des spinners pour les heures de début et de fin
    Spinner<Integer> startHourSpinner = new Spinner<>(0, 23, 0);
    Spinner<Integer> startMinuteSpinner = new Spinner<>(0, 59, 0);
    Spinner<Integer> endHourSpinner = new Spinner<>(0, 23, 0);
    Spinner<Integer> endMinuteSpinner = new Spinner<>(0, 59, 0);

    // Personnalisation des spinners
    startHourSpinner.setPrefWidth(200);
    endHourSpinner.setPrefWidth(200);
    startMinuteSpinner.setPrefWidth(200);
    endMinuteSpinner.setPrefWidth(200);

    // Création des étiquettes pour les heures de début et de fin
    Label startHourLabel = new Label("Heure début :");
    Label startMinuteLabel = new Label("Minute début:");
    Label endHourLabel = new Label("Heure fin:      ");
    Label endMinuteLabel = new Label("Minute fin:     ");

    // Création du conteneur pour les spinners d'heures
    HBox startHourBox = new HBox(10, startHourLabel, startHourSpinner);
    HBox endHourBox = new HBox(10, endHourLabel, endHourSpinner);
    startHourBox.setAlignment(Pos.CENTER_LEFT);
    endHourBox.setAlignment(Pos.CENTER_LEFT);

    // Création du conteneur pour les spinners de minutes
    HBox startMinuteBox = new HBox(10, startMinuteLabel, startMinuteSpinner);
    HBox endMinuteBox = new HBox(10, endMinuteLabel, endMinuteSpinner);
    startMinuteBox.setAlignment(Pos.CENTER_LEFT);
    endMinuteBox.setAlignment(Pos.CENTER_LEFT);

    // Création du conteneur pour le contenu de la boîte de dialogue
    GridPane content = new GridPane();
    content.setHgap(10);
    content.setVgap(10);
    content.setPadding(new Insets(10));
    content.add(startHourBox, 0, 0);
    content.add(startMinuteBox, 1, 0);
    content.add(endHourBox, 0, 1);
    content.add(endMinuteBox, 1, 1);

    // Création du label "Ajout d'un nouveau créneau"
    Label titleLabel = new Label("Ajout d'un nouveau créneau");
    titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    titleLabel.setAlignment(Pos.CENTER);

    // Création du conteneur pour le contenu de la boîte de dialogue avec le titre
    VBox dialogContent = new VBox(10, titleLabel, content);
    dialogContent.setAlignment(Pos.CENTER);

    // Création des boutons OK et Annuler
    ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
    ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);

    // Création de la boîte de dialogue
    Dialog<Void> dialog = new Dialog<>();
    dialog.setTitle("Sélectionnez les heures");
    dialog.getDialogPane().setContent(dialogContent);
    dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);

    // Personnalisation de la boîte de dialogue
    dialog.getDialogPane().setPrefSize(800, 491);

    // Récupération des boutons de la boîte de dialogue
    Button okButtonControl = (Button) dialog.getDialogPane().lookupButton(okButton);
    Button cancelButtonControl = (Button) dialog.getDialogPane().lookupButton(cancelButton);

    // Personnalisation des boutons
    okButtonControl.setStyle("-fx-background-color: #191A43; -fx-text-fill: white;");
    cancelButtonControl.setStyle("-fx-background-color: #191A43; -fx-text-fill: white;");

    // Personnalisation de la boîte de dialogue
    dialog.getDialogPane().setStyle("-fx-background-color: #FBEDEC;");

    // Gestion des événements des boutons
    okButtonControl.setOnAction(e -> {
        int startHour = startHourSpinner.getValue();
        int endHour = endHourSpinner.getValue();
        int startMinute = startMinuteSpinner.getValue();
        int endMinute = endMinuteSpinner.getValue();
        LocalTime heureDebut = LocalTime.of(startHour, startMinute);
        LocalTime heureFin = LocalTime.of(endHour, endMinute);
        if (!jour.isCreneauValide(heureDebut, heureFin)) {
            System.out.println("Les heures du créneau sont invalides ou se chevauchent. Veuillez réessayer.");
            titleLabel.setText("Erreur : les heures du créneau sont invalides ou se chevauchent.");
            titleLabel.setTextFill(Color.RED);
            dialog.showAndWait();
        } else {
            double rectangleWidth = 1000;
            double rectangleHeight = 120.0;
            Crineau cs = new Crineau(heureDebut, heureFin, null);
            jour.ajouterCrineau(cs);
            Rectangle rectangle = new Rectangle(rectangleWidth, rectangleHeight);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(Color.LIGHTGRAY);
            StackPane stackPane = new StackPane(rectangle);
            // Create and configure the Text node
            Text text = new Text(cs.getHeureDebut() + "-----" + cs.getHeureFin());
            text.setTextAlignment(TextAlignment.LEFT);
            text.setFill(Color.BLACK);
            text.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            // Add the Text node to the StackPane
            stackPane.getChildren().add(text);
            Daypane.getChildren().add(stackPane);
            drawDay();
            dialog.close();
        }
    });
    cancelButtonControl.setOnAction(e -> dialog.close());

    // Affichage de la boîte de dialogue
    dialog.showAndWait();
}
private void showDialog2(Crineau c) {
    // Création des champs de saisie
    TextField taskNameField = new TextField();
    Spinner<Integer> durationSpinner = new Spinner<>(1, Integer.MAX_VALUE, 1);
    ChoiceBox<Priorite> priorityChoiceBox = new ChoiceBox<>();
    priorityChoiceBox.getItems().addAll(Priorite.values());

    ChoiceBox<Categorie> categoryChoiceBox = new ChoiceBox<>();
    categoryChoiceBox.getItems().addAll(Categorie.values());
    ColorPicker colorPicker = new ColorPicker();
    DatePicker deadlineDatePicker = new DatePicker();

    // Création des étiquettes
    Label taskNameLabel = new Label("Nom de la tâche :");
    Label durationLabel = new Label("Durée :");
    Label priorityLabel = new Label("Priorité :");
    Label categoryLabel = new Label("Catégorie :");
    Label colorLabel = new Label("Couleur :");
    Label deadlineLabel = new Label("Date limite :");

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

    // Placement de la grille dans le contenu de la boîte de dialogue
    VBox content = new VBox(gridPane);
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

            if (duration.compareTo(c.calculerDureeCrineau()) <= 0) {
                TacheSimple tache = new TacheSimple(taskName, duration, priority, category, convertColorToString(color), deadline, 0);
                c.setEstDisponible(false);
                Crineau cs = c.ajouterTache(tache);

                if (cs != null) {
                    daycrineaux.add(cs);
                }

                drawDay();
            }
        }
        return null;
    });

    // Affichage de la boîte de dialogue
    dialog.showAndWait();
}
public void ajouterCreneau(ActionEvent event) {
    showDialog();
}

public void ajouterTache(Crineau c) {
	showDialog2(c);
}


}