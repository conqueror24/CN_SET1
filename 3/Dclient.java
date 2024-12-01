import java.io.*;
import java.net.*;

public class Dclient {
    public static void main(String[] args) {
        String hostname = "127.0.0.1"; // Server IP address
        int port = 65432; // Server port number

        try (Socket socket = new Socket(hostname, port);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connected to the server.");

            // Read date and time from server
            String serverResponse = input.readLine();
            System.out.println("Server response: " + serverResponse);

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
