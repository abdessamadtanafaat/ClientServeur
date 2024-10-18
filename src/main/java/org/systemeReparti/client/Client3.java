package org.systemeReparti.client;

// src/Client.java
import org.systemeReparti.Model.Personne;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class Client3 {
    public static void main(String[] args) {
        final String HOST = "localhost"; // ou l'adresse IP du serveur
        final int PORT = 12345; // Port du serveur

        try {
            // Connexion au serveur
            Socket socket = new Socket(HOST, PORT);

            // Création d'un flux d'objets pour envoyer l'objet Personne
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            // Saisie des informations de la personne
            System.out.print("Nom: ");
            String nom = scanner.nextLine();
            System.out.print("Prénom: ");
            String prenom = scanner.nextLine();
            System.out.print("Date de naissance (format: yyyy-MM-dd): ");
            Date dateNaissance = java.sql.Date.valueOf(scanner.nextLine());

            // Création de l'objet Personne
            Personne personne = new Personne(nom, prenom, dateNaissance);

            // Envoi de l'objet Personne au serveur
            oos.writeObject(personne);
            oos.flush();

            // Réception de l'âge calculé du serveur
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            int age = (int) ois.readObject();
            System.out.println("L'âge de " + nom + " est: " + age + " ans");

            // Fermeture des ressources
            ois.close();
            oos.close();
            socket.close();
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

