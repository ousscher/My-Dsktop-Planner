package Model;
import java.io.Serializable;
import java.time.*;
import java.util.*;

import javafx.scene.paint.Color;;
public class TacheComplex extends Tache implements Serializable, Comparable <Tache>{
	private List<TacheSimple> sousTaches;

    public TacheComplex(String nom, Duration duree, Priorite priorite, Categorie categorie, String couleur,
            LocalDate deadline, int periodicite) {
        super(nom, duree, priorite, categorie, couleur, deadline, periodicite);
    }

	    /*
	     * diviserTache
	     * evaluation
	     */
	    public List<TacheSimple> geTacheSimples() {
	        return this.sousTaches;
	    }
	    
	  

	    public void decomposerTache(TreeMap<LocalDate, List<Crineau>> propos) { // decomposer la tache complexe en tache
	                                                                            // simples selon les propositions
	        this.sousTaches = new ArrayList<>();
	        int i = 1;
	        Duration duration1 = Duration.ofMinutes(0); // pour controller le reste des taches
	        Duration duration2 = this.getDuree(); // pour controller le reste des taches
	        for (Map.Entry<LocalDate, List<Crineau>> entry : propos.entrySet()) {
	            List<Crineau> crineaux = entry.getValue();
	            for (Crineau crineau : crineaux) {
	                duration1 = duration1.plus(crineau.calculerDureeCrineau());
	                if (duration1.compareTo(this.getDuree()) <= 0) {
	                    sousTaches.add(new TacheSimple(getNom() + i, crineau.calculerDureeCrineau(), getPriorite(),
	                            getCategorie(), getCouleur(), getDeadline(), getPeriodicite()));
	                    duration2 = duration2.minus(crineau.calculerDureeCrineau());
	                } else {
	                    sousTaches.add(new TacheSimple(getNom() + i, duration2, getPriorite(), getCategorie(), getCouleur(),
	                            getDeadline(), getPeriodicite()));
	                }
	                i++;
	            }
	        }
	    }

		
		@Override
		public int compareTo(Tache o) {
			// TODO Auto-generated method stub
			return this.getDuree().compareTo(o.getDuree());
		}
		}