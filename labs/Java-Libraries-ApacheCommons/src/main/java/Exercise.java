package main.java;

import org.apache.commons.io.FileUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Exercise {
    public static void main(String[] args) throws IOException, URISyntaxException {

        // Import library
        if (checksum()) {
            System.out.println("Successfully imported the Apache Commons IO library");
        } else {
            System.out.println("Wrong version of the library");
        }

        // Working with Streams
        readWebPage("https://digitalcareerinstitute.org/");

        // Working with files
        readFile("LoremIpsum.txt");
        copyFile("LoremIpsum.txt");

        // Find files with extension .java in other project
        findJavaFiles("./");
    }


    private static boolean checksum() throws IOException {
        // TODO: Add your code here
        long checksum = FileUtils.checksumCRC32(new File(FileUtils.class.getProtectionDomain().getCodeSource().getLocation().getFile()));
        return checksum == 2449403980L;
    }

    private static void readWebPage(String url) throws IOException, URISyntaxException {
        System.out.println("\n\nReading " + url);

        // TODO: Add your code here
        URL link = new URI(url).toURL();

        HttpsURLConnection connection = (HttpsURLConnection) link.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response);
    }

    private static void readFile(String fileName) throws IOException {
        System.out.println("\n\nReading LoremIpsum.txt:");

        // TODO: Add your code here
        System.out.println(FileUtils.readFileToString(new File("labs/Java-Libraries-ApacheCommons/src/main/resources/" + fileName), StandardCharsets.UTF_8));
    }

    private static void copyFile(String fileName) throws IOException {
        System.out.println("\n\nCreating a copy of LoremIpsum.txt in " + FileUtils.getTempDirectory());

        // TODO: Add your code here
        FileUtils.copyFile(new File("labs/Java-Libraries-ApacheCommons/src/main/resources/" + fileName), new File(FileUtils.getTempDirectory() + "/" + fileName));
    }

    private static void findJavaFiles(String relativePath) {
        System.out.println("\n\nListing all java files in directory " + relativePath);
        System.out.println(FileUtils.listFiles(new File(relativePath), new String[]{"java"}, true));
    }
}
