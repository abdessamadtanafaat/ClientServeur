package org.systemeReparti.server;

// Importation des classes nécessaires pour la gestion des entrées/sorties et des sockets
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
    // Le port sur lequel le serveur écoutera les connexions entrantes
    final static int PORT = 7878;

    public static void main(String[] args) throws Exception {
        // Création du serveur qui attend des connexions sur le port défini
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("J'attends la connexion d'un client...");

        // Acceptation d'une connexion cliente
        Socket socket = serverSocket.accept();
        System.out.println("Un client est connecté, son IP est :" + socket.getInetAddress());

        try {
            // Récupération du flux d'entrée pour lire les données envoyées par le client
            InputStream IS = socket.getInputStream();
            InputStreamReader ISR = new InputStreamReader(IS);
            BufferedReader reader = new BufferedReader(ISR);

            // Lecture du message envoyé par le client
            String requete = reader.readLine();
            System.out.println("Le client a envoyé le message : " + requete);

            // Envoi d'une réponse au client via le flux de sortie
            OutputStream OS = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(OS);
            writer.println("Bonjour Mr le client");
            writer.flush();

            // Fermeture des flux et de la connexion
            reader.close();
            writer.close();
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            // Gestion des erreurs
            e.printStackTrace();
        }
    }
}

