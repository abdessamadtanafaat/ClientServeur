package org.systemeReparti.tp2.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NIOClientEx2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Connect to the server
            SocketChannel client = SocketChannel.open(new InetSocketAddress("localhost", 8089));
            System.out.println("Connected to the server.");

            while (true) {
                // Get user input
                System.out.println("Enter a message (type 'bye' to quit):");
                String message = scanner.nextLine();

                // Prepare the buffer with the message
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                buffer.put(message.getBytes());
                buffer.flip();

                // Send the message to the server
                client.write(buffer);

                // Exit if the user types "bye"
                if (message.equalsIgnoreCase("bye")) {
                    System.out.println("Closing connection...");
                    client.close();
                    break;
                }

                // Clear the buffer for reading the response
                buffer.clear();

                // Read the server's response
                client.read(buffer);
                String response = new String(buffer.array()).trim();
                System.out.println("Server Response: " + response);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
