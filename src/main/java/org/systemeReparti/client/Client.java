package org.systemeReparti.client;

// Importation des classes nécessaires pour la gestion des entrées/sorties et des sockets
import java.io.*;
import java.net.Socket;

public class Client {
    // Le port et l'adresse du serveur auquel se connecter
    final static int PORT = 7878;
    final static String HOST = "localhost";

    public static void main(String[] args) throws Exception {
        try {
            // Connexion au serveur via son adresse (localhost) et son port
            Socket socket = new Socket(HOST, PORT);
            System.out.println("J'ai envoyé la requête de connexion via le port N°" + socket.getLocalPort());

            // Envoi d'un message au serveur
            OutputStream OS = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(OS);
            String requete = "Bonjour Mr le serveur";
            writer.println(requete);
            writer.flush();

            // Récupération de la réponse du serveur
            InputStream IS = socket.getInputStream();
            InputStreamReader ISR = new InputStreamReader(IS);
            BufferedReader reader = new BufferedReader(ISR);
            String reponse = reader.readLine();
            System.out.println("Le serveur m'a répondu par le message : " + reponse);

            // Fermeture des flux et de la connexion
            writer.close();
            reader.close();
            socket.close();
        } catch (Exception e) {
            // Gestion des erreurs
            e.printStackTrace();
        }
    }
}
