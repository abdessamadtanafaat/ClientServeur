package org.systemeReparti.server;

import java.io.*;
import java.net.*;
import java.util.Date;

public class Serveur2 {
    final static int PORT = 1234;  // Spécifier le port d'écoute du serveur

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Le serveur est en écoute sur le port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();  // Accepter une connexion client
                System.out.println("Client connecté.");

                // Création des flux de communication
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Envoyer le message de bienvenue
                out.println("Bienvenue sur le serveur !");

                // Envoyer la date et l'heure
                Date now = new Date();
                out.println("La date et l'heure actuelles sont : " + now.toString());

                // Boucle pour interagir avec le client
                String message;
                do {
                    out.println("Veuillez entrer une phrase : ");
                    message = in.readLine();  // Lire la phrase du client
                    out.println("En majuscules : " + message.toUpperCase());  // Envoyer la phrase en majuscules
                } while (!message.equalsIgnoreCase("bye"));

                out.println("Au revoir !");
                clientSocket.close();  // Fermer la connexion avec le client
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
