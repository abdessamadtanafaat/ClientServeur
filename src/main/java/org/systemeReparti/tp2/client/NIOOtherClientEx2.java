package org.systemeReparti.tp2.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NIOOtherClientEx2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Establish connection to the server at localhost on port 8089
            SocketChannel client = SocketChannel.open(new InetSocketAddress("localhost", 8089));
            System.out.println("Connected to the server from OtherClient.");

            while (true) {
                // Prompt the user to input a message
                System.out.println("Enter a message to send (OtherClient, type 'bye' to quit):");
                String message = scanner.nextLine();

                // Prepare the buffer with the message
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                buffer.put(message.getBytes());
                buffer.flip();

                // Send the message to the server
                client.write(buffer);

                // Exit loop if "bye" is entered
                if (message.equalsIgnoreCase("bye")) {
                    System.out.println("Closing OtherClient connection...");
                    client.close();
                    break;
                }

                // Clear the buffer for reading the response from the server
                buffer.clear();

                // Read the server's response
                client.read(buffer);
                String response = new String(buffer.array()).trim();
                System.out.println("Server Response (OtherClient): " + response);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}

