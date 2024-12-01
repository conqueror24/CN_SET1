import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Dserver {
    public static void main(String[] args) {
        int port = 65432; // Port number for the server

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("DateTime server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                try (PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {
                    // Get current date and time
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String dateTime = now.format(formatter);

                    // Send date and time to client
                    output.println("Current Date and Time: " + dateTime);
                    System.out.println("Sent to client: " + dateTime);
                } catch (IOException e) {
                    System.err.println("Error sending data to client: " + e.getMessage());
                }

                socket.close();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
