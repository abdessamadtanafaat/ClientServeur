package org.systemeReparti.server;

import java.io.*;
import java.net.*;

public class ServeurMultiClient {
    final static int PORT = 1234;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Le serveur multi-client est en écoute sur le port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connecté.");

                // Démarrer un nouveau thread pour chaque client
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            // Envoyer le message de bienvenue et la date
            out.println("Bienvenue sur le serveur !");
            out.println("La date et l'heure actuelles sont : " + new java.util.Date().toString());

            // Boucle d'échange avec le client
            String message;
            do {
                out.println("Veuillez entrer une phrase : ");
                message = in.readLine();
                out.println("En majuscules : " + message.toUpperCase());
            } while (!message.equalsIgnoreCase("bye"));

            out.println("Au revoir !");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

