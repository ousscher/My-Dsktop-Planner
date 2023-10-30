package Model;
import application.Menuone_controller;
import java.io.Serializable;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

import javafx.scene.paint.Color;;
public class DesktopPlaner implements Serializable{
	  private HashSet<Utilisateur> utilisateurs;
	    private Utilisateur utilisateurActuel;

	    /*
	     * login --- done
	     * signup --- done
	     * creerCalendrier --- done
	     * AfficherCalendrier --- done
	     * creneau : proposer (taches)
	     * PlanifierTask (tache, crenau )
	     */
	    public DesktopPlaner() {
	        utilisateurs = new HashSet<Utilisateur>();
	    }

	    public HashSet<Utilisateur> getUtilisateurs() {
	        return this.utilisateurs;
	    }

	    public Utilisateur getUtilisateurActuel() {
	        return this.utilisateurActuel;
	    }
	    
	    public void setUtilisateur (Utilisateur user) {
	    	this.utilisateurActuel = user;
	    }

	    public void signup(String pseudo) {
	        if (utilisateurs.add(new Utilisateur(pseudo))) {
	            // this.utilisateurActuel = new Utilisateur(pseudo);
	            System.out.println("Compte bien crée, en tant que " + pseudo);
	        } else
	            System.out.println("Pseudo existe déja");
	    }

	    public void login(String pseudo) {
	        if (utilisateurActuel != null)
	            updateCurrentUser(); // save changes avant deconnecter
	        Iterator<Utilisateur> it = utilisateurs.iterator();
	        boolean conncted = false;
	        while (it.hasNext()) {
	            Utilisateur utilisateur = it.next();
	            if (utilisateur.getPsuedo().equals(pseudo)) {
	                this.utilisateurActuel = utilisateur;
	                conncted = true;
	                System.out.println("Connecté en tant que " + utilisateurActuel.getPsuedo());
	            }
	        }
	        if (!conncted)
	            System.out.println("pseudo n'existe pas, verifiez que vous etes inscrits");
	    }

	    public void updateCurrentUser() { // c'est une methode utilisé pour faire la mise à jour sur les changements de
	                                      // current user
	        Iterator<Utilisateur> it = utilisateurs.iterator();
	        while (it.hasNext()) {
	            Utilisateur utilisateur = it.next();
	            if (utilisateur.getPsuedo().equals(this.utilisateurActuel.getPsuedo())) {
	                it.remove();
	            }
	        }
	        utilisateurs.add(utilisateurActuel);
	    }

	    public void afficherUtilisateurs() {
	        // refreshUtilisateur();
	        System.out.println("La liste des utilisateurs inscrits sont : ");
	        Iterator<Utilisateur> iterable = utilisateurs.iterator();
	        while (iterable.hasNext()) {
	            System.out.println(iterable.next().getPsuedo());
	        }
	    }

	    public void afficherCurrentUser() {
	        System.out.println("L'utilisateur actuelle est : " + this.utilisateurActuel.getPsuedo());
	    }

	    public void creerCalendrier(LocalDate date1 , LocalDate date2 ) {
	        int nbrJours = Math.toIntExact(ChronoUnit.DAYS.between(date1, date2));
	        Calendrier calendrier = new Calendrier(nbrJours , date1);
            calendrier.RemplirCalendrier();
	        utilisateurActuel.ajouterCalendrier(calendrier);
	        updateCurrentUser();
	        // scanner.close();
	    }

	    public void AfficherCalendrier() {
	        if (this.utilisateurActuel == null)
	            System.out.println("Pas d'utilisateur connecté");
	        else {
	            if (this.utilisateurActuel.getCalendrier() == null)
	                System.out.println("pas de calendrier pour cet utilisaterur");
	            else {
	                this.utilisateurActuel.afficherCalendrierUtilisateur();
	            }
	        }

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

	    public Tache identifierTache(String nom, Duration duree, LocalDate date , Priorite priorite, Color couleur , Categorie c , int per) {
	        boolean estTacheSimple = false;
	        boolean comp = false;
	        System.out.println("la date du deadline est : "+date);
	        for (Jour jour : utilisateurActuel.getCalendrier().getJours()) {
	            for (Crineau creneau : jour.getCrineaux()) {
	                Duration dureeCreneau = creneau.calculerDureeCrineau();
	                if (duree.compareTo(dureeCreneau) <= 0) {
	                	estTacheSimple = true;
	                    break;
	                }
	            }
	            if (estTacheSimple) {
	                break;
	            }
	            if (jour.getDate().equals(date))
	                break;
	        }
	        Duration dure = Duration.ofMinutes(0);
	        if(!estTacheSimple) {
	        for (Jour jour : utilisateurActuel.getCalendrier().getJours()) {
	            for (Crineau creneau : jour.getCrineaux()) {
	                dure = dure.plus(creneau.calculerDureeCrineau());
	                if (dure.compareTo(duree)>=0) {
		            	comp = true;
		                break;
		            }
	            }
	            if (comp) break;
	            if (jour.getDate().equals(date))
	                break;
	        }
	        if(!comp) estTacheSimple = true;
	        }
	        if (estTacheSimple) {
	            return new TacheSimple(nom, duree, priorite, c, convertColorToString(couleur), date, per);
	        } else {
	            return new TacheComplex(nom, duree, priorite, c, convertColorToString(couleur), date, per);
	        }
	       
	    }

	    public void autoPlanifierTacheSimple(TacheSimple tache) { // utilisé pour les taches simples
	    	String message;
	    	boolean prop = false;
	        boolean ajoute = false;
	        boolean deadline = false;
	        for (Jour jour : utilisateurActuel.getCalendrier().getJours()) {
	            for (Crineau crineau : jour.getCrineaux()) {
	                if (crineau.calculerDureeCrineau().compareTo(tache.getDuree()) >= 0)
	                         {
	                    if (crineau.getEstDisponible()) {
	                        System.out.print("un crineau disponsible ");
	                        crineau.afficherCrineau();
	                        System.out.println("Si vous voulez planifier cette tache dans ce crineau appuiyez sur 1");
	                        int i =Menuone_controller.showDialogSimple("Crineau commence à " + crineau.getHeureDebut() + " et se termine à " + crineau.getHeureFin() +" le jour "+jour.getDate(), tache);
	                       prop = true;
	                        if (i == 1) {
	                            Crineau c = crineau.ajouterTache(tache);
	                            if (c != null)
	                                jour.getCrineaux().add(c);
	                            ajoute = true;
	                            break;
	                        }
	                    }
	                }
	            }
	            if (jour.getDate().equals(tache.getDeadline())) {
	                deadline = true;
	                
	                break;
	            }
	            if (ajoute)
	                break;
	        }
	       
	        if (!ajoute)
	        {
	        	if (deadline) message = "Pas de créneau libre avant le deadline de votre tache";
	        	else {
	        		if (prop) message = "Vous avez refusé tous les propositions possibles, pas d'autres propositions ";
	        		else message = "Pas de creneaux possibles dans votre planning, veuillez ajouter de nouveaux creneaux";
	        	}
	        	Menuone_controller.showDialogError(message , tache);
	        	
	        }
	        if (deadline)
	            System.out.println("pas de creneaux possibles avant le deadline de votre tache");
	        // scanner.close();
	    }

	    public HashMap<Crineau, LocalDate> proposerCreneaux(TacheComplex tache) {
	        HashMap<Crineau, LocalDate> creneaux = new HashMap<>();
	        Duration duration = Duration.ofMinutes(0);
	        boolean possible = false;
	        for (Jour jour : this.utilisateurActuel.getCalendrier().getJours()) {
	            for (Crineau crineau : jour.getCrineaux()) {
	                if (crineau.getEstDisponible())// creneau disponible
	                {
	                    duration = duration.plus(crineau.calculerDureeCrineau());
	                    creneaux.put(crineau, jour.getDate());
	                    if (duration.compareTo(tache.getDuree()) > 0)
	                        possible = true;
	                }
	            }
	            if(jour.getDate().equals(tache.getDeadline())) break; //arreter en deadline
	        }
	        // System.out.println("-----impression de la liste de creneux
	        // possibles-------");
	        // for (Map.Entry<Crineau, LocalDate> entry : creneaux.entrySet())
	        // System.out.println("----"+entry.getKey().getHeureDebut()+"
	        // "+entry.getKey().getHeureFin()+"----"+entry.getValue());
	        if (possible)
	            return creneaux;
	        else
	            return null;
	    }

	    public TreeMap<LocalDate, List<Crineau>> tacheDecomposablePropsition(HashMap<Crineau, LocalDate> creneaux,
	            TacheComplex tache) { // la methode qui retourne une seule proposition pour la planification
	        Duration duration = Duration.ofDays(0);
	        TreeMap<LocalDate, List<Crineau>> propositions = new TreeMap<LocalDate, List<Crineau>>();
	        Random random = new Random();
	        int i = 0;
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("Voici une proposition de creneaux pour votre Tache : ");
	        while (i != 1) {
	            HashMap<Crineau, LocalDate> creneaux2 = new HashMap<Crineau, LocalDate>(creneaux);
	            List<Crineau> keys = new ArrayList<Crineau>(creneaux2.keySet());
	            propositions.clear();
	            duration = Duration.ofMinutes(0);
	            while (duration.compareTo(tache.getDuree()) < 0) {
	                int randomIndex = random.nextInt(keys.size());
	                Crineau key = keys.get(randomIndex);
	                keys.remove(randomIndex);
	                LocalDate value = creneaux2.get(key);
	                creneaux2.remove(key, value);
	                if (propositions.containsKey(value))
	                    propositions.get(value).add(key);
	                else {
	                    List<Crineau> cs = new ArrayList<>();
	                    cs.add(key);
	                    propositions.put(value, cs);
	                }
	                duration = duration.plus(key.calculerDureeCrineau());
	            }
	            System.out.println("Votre tache sera planifié dans le crineau");
	            List<String> messages = new ArrayList<String>();
	            for (Map.Entry<LocalDate, List<Crineau>> entry : propositions.entrySet()) {
	                LocalDate date = entry.getKey();
	                List<Crineau> crineaux = entry.getValue();

	                // Triez les crineaux selon l'heure de début (si nécessaire)
	                Collections.sort(crineaux, Comparator.comparing(Crineau::getHeureDebut));

	                // Afficher les crineaux pour la date donnée
	                System.out.println("Date: " + date);
	                messages.add("Date: " + date);
	                for (Crineau crineau : crineaux) {
	                    System.out.println(crineau.getHeureDebut() + " ---- " + crineau.getHeureFin());
	                    messages.add(crineau.getHeureDebut() + " ---- " + crineau.getHeureFin());
	                }
	            }
	            i = Menuone_controller.showDialogComplex(messages , tache);
	        }
	        return propositions;
	    }

	    public void autoPlanifierTacheDecomposable(TacheComplex tache) {
	        HashMap<Crineau, LocalDate> creneaux = proposerCreneaux(tache);
	        if (creneaux != null) {
	            TreeMap<LocalDate, List<Crineau>> creneaux2 = tacheDecomposablePropsition(creneaux, tache);
	            tache.decomposerTache(creneaux2);
	            // commence de planification
	            int i = 0;
	            for (Map.Entry<LocalDate, List<Crineau>> entry : creneaux2.entrySet()) {
	                List<Crineau> crineaux = entry.getValue();
	                LocalDate date = entry.getKey();
	                for (Crineau crineau : crineaux) {
	                    Crineau c = crineau.ajouterTache(tache.geTacheSimples().get(i));
	                    i++;
	                    if (c != null) {
	                        for (Jour jour : utilisateurActuel.getCalendrier().getJours()) {
	                            if (jour.getDate().equals(date)) {
	                                jour.ajouterCrineau(c);
	                            }
	                        }
	                    }
	                }
	            }
	        } else
	            System.out.println("pas de creneaux suffisants avant le deadline pour cette tache pour cette tache");

	    }	    public void planifierEnsembleTache(){

	    }
	    
}
