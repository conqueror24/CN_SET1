import java.io.*;
import java.net.*;

public class client {
    public static void main(String[] args) {
        String hostname = "127.0.0.1"; // Server IP address
        int port = 65432; // Server port number

        try (Socket socket = new Socket(hostname, port);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to the echo server.");
            String message;

            while (true) {
                System.out.print("Enter a message (or 'exit' to quit): ");
                message = consoleReader.readLine();

                if ("exit".equalsIgnoreCase(message)) {
                    System.out.println("Closing connection...");
                    break;
                }

                output.println(message); // Send message to server
                String response = input.readLine(); // Read echoed message
                System.out.println("Echoed response: " + response);
            }
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
