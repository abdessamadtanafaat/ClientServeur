package org.systemeReparti.tp2.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class OtherClient {
    public static void main(String[] args) {
        Scanner clavier = new Scanner(System.in);
        try {
            // Connexion au serveur
            SocketChannel client = SocketChannel.open(new InetSocketAddress("localhost", 8089));
            System.out.println("Connection accepted by the Server..");

            // Saisir le message à envoyer au serveur
            System.out.println("Saisir le message à envoyer au serveur (OtherClient)");
            String msg = clavier.nextLine();

            // Préparation du buffer pour envoyer le message
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(msg.getBytes());
            buffer.flip(); // Préparer le buffer en mode lecture

            // Envoyer le message au serveur
            int bytesWritten = client.write(buffer);
            System.out.println(String.format("Sending Message: %s\nBytes Written: %d", msg, bytesWritten));

            // Effacer et préparer le buffer pour recevoir la réponse du serveur
            buffer.clear();
            client.read(buffer); // Lire la réponse du serveur dans le buffer

            // Convertir le buffer en string
            String data = new String(buffer.array()).trim();
            System.out.println("Received message from server: " + data);

            // Fermer la connexion
            client.close();
            System.out.println("OtherClient connection closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

