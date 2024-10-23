package org.systemeReparti.tp2.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Connect to the server at localhost:8089
            SocketChannel client = SocketChannel.open(new InetSocketAddress("localhost", 8089));
            System.out.println("Connection accepted by the Server..");

            // Get the message to send to the server
            System.out.println("Enter the message to send to the server:");
            String message = scanner.nextLine();

            // Prepare the buffer with the message
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(message.getBytes()); // Put the message into the buffer
            buffer.flip(); // Switch buffer from write mode to read mode

            // Send the message to the server
            int bytesWritten = client.write(buffer);
            System.out.println(String.format("Sending Message: %s\nBuffer Bytes: %d", message, bytesWritten));

            // Clear the buffer to prepare it for reading the server's response
            buffer.clear();
            client.read(buffer); // Read the server's response into the buffer

            // Convert the buffer's content to a string
            String response = new String(buffer.array()).trim();
            System.out.println("Received message from server: " + response);

            // Close the client connection
            client.close();
            System.out.println("Client connection closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
