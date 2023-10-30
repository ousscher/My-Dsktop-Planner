package Model;
import java.io.Serializable;
import java.time.*;
import java.util.*;;
public class Jour implements Serializable{
	 private TreeSet<Crineau> crineaux;
	    private int minTaches; // le nombre minimale de taches pour qu'il soit recompensé
	    private LocalDate date;
	    private int TachesAccomplis;
	    public static int nbrMinimaleParJour;

	    /*
	     * initialiserCreneauxJour ---done
	     * evaluerJournee
	     */
	    // constructeur - setteurs - guetteurs
	    public Jour(TreeSet<Crineau> crineaux, int minTaches, LocalDate date, int TachesAccomplis) {
	        this.crineaux = crineaux;
	        this.minTaches = minTaches;
	        this.date = date;
	        this.TachesAccomplis = TachesAccomplis;
	        crineaux = new TreeSet<>();
	    }

	    public Jour(LocalDate date) {
	        this.date = date;
	        crineaux = new TreeSet<>();
	    }
	    public Jour()
	    {}
	    public TreeSet<Crineau> getCrineaux() {
	        return this.crineaux;

	    }

	    public void setCrineaux(TreeSet<Crineau> crineaux) {
	        this.crineaux = crineaux;
	    }

	    public int getMinTaches() {
	        return this.minTaches;
	    }

	    public void setMintachs(int minTaches) {
	        this.minTaches = minTaches;
	    }

	    public LocalDate getDate() {
	        return this.date;
	    }

	    public void setDate(LocalDate date) {
	        this.date = date;
	    }

	    public int getTachesAccomplis() {
	        return this.TachesAccomplis;
	    }

	    public void setTachesAccomplis(int tachesAccomplis) {
	        this.TachesAccomplis = tachesAccomplis;
	    }

	    // constructeur - setteurs - guetteurs
	    public void incrementerTachesAccomplis() {
	        this.TachesAccomplis++;
	    }

	    public void ajouterCrineau(Crineau c) {
	        crineaux.add(c);
	    }

	    public void initaliserCrineauxJour(int nbrCrineaux) {
	        Scanner scanner = new Scanner(System.in);
	        int i = 0;
	        while (i < nbrCrineaux) {
	            System.out.print("precisez l'heure de debut du crineau numero " + (i + 1) + " : ");
	            LocalTime heureDebut = LocalTime.of(scanner.nextInt(), 0);
	            System.out.print("precisez l'heure de fin du crineau numero" + (i + 1) + " : ");
	            LocalTime heureFin = LocalTime.of(scanner.nextInt(), 0);
	            // test de l'heure
	            if (!isCreneauValide(heureDebut, heureFin)) {
	                System.out.println("Les heures du créneau sont invalides ou se chevauchent. Veuillez réessayer.");
	            } else {
	                ajouterCrineau(new Crineau(heureDebut, heureFin, null));
	                i++;
	            }
	        }
	    }

	    private LocalTime parseLocalTime(String heure) {
	        try {
	            String[] parts = heure.split(":");
	            int heureInt = Integer.parseInt(parts[0]);
	            int minuteInt = Integer.parseInt(parts[1]);

	            if (heureInt >= 0 && heureInt <= 23 && minuteInt >= 0 && minuteInt <= 59) {
	                return LocalTime.of(heureInt, minuteInt);
	            } else {
	                System.out.println("Heure invalide. Veuillez entrer une heure valide entre 00:00 et 23:59.");
	                return null;
	            }
	        } catch (Exception e) {
	            System.out.println("Format d'heure invalide. Veuillez entrer une heure au format HH:mm.");
	            return null;
	        }
	    }

	    public boolean isCreneauValide(LocalTime heureDebut, LocalTime heureFin) {
	    	if (heureFin.isBefore(heureDebut) || heureFin.equals(heureDebut)) {return false;}
	        for (Crineau creneau : crineaux) {
	            if (heureDebut.equals(creneau.getHeureDebut()) || heureFin.equals(creneau.getHeureFin())) {
	                return false; // Créneau avec heure de début ou heure de fin identique
	            }
	            if (heureDebut.isAfter(creneau.getHeureDebut()) && heureDebut.isBefore(creneau.getHeureFin())) {
	                return false; // Créneau avec intersection (heure de début entre créneau existant)
	            }
	            if (heureFin.isAfter(creneau.getHeureDebut()) && heureFin.isBefore(creneau.getHeureFin())) {
	                return false; // Créneau avec intersection (heure de fin entre créneau existant)
	            }
	            
	        }
	        return true;
	    }

}