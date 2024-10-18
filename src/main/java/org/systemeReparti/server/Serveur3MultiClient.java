package org.systemeReparti.server;

import org.systemeReparti.Model.Personne;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur3MultiClient {
    public static void main(String[] args) {
        final int PORT = 12345; // Port du serveur

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Serveur démarré, en attente de connexions...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connecté");

                // Créez un nouveau thread pour chaque client
                new Thread(() -> handleClient(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket socket) {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            // Réception de l'objet Personne
            Personne personne = (Personne) ois.readObject();

            // Calcul de l'âge
            int age = personne.calculAge();

            // Envoi de l'âge au client
            oos.writeObject(age);
            oos.flush();

            // Fermeture des ressources
            ois.close();
            oos.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}