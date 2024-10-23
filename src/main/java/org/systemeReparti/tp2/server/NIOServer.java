package org.systemeReparti.tp2.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    private static Selector selector = null;

    // Method to handle incoming client connections
    private static void handleAccept(ServerSocketChannel mySocket, SelectionKey key) throws IOException {
        System.out.println("Connection Accepted..");
        SocketChannel client = mySocket.accept(); // Accept the connection
        client.configureBlocking(false); // Set non-blocking mode
        // Register that client is reading this channel
        client.register(selector, SelectionKey.OP_READ);
    }

    // Method to handle reading messages from clients
    private static void handleRead(SelectionKey key) throws IOException {
        System.out.println("Reading client's message.");
        // Get the client channel
        SocketChannel client = (SocketChannel) key.channel();
        // Create a buffer to read data
        ByteBuffer buffer = ByteBuffer.allocate(1024); // Create an empty buffer
        client.read(buffer); // Read the client's message into the buffer
        // Convert the buffer's content to a string
        String data = new String(buffer.array()).trim();
        System.out.println("Received message: " + data);
        System.out.println("Writing a message to client");

        // Clear buffer and prepare it for writing
        buffer.clear();
        buffer.put(new String("Bonjour Mr client").getBytes()); // Server's response
        buffer.flip(); // Switch buffer from write mode to read mode
        client.write(buffer); // Send the response to the client
        client.close(); // Close the client connection
    }

    public static void main(String[] args) {
        try {
            selector = Selector.open(); // Open a selector for handling multiple channels
            // Create a server socket channel
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            ServerSocket serverSocket = serverSocketChannel.socket();
            // Bind server socket to localhost at port 8089
            serverSocket.bind(new InetSocketAddress("localhost", 8089));
            serverSocketChannel.configureBlocking(false); // Set non-blocking mode
            // Register the server socket channel with the selector
            serverSocketChannel.register(selector, serverSocketChannel.validOps(), null);

            // Server loop to handle events
            while (true) {
                selector.select(); // Wait for an event
                Set<SelectionKey> selectedKeys = selector.selectedKeys(); // Get set of ready keys
                Iterator<SelectionKey> i = selectedKeys.iterator();
                while (i.hasNext()) {
                    SelectionKey key = i.next();
                    if (key.isAcceptable()) { // If it's a new client connection
                        handleAccept(serverSocketChannel, key);
                    } else if (key.isReadable()) { // If it's a readable event (client sent data)
                        handleRead(key);
                    }
                    i.remove(); // Remove the key from the iterator after handling it
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
