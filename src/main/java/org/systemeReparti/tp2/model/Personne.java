
package org.systemeReparti.tp2.model;

import java.io.Serializable;
import java.util.Date;

public class Personne implements Serializable {
    private String nom;
    private String prenom;
    private Date dateNaissance;

    public Personne(String nom, String prenom, Date dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    public int calculAge(Date date) {
        // Implement logic to calculate age based on date of birth
        // This is just a placeholder; you'll need to implement the actual calculation
        return 0; // Replace with actual calculation
    }

    // Getters and setters
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

