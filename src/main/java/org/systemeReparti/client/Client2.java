package org.systemeReparti.client;

import java.io.*;
import java.net.*;

public class Client2 {
    final static int PORT = 1234;  // Port du serveur
    final static String HOST = "localhost";  // Adresse du serveur

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(HOST, PORT)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            // Afficher les messages du serveur
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println(message);

                // Lire la réponse de l'utilisateur
                if (message.equals("Veuillez entrer une phrase : ")) {
                    String input = userInput.readLine();
                    out.println(input);  // Envoyer la réponse au serveur
                }

                if (message.contains("Au revoir")) {
                    break;  // Sortir si le serveur dit "Au revoir"
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

