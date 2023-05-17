import java.sql.Date;
import java.util.*;;


//tous ce qui concerne les taches
abstract class Tache {
    private String nom;
    private int duree;
    private Priorite priorite;
    private Categorie categorie;
    private Couleur couleur;
    private Date deadline;
    private int periodicite;
    private Etat etat;
    /*les constructeurs - settteurs - guetterus*/
    Tache(String nom , int duree , Priorite priorite , Categorie categorie , Couleur couleur, Date deadline, int periodicite, Etat etat){
        this.nom = nom;
        this.duree = duree;
        this.priorite = priorite;
        this.categorie = categorie;
        this.couleur = couleur;
        this.deadline = deadline;
        this.periodicite = periodicite;
        this.etat = etat;
    }
    public Etat getEtat(){
        return this.etat;
    }
    public String getNom(){
        return this.nom;
    }
    public int getDuree(){
        return this.duree;
    }
    public Categorie getCategorie(){
        return this.categorie;
    }
    public Couleur getCouleur(){
        return this.couleur;
    }
    public Date getDeadline(){
        return this.deadline;
    }
    public Priorite getPriorite(){
        return this.priorite;
    }
    public int getPeriodicite(){
        return this.periodicite;
    }

    /*les constructeurs - settteurs - guetterus*/
}
class TacheSimple extends Tache {
    private int pourcentageAvancement;
    public TacheSimple(String nom , int duree , Priorite priorite , Categorie categorie , Couleur couleur, Date deadline, int periodicite, Etat etat , int pourcentageAvancement){
        super(nom, duree, priorite, categorie, couleur, deadline, periodicite, etat);
        this.pourcentageAvancement = pourcentageAvancement;
    }
}
class TacheComplex extends Tache{
    private TacheSimple[] sousTaches;
    public TacheComplex(String nom , int duree , Priorite priorite , Categorie categorie , Couleur couleur, Date deadline, int periodicite, Etat etat){
        super(nom, duree, priorite, categorie, couleur, deadline, periodicite, etat);
    }
    /*
    diviserTache
    evaluation
     */
}

//les enumes
enum Etat {
    NOT_REALIZED,
    COMPLETED,
    IN_PROGRESS,
    CANCELLED,
    DELAYED
} 


enum Couleur{
    BLEU,
    RED,
    GREEN,

}
enum Priorite {
    HIGH,
    LOW,
    MEDIUM,
}
enum Categorie{
    STUDIES,
    WORK,
    HOBBY,
    SPORT,
    HEALTH,
}
// Les creneaux
class Crineau {
    static private int dureeMinimum = 30 ;
    private int heureDebut;
    private int heureFin;
    private Boolean EstDisponible;
    private Tache tache;
    /*
    decomposer crenau 
    modifier creneau 
    supprimer creaneau 
    calculer durée ---done
     */

    /*<constructeur - setteurs - guetteurs> */
    public Crineau(int heureDebut , int heureFin , boolean EstDisponible , Tache tache ){
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.EstDisponible = EstDisponible;
        this.tache = tache;
    }
    public void setHeureDebut(int h){
        this.heureDebut = h;
    }
    public int getHeureDebut(){
        return this.heureDebut;
    }
    public void setHeureFin(int h){
        this.heureFin = h;
    }
    public int getHeureFin(){
        return this.heureFin;
    }
    public void setEstDisponible(Boolean d){
        this.EstDisponible = d;
    }
    public boolean getEstDisponible(){
        return this.EstDisponible;
    }
    public void setTache(Tache tache){
        this.tache = tache;
    }
    public Tache getTache(){
        return this.tache;
    }
    /*</constructeur - setteurs - guetteurs> */
    
    public int calculerDureeCrineau(){
        return heureFin - heureDebut;
    }
    

}
class Jour{
    private List <Crineau> crineaux;
    private int minTaches; //le nombre minimale de taches pour qu'il soit recompensé
    private Date date;
    private int TachesAccomplis;
    /*
    initialiserCreneauxJour
    evaluerJournee
     */
    //constructeur - setteurs - guetteurs 
    public Jour(List<Crineau> crineaux , int minTaches , Date date , int TachesAccomplis ){
        this.crineaux = crineaux;
        this.minTaches = minTaches;
        this.date = date;
        this.TachesAccomplis = TachesAccomplis;
        crineaux = new ArrayList<>();
    }
    public Jour(){
        crineaux = new ArrayList<>();
    }
    
    public List <Crineau> getCrineaux (){ 
        return this.crineaux;
        
    }
    public void setCrineaux (List <Crineau> crineaux){
        this.crineaux = crineaux;
    }
    public int getMinTaches (){ 
        return this.minTaches;
    }
    public void setMintachs (int minTaches){
        this.minTaches = minTaches;
    }
    public Date getDate (){ 
        return this.date;
    }
    public void setDate (Date date){
        this.date = date;
    }
    public int getTachesAccomplis (){ 
        return this.TachesAccomplis;
    }
    public void setTachesAccomplis (int tachesAccomplis){
        this.TachesAccomplis = tachesAccomplis;
    }
    //constructeur - setteurs - guetteurs 
    public void incrementerTachesAccomplis(){
        this.TachesAccomplis++;
    }
    public void ajouterCrineau(Crineau c){
        crineaux.add(c);
    }
    public void initaliserCrineauxJour(int nbrCrineaux){
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        while(i<nbrCrineaux){
            System.out.print("precisez l'heure de debut du crineau numero "+ (i+1) + " : ");
            int heureDebut =scanner.nextInt();
            System.out.print("precisez l'heure de fin du crineau numero"+ (i+1) + " : ");
            int heureFin =scanner.nextInt();
            boolean acceptable = false;
            //test de l'heure 
            if ((heureDebut >=0) && (heureFin <= 23) && (heureFin>=0) && (heureFin <=24) ){
                if (heureFin > heureDebut){
                    if (crineaux.size()>0){
                        if (heureDebut >= crineaux.get(crineaux.size() - 1).getHeureFin()){
                            acceptable = true;
                        };
                    }else acceptable = true;
                }
            }
            if (acceptable){
                i++;
            ajouterCrineau(new Crineau(heureDebut,heureFin, false, null));}
            else System.out.println("verifiez que les heures introduites sont correctes et corehentes");
        }
    }

}

class Calendrier{
    private int periode;
    private Date dateDebut;
    private List<Jour> jours;
    private int periodicite;
    // initialiserCalendrier()
    //les constructeurs - les setteurs et les guetteurs
    public Calendrier(int periode , int periodicite ){
        this.periode = periode;
        this.periodicite = periodicite;
        jours = new ArrayList<>();
    }
    public int getPeriode(){
        return this.periode;
    }
    public void setPeriode(int periode) {
        this.periode = periode;
    }
    public Date getDateDebut(){
        return this.dateDebut;
    }
    public void setDateDebut(Date date) {
        this.dateDebut = date;
    }
    public List<Jour> getJours(){
        return this.jours;
    }
    public int getPeriodicité(){
        return this.periodicite;
    }
    public void setPeriodicite(int periodicite) {
        this.periodicite = periodicite;
    }

    //les constructeurs - les setteurs et les guetteurs
    public void ajouterJour(Jour jour){
        jours.add(jour);
    }
    public void RemplirCalendrier(){
        int p = (this.periodicite == 0)?periode : periodicite;
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < p ; i++ ){
            Jour jour = new Jour();
            System.out.println("Donne le nombre de crineaux dans le jour " + (i+1));
            jour.initaliserCrineauxJour(scanner.nextInt());
            ajouterJour(jour);
        }
        if (this.periodicite != 0){
            for (int i=this.periodicite ; i<periode;i++ ){
                Jour j = jours.get(i % this.periodicite);
                ajouterJour(j);
            }
        }
    }
}
class Projet{
    private String nom;
    private String description;
    private List<Tache> taches;
    private Etat etat;
    /*
    ajouterTache() --- done
    ....
     */
    public Projet (String nom , String description , List<Tache> taches ){
        this.nom = nom;
        this.description = description;
        this.taches = taches;
    }
    // <les seteurs et les guetteurs>
    public void setNom(String nom){
        this.nom = nom;
    }
    public String getNom(){
        return this.nom;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public List<Tache> getTaches(){
        return this.taches;
    }
    // </les seteurs et les guetteurs>

    public void ajouterTache (Tache tache){
        taches.add(tache);
    }

    public void configurerEtat(){
        boolean allTasksDone = true;
        boolean inProgress = false;
        for(Tache tache : this.taches){
            if(tache.getEtat() != Etat.COMPLETED){
                allTasksDone = false;
            }
            if (tache.getEtat() ==Etat.IN_PROGRESS){
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

// Tous ce qui est Utilisateurs
class Utilisateur{
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

class DesktopPlaner{
    private List<Utilisateur> utilisateurs;
    private Utilisateur utilisateurActuel;
    /*
    login --- done 
    signup --- done  
    creerCalendrier --- done 
    AfficherCalendrier --- done 
    creneau : proposer (taches)
    PlanifierTask (tache, crenau )
     */
    public DesktopPlaner(){
        utilisateurs = new ArrayList<>();
    }
    public List<Utilisateur> getUtilisateurs(){
        return this.utilisateurs;
    }
    public Utilisateur getUtilisateurActuel (){
        return this.utilisateurActuel;
    }
    public void signup(Utilisateur utilisateur){
        utilisateurs.add( utilisateur);
    }
    public void login (int pos){
        if (pos>=0 && pos <utilisateurs.size()){
        this.utilisateurActuel = utilisateurs.remove(pos);
        utilisateurs.add(0, utilisateurActuel);}
        else System.out.println("indice n'existe pas");
    }
    public void refreshUtilisateur(){
        utilisateurs.set(0, utilisateurActuel);
    }
    public void afficherUtilisateurs(){
        // refreshUtilisateur();
        System.out.println("La liste des utilisateurs inscrits sont : ");
        for(int i = 0;i<utilisateurs.size() ; i++){
            System.out.println(i + ") "+utilisateurs.get(i).getPsuedo());
        }
    }
    public void afficherCurrentUser(){
        System.out.println("L'utilisateur actuelle est : " + this.utilisateurActuel.getPsuedo());
    }
    public void creerCalendrier(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Quel est le nombre de jours dans votre calendrier : ");
        int nbrJours = scanner.nextInt();
        System.out.print("Donne la periodicité de votre creneux (0 si pas de periodité)");
        int periodicité = scanner.nextInt();
        scanner.nextLine();
        Calendrier calendrier = new Calendrier(nbrJours, periodicité);
        calendrier.RemplirCalendrier();
        utilisateurActuel.ajouterCalendrier(calendrier);
        refreshUtilisateur();
    }
    public void AfficherCalendrier(){
        if(this.utilisateurActuel == null) System.out.println("Pas d'utilisateur connecté");
                else{
                if(this.utilisateurActuel.calendrier == null) System.out.println("pas de calendrier pour cet utilisaterur");
                else{
                    this.utilisateurActuel.afficherCalendrierUtilisateur();
                }
            }
        
    }
}

//*********************************************************** */
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i;
        DesktopPlaner application = new DesktopPlaner();
        do {
            System.out.println("1) Pour creer nouvau utilisateur ");
            System.out.println("2) pour connecter à un compte existant");
            System.out.println("3) pour afficher tous les utilisateurs");
            System.out.println("4) pour afficher l'utilisateur courant");
            System.out.println("5) pour creer un calendrier pour l'utilisateur courant");
            System.out.println("6)affichage du calendrier du current user");
            System.out.println("0) pour arreter");
            i = scanner.nextInt();
            scanner.nextLine();
            switch (i){
                case 1: 
                System.out.println("Entrer votre nom");
                application.signup(new Utilisateur(scanner.nextLine() ));
                break;
                case 2:
                if(application.getUtilisateurs().size() != 0) {
                application.afficherUtilisateurs();
                System.out.print("Quel est ton psuedo : ");
                int pos = scanner.nextInt();
                scanner.nextLine();
                application.login(pos);}
                else System.out.println("Pas d'utilisateurs inscrits, veuillez s'inscrire");
                break;
                case 3:
                if(
                    application.getUtilisateurs().size() != 0) 
                    application.afficherUtilisateurs();
                    else System.out.println("Pas d'utilisateurs inscrits");
                break;
                case 4:
                if (application.getUtilisateurActuel() != null) application.afficherCurrentUser();
                else System.out.println("No user logined");
                break;
                case 5://creer un calendrier pour le current user
                if(application.getUtilisateurActuel() == null) System.out.println("veuillez connecter");
                else{
                    application.creerCalendrier();
                }
                break;
                case 6://affichage du calendrier
                application.AfficherCalendrier();
                break;
            }
            
        } while (i !=0);
    }   

}