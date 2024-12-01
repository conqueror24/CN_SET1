import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String hostname = "127.0.0.1"; // Server's IP address
        int port = 65432; // Server's port number

        try (Socket socket = new Socket(hostname, port);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {

            // Read file name from user
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the file name to request: ");
            String fileName = consoleReader.readLine();

            // Send file name to server
            output.println(fileName);

            // Receive and print server response
            System.out.println("Server response:");
            String response;
            while ((response = input.readLine()) != null) {
                System.out.println(response);
            }

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
