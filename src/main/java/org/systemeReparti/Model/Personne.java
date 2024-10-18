package org.systemeReparti.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.Calendar;

public class Personne implements Serializable {
    private String nom;
    private String prenom;
    private Date dateNaissance;

    public Personne(String nom, String prenom, Date dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    public int calculAge() {
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(this.dateNaissance);
        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return age;
    }

    // Getters pour nom et prenom (si nécessaire)
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }
}

