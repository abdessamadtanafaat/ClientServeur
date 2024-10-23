package org.systemeReparti.tp2.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class OtherClientEx1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Connect to the server on localhost at port 8089
            SocketChannel client = SocketChannel.open(new InetSocketAddress("localhost", 8089));
            System.out.println("OtherClient connected to the server.");

            while (true) {
                // Get the message from the user
                System.out.print("OtherClient: Enter a message to send to the server: ");
                String message = scanner.nextLine();

                // Prepare the buffer with the message
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                buffer.put(message.getBytes());
                buffer.flip(); // Prepare buffer for reading

                // Send the message to the server
                client.write(buffer);

                // Exit if the user types "bye"
                if (message.equalsIgnoreCase("bye")) {
                    System.out.println("Closing OtherClient connection...");
                    client.close();
                    break;
                }

                // Clear buffer for receiving the response from the server
                buffer.clear();
                client.read(buffer); // Read response from server
                String response = new String(buffer.array()).trim();
                System.out.println("OtherClient received response from server: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}


