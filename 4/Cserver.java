import java.io.*;
import java.net.*;

public class Cserver {
    public static void main(String[] args) {
        int port = 65432; // Server port number

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Chat server is listening on port " + port);

            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {

                Thread receiveThread = new Thread(() -> {
                    try {
                        String messageFromClient;
                        while ((messageFromClient = input.readLine()) != null) {
                            System.out.println("Client: " + messageFromClient);
                        }
                    } catch (IOException e) {
                        System.err.println("Error receiving message: " + e.getMessage());
                    }
                });
                receiveThread.start();

                String messageToClient;
                while (true) {
                    System.out.print("You: ");
                    messageToClient = consoleReader.readLine();
                    if ("exit".equalsIgnoreCase(messageToClient)) {
                        System.out.println("Closing chat...");
                        break;
                    }
                    output.println(messageToClient);
                }
            } catch (IOException e) {
                System.err.println("Error during chat: " + e.getMessage());
            }

            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
        }
    }
}
