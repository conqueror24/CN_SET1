import java.io.*;
import java.net.*;

public class Cclient {
    public static void main(String[] args) {
        String hostname = "127.0.0.1"; // Server IP address
        int port = 65432; // Server port number

        try (Socket socket = new Socket(hostname, port);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to the chat server.");

            Thread receiveThread = new Thread(() -> {
                try {
                    String messageFromServer;
                    while ((messageFromServer = input.readLine()) != null) {
                        System.out.println("Server: " + messageFromServer);
                    }
                } catch (IOException e) {
                    System.err.println("Error receiving message: " + e.getMessage());
                }
            });
            receiveThread.start();

            String messageToServer;
            while (true) {
                System.out.print("You: ");
                messageToServer = consoleReader.readLine();
                if ("exit".equalsIgnoreCase(messageToServer)) {
                    System.out.println("Closing chat...");
                    break;
                }
                output.println(messageToServer);
            }
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
