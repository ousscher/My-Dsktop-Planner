package Model;

import java.io.Serializable;

import java.time.*;

public class Crineau implements Serializable , Comparable<Crineau>{
	 static public Duration dureeMinimum = Duration.ofMinutes(30);
	    private LocalTime heureDebut;
	    private LocalTime heureFin;
	    private Boolean EstDisponible;
	    private Tache tache;
	    /*
	     * decomposer crenau
	     * modifier creneau
	     * supprimer creaneau
	     * calculer durée ---done
	     */

	    /* <constructeur - setteurs - guetteurs> */
	    public Crineau(LocalTime heureDebut, LocalTime heureFin, Tache tache) {
	        this.heureDebut = heureDebut;
	        this.heureFin = heureFin;
	        this.EstDisponible = true;
	        this.tache = tache;
	    }

	    public void setHeureDebut(LocalTime h) {
	        this.heureDebut = h;
	    }

	    public LocalTime getHeureDebut() {
	        return this.heureDebut;
	    }

	    public void setHeureFin(LocalTime h) {
	        this.heureFin = h;
	    }

	    public LocalTime getHeureFin() {
	        return this.heureFin;
	    }

	    public void setEstDisponible(Boolean d) {
	        this.EstDisponible = d;
	    }

	    public boolean getEstDisponible() {
	        return this.EstDisponible;
	    }

	    public void setTache(Tache tache) {
	        this.tache = tache;
	    }

	    public Tache getTache() {
	        return this.tache;
	    }
	    /* </constructeur - setteurs - guetteurs> */

	    public Duration calculerDureeCrineau() {
	        long minutes = (Duration.between(heureDebut, heureFin)).toMinutes();
	        return Duration.ofMinutes(minutes);
	    }

	    public void afficherCrineau() {
	        System.out.println("Ce crineau commence à " + this.heureDebut + " et se termine à " + this.heureFin);
	    }

	    public Crineau ajouterTache(Tache tache) { // calcul la durée restante et retourne un nouveau creneau si il reste
	                                               // encore du temps libre
	        this.tache = tache;
	        this.EstDisponible = false;
	        Duration difference = this.calculerDureeCrineau().minus(tache.getDuree());
	        if (difference.compareTo(Crineau.dureeMinimum) >= 0) {
	            Crineau c = new Crineau(heureDebut.plus(tache.getDuree()), heureFin, null);
	            this.heureFin = heureDebut.plus(tache.getDuree());
	            return c;
	        } else {
	            this.heureFin = heureDebut.plus(tache.getDuree());
	            return null;
	        }
	    }

	    public int compareTo(Crineau c) {
	        return this.heureDebut.compareTo(c.heureDebut);
	    }

}