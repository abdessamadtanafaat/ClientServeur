package org.systemeReparti.tp2.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class NIOServerEx2 {
    private static Selector selector;

    // Handle new client connections
    private static void handleAccept(ServerSocketChannel mySocket, SelectionKey key) throws IOException {
        System.out.println("New Connection Accepted..");
        SocketChannel client = mySocket.accept(); // Accept the connection
        client.configureBlocking(false); // Non-blocking mode
        client.register(selector, SelectionKey.OP_READ); // Register client for reading
    }

    // Handle reading client messages
    private static void handleRead(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int bytesRead = client.read(buffer);

        if (bytesRead == -1) {
            client.close();
            return;
        }

        String clientMessage = new String(buffer.array()).trim();
        System.out.println("Received message: " + clientMessage);

        // Check if the client wants to quit
        if (clientMessage.equalsIgnoreCase("bye")) {
            client.close();
            System.out.println("Connection closed by client.");
            return;
        }

        // Prepare a response
        buffer.clear();

        // Send welcome message and date/time
        String welcomeMsg = "Bienvenue! Date et heure: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\n";
        String upperCaseMsg = "Phrase en majuscule: " + clientMessage.toUpperCase();

        // Combine response
        String response = welcomeMsg + upperCaseMsg;
        buffer.put(response.getBytes());
        buffer.flip();

        // Write the response to the client
        client.write(buffer);
        buffer.clear();
    }

    public static void main(String[] args) {
        try {
            // Create a selector
            selector = Selector.open();

            // Open a server socket channel
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("localhost", 8089));
            serverSocketChannel.configureBlocking(false);

            // Register the server socket channel with the selector
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("Server started. Waiting for clients...");

            // Server loop
            while (true) {
                selector.select(); // Block until an event happens
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectedKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    if (key.isAcceptable()) {
                        handleAccept(serverSocketChannel, key); // Accept client connection
                    } else if (key.isReadable()) {
                        handleRead(key); // Read client data
                    }

                    iterator.remove(); // Remove the processed key
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

