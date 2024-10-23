package org.systemeReparti.tp2.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServerEx1 {
    private static Selector selector;

    // Handle client connection
    private static void handleAccept(ServerSocketChannel serverSocketChannel, SelectionKey key) throws IOException {
        SocketChannel client = serverSocketChannel.accept();
        client.configureBlocking(false); // Non-blocking mode
        System.out.println("Accepted connection from " + client.getRemoteAddress());
        client.register(selector, SelectionKey.OP_READ); // Register for reading
    }

    // Handle client message
    private static void handleRead(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int bytesRead = client.read(buffer);
        if (bytesRead == -1) {
            client.close();
            System.out.println("Connection closed by client.");
            return;
        }

        String message = new String(buffer.array()).trim();
        System.out.println("Received message from client: " + message);

        // Prepare the response to the client
        buffer.clear();
        buffer.put("Hello from server".getBytes());
        buffer.flip();
        client.write(buffer); // Send response to client
    }

    public static void main(String[] args) {
        try {
            // Open a selector
            selector = Selector.open();

            // Create a server socket channel and bind it to a port
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("localhost", 8089));
            serverSocketChannel.configureBlocking(false); // Non-blocking mode
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); // Register for accepting new clients

            System.out.println("Server started. Waiting for clients...");

            // Main server loop
            while (true) {
                selector.select(); // Block until an event occurs
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectedKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    if (key.isAcceptable()) {
                        handleAccept(serverSocketChannel, key); // Accept new clients
                    } else if (key.isReadable()) {
                        handleRead(key); // Read client messages
                    }

                    iterator.remove(); // Remove the key from the set
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


