import java.io.*;
import java.net.*;

public class server {
    public static void main(String[] args) {
        int port = 65432; // Port number for the server

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Echo server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {

                    String receivedMessage;
                    while ((receivedMessage = input.readLine()) != null) {
                        System.out.println("Received: " + receivedMessage);
                        output.println(receivedMessage); // Echo back the message
                    }
                } catch (IOException e) {
                    System.err.println("Error handling client: " + e.getMessage());
                }

                socket.close();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}