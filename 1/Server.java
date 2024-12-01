import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 65432; // Port number for the server

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {

                    // Receive file name from client
                    String fileName = input.readLine();
                    System.out.println("Requested file: " + fileName);

                    File file = new File(fileName);
                    if (file.exists() && !file.isDirectory()) {
                        // Send file content to client
                        BufferedReader fileReader = new BufferedReader(new FileReader(file));
                        String line;
                        while ((line = fileReader.readLine()) != null) {
                            output.println(line);
                        }
                        fileReader.close();
                        System.out.println("File sent successfully.");
                    } else {
                        // File not found
                        output.println("ERROR: File not found.");
                        System.out.println("File not found.");
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
