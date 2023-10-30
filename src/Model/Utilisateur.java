package Model;
import java.io.Serializable;
import java.util.*;

public class Utilisateur implements Serializable{
	  private String psuedo;
	  public static int nbrTachesRealises;
	  private Badge badge;
	  
	    private Calendrier calendrier;
	    private Set<TacheComplex> tachesComplexs;

	    public Utilisateur(String psuedo, Calendrier calendrier) {
	        this.psuedo = psuedo;
	        this.calendrier = calendrier;
	    }

	    public Utilisateur(String psuedo) {
	        this.psuedo = psuedo;
	    }

	    public String getPsuedo() {
	        return this.psuedo;
	    }

	    public void ajouterCalendrier(Calendrier calendrier) {
	        this.calendrier = calendrier;
	    }

	    public Calendrier getCalendrier() {
	        return this.calendrier;
	    }

	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null || getClass() != obj.getClass())
	            return false;
	        Utilisateur autreUtilisateur = (Utilisateur) obj;
	        return Objects.equals(psuedo, autreUtilisateur.psuedo);
	    }

	    public int hashCode() {
	        return psuedo.hashCode();
	    }

	    public void afficherCalendrierUtilisateur() {
	        System.out.println("votre calendriercomence à " + calendrier.getDateDebut() + " contient "
	                + calendrier.getPeriode() + "jours");
	        int j = 1;
	        for (Jour jour : this.calendrier.getJours()) {
	            System.out.println("-------------Affichage du jour " + j + " " + jour.getDate() + " ------------");
	            j++;
	            for (Crineau crineau : jour.getCrineaux()) {
	                System.out.println("------------------------------------------------------------------------------");
	                System.out.println("ce crineau commence à " + crineau.getHeureDebut() + "et se termine à "
	                        + crineau.getHeureFin());
	                if (crineau.getEstDisponible())
	                    System.out.println("ce crineau est vide");
	                else
	                    System.out.println("ce creneau contient la tache " + crineau.getTache().getNom());
	            }
	        }
	    }
	    public void setBadge()
	    {
	    	int nbr =0;
	    	for(Jour jr :this.calendrier.getJours())
	    	{
	    		System.out.println("le nombre de taches accomplies pour le jour "+jr.getDate()+" est "+jr.getTachesAccomplis());
	    		if (jr.getTachesAccomplis() >jr.nbrMinimaleParJour)
	    		{
	    			nbr++;
	    			System.out.println("nbr = ---" +nbr);
	    		}
	    	}
	    	if( 5<= nbr && nbr <15  )
	    	{
	    		 badge =  Badge.GOOD;
	    	}else {
	    		if(15<= nbr && nbr <45 )
	    		{
	    			 badge =  Badge.VERY_GOOD;	
	    		}else if(nbr >= 45)
	    		{
	    			 badge =  Badge.EXCELLENT;
	    		}
	    	}
	    }
	    
	    public void AjouterTacheComplexe(TacheComplex t){
	        tachesComplexs.add(t);
	    }
	    
	    public Badge getBadge () {
	    	return this.badge;
	    }
	    /*
	     * AjouterTask (task , creneau )
	     * demandePlanife()
	     * valider()
	     * anuller()
	     * ajouterprojet
	     * calcstatis
	     * reporter
	     * 
	     */
    
}
