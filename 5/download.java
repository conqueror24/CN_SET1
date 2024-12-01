import java.io.*;
import java.net.*;

public class download {
    public static void main(String[] args) {
        // Example URL and target file path
        String fileURL = "https://www.pinterest.com/azarystell/monkey-d-luffy-pics/"; // Replace with your file URL
        String savePath = "downloaded_sample.webp"; // Replace with your target file name

        try {
            downloadFile(fileURL, savePath);
            System.out.println("File downloaded successfully to: " + savePath);
        } catch (IOException e) {
            System.err.println("Error downloading file: " + e.getMessage());
        }
    }

    public static void downloadFile(String fileURL, String savePath) throws IOException {
        // Create a URI and convert it to URL
        URI uri = URI.create(fileURL);
        URL url = uri.toURL(); // Convert URI to URL

        // Open a connection to the file URL
        URLConnection connection = url.openConnection();
        long fileSize = connection.getContentLengthLong(); // Get file size

        // Display file size if available
        if (fileSize != -1) {
            System.out.println("File size: " + (fileSize / 1024) + " KB");
        } else {
            System.out.println("File size not available.");
        }

        // Input stream to read the file
        try (InputStream inputStream = connection.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(savePath)) {

            // Buffer for reading data
            byte[] buffer = new byte[4096];
            int bytesRead;

            System.out.println("Downloading...");
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("Download complete.");
        }
    }
}
