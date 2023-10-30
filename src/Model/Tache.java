package Model;
import java.io.Serializable;
import java.time.*;
import java.time.LocalDate;
import java.util.*;

import javafx.scene.paint.Color;;
public abstract class Tache implements Serializable, Comparable<Tache>{
	private String nom;
    private Duration duree;
    private Priorite priorite;
    private Categorie categorie;
    private String couleur;
    private LocalDate deadline;
    private int periodicite;
    private Etat etat;

    /* les constructeurs - settteurs - guetterus */
    Tache(String nom, Duration duree, Priorite priorite, Categorie categorie, String couleur2, LocalDate deadline,
            int periodicite) {
        this.nom = nom;
        this.duree = duree;
        this.priorite = priorite;
        this.categorie = categorie;
        this.couleur = couleur2;
        this.deadline = deadline;
        this.periodicite = periodicite;
        this.etat = etat.NOT_REALIZED;
    }

    public Etat getEtat() {
        return this.etat;
    }

    public String getNom() {
        return this.nom;
    }

    public Duration getDuree() {
        return this.duree;
    }

    public Categorie getCategorie() {
        return this.categorie;
    }

    public String getCouleur() {
        return this.couleur;
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    public Priorite getPriorite() {
        return this.priorite;
    }

    public int getPeriodicite() {
        return this.periodicite;
    }
    public void setEtat(Etat etat) {
    	this.etat = etat;
    }
    public int CompareTo(Tache other) {
    	return duree.compareTo(other.getDuree());
    }

    /* les constructeurs - settteurs - guetterus */}