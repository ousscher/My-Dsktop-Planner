package Utilisateur;

public class Utilisateur{
    private String psuedo;
    Calendrier calendrier;
    public Utilisateur(String psuedo , Calendrier calendrier){
        this.psuedo = psuedo;
        this.calendrier = calendrier;
    }
    public Utilisateur(String psuedo){
        this.psuedo = psuedo;
    }
    public String getPsuedo(){
        return this.psuedo;
    }
    public void ajouterCalendrier(Calendrier calendrier){
        this.calendrier = calendrier;
    }
    public Calendrier getCalendrier(){
        return this.calendrier;
    }
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Utilisateur autreUtilisateur = (Utilisateur) obj;
        return Objects.equals(psuedo, autreUtilisateur.psuedo);
    }
    public int hashCode (){
        return psuedo.hashCode();
    }
    public void afficherCalendrierUtilisateur(){
                    System.out.println("votre calendrier contient "+ calendrier.getPeriode() + "jours");
                    int j = 1;
                    for (Jour jour :this.calendrier.getJours() ){
                        System.out.println("-------------Affichage du jour "+ j +" ------------");
                        j++;
                        for (Crineau crineau : jour.getCrineaux()){
                            System.out.println("ce crineau commence à " + crineau.getHeureDebut() + "et se termine à "+ crineau.getHeureFin() );
                        }
                    }
    }
    /*
    AjouterTask (task , creneau )
    demandePlanife()
    valider()
    anuller()
    ajouterprojet
    calcstatis
    reporter

     */
    
}
