package Model;
import java.io.Serializable;
import java.util.*;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;;
public class Projet implements Serializable{
	 private String nom;
	    private String description;
	    private int nbrTaches;
	    private List<Tache> taches;
	    private Etat etat;
	    String couleur;

	    /*
	     * ajouterTache() --- done
	     * ....
	     */
	    public Projet(String nom, String description , int nbrTaches , String couleur) {
	        this.nom = nom;
	        this.description = description;
	        this.taches =new ArrayList<Tache>();
	        this.nbrTaches = nbrTaches;
	        this.couleur = couleur;
	    }

	    // <les seteurs et les guetteurs>
	    public void setNom(String nom) {
	        this.nom = nom;
	    }

	    public String getNom() {
	        return this.nom;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public String getDescription() {
	        return this.description;
	    }
	    public String getColor () {
	    	return this.couleur;
	    }

	    public List<Tache> getTaches() {
	        return this.taches;
	    }
	    // </les seteurs et les guetteurs>

	    public void ajouterTache(Tache tache) {
	        taches.add(tache);
	    }

	    public void configurerEtat() {
	        boolean allTasksDone = true;
	        boolean inProgress = false;
	        for (Tache tache : this.taches) {
	            if (tache.getEtat() != Etat.COMPLETED) {
	                allTasksDone = false;
	            }
	            if (tache.getEtat() == Etat.IN_PROGRESS) {
	                inProgress = true;
	                break;
	            }
	        }
	        if (allTasksDone) {
	            etat = Etat.COMPLETED;
	        } else if (inProgress) {
	            etat = Etat.IN_PROGRESS;
	        } else {
	            etat = Etat.DELAYED;
	        }
	    }

}



//Color color = colorPicker.getValue();
//convertColorToString(color)
