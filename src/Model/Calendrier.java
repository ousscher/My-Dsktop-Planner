package Model;
import java.io.Serializable;
import java.time.*;
import java.util.*;;

public class Calendrier implements Serializable{
	 private int periode;
	    private LocalDate dateDebut;
	    private List<Jour> jours;
	    private int periodicite;

	    // initialiserCalendrier() --- done
	    // les constructeurs - les setteurs et les guetteurs
	    public Calendrier(int periode,  LocalDate date) {
	        this.periode = periode;
	        jours = new ArrayList<>();
	        this.dateDebut = date;
	    }

	    public int getPeriode() {
	        return this.periode;
	    }

	    public void setPeriode(int periode) {
	        this.periode = periode;
	    }

	    public LocalDate getDateDebut() {
	        return this.dateDebut;
	    }

	    public void setDateDebut(LocalDate date) {
	        this.dateDebut = date;
	    }

	    public List<Jour> getJours() {
	        return this.jours;
	    }

	    public int getPeriodicite() {
	        return this.periodicite;
	    }

	    public void setPeriodicite(int periodicite) {
	        this.periodicite = periodicite;
	    }

	    // les constructeurs - les setteurs et les guetteurs
	    public void ajouterJour(Jour jour) {
	        jours.add(jour);
	    }

	    public void RemplirCalendrier() {
	       for (int i=0; i<=periode;i++ ) {
	    	   Jour jour = new Jour(this.dateDebut.plusDays(i));
	    	   jours.add(jour);
	       }
	    }
	    public void elater(int plus) {
	    	for(int i=periode+1 ; i<=periode + plus ; i++ ) {
	    		Jour jour = new Jour(this.dateDebut.plusDays(i));
		    	 jours.add(jour);
	    	}
	    	//this.periode =+plus;
	    	this.periode = this.periode + plus;
	    	System.out.print("---periode"+periode );
	    	for (Jour jour:jours) {
	    		System.out.println("---"+ jour.getDate());
	    	}
	    }
}