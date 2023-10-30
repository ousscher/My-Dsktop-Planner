package Model;

import java.io.Serializable;
import java.time.LocalDate;

import javafx.scene.paint.Color;
import javafx.util.Duration;

public class TacheSimple extends Tache implements Serializable{
	 private int pourcentageAvancement;

	    public TacheSimple(String nom, java.time.Duration duree, Priorite priorite, Categorie categorie, String couleur,
	            LocalDate deadline, int periodicite) {
	        super(nom, duree, priorite, categorie, couleur, deadline, periodicite);
	    }

		@Override
		public int compareTo(Tache o) {
			// TODO Auto-generated method stub
			return this.getDuree().compareTo(o.getDuree());
		}
}
