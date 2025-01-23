package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.io.File;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class Main {

    private static final String LOREM_IPSUM_FILE = "labs/Java-Libraries-ApacheCommons/src/main/resources/LoremIpsum.txt";
    private static final String TMP_FILE = FileUtils.getTempDirectory() + "/LoremIpsum.txt";

    public static void main(String[] args) throws IOException, InterruptedException {

        // Import library
        if (checksum()) {
            System.out.println("Successfully imported the Apache Commons IO library");
        } else {
            System.out.println("Wrong version of the library");
        }

        // Working with Streams

        // There was an issue with the DCI website, so I used the Google instead
        readWebPage("https://google.com/");

        // Working with files
        readFile("LoremIpsum.txt");
        copyFile("LoremIpsum.txt");

        // Find files with extension .java in other project
        findJavaFiles("./");
    }

    private static boolean checksum() throws IOException {
        System.out.println("\n\nCalculating checksum for 'LoremIpsum.txt':");

        // Load the file as a resource
        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("LoremIpsum.txt");

        // Check if the file exists as a resource
        if (inputStream == null) {
            System.out.println("File not found: LoremIpsum.txt");
            return false;
        }

        try {
            // Use a temporary file to calculate checksum
            File tempFile = File.createTempFile("checksum_temp", ".txt");
            FileUtils.copyInputStreamToFile(inputStream, tempFile);

            // Calculate the CRC32 checksum for the file
            long checksum = FileUtils.checksumCRC32(tempFile);

            // Clean up the temporary file
            tempFile.delete();

            // Compare checksum value
            System.out.println("Calculated checksum: " + checksum);
            return checksum == 2449403980L;
        } finally {
            inputStream.close();
        }
    }

    private static void readWebPage(String url) throws IOException {
        System.out.println("\n\nReading " + url);
        InputStream in = new URL(url).openStream();
        try {
            String content = IOUtils.toString(in, StandardCharsets.UTF_8);
            System.out.println(content);
        } finally {
            in.close();
        }
    }

    private static void readFile(String fileName) throws IOException {
        System.out.println("\n\nReading " + fileName + ":");

        // Load the file as a resource
        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // Check if the file was found
        if (inputStream == null) {
            System.out.println("File not found: " + fileName);
            return;
        }

        // Use IOUtils to read the file content
        try {
            String content = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            System.out.println(content);
        } finally {
            inputStream.close();
        }
    }

    private static void copyFile(String fileName) throws IOException, InterruptedException {
        System.out.println("\n\nCreating a copy of " + fileName + " in " + FileUtils.getTempDirectory());

        // Load the file as a resource
        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // Check if the file exists
        if (inputStream == null) {
            System.out.println("File not found: " + fileName);
            return;
        }

        // Define the target file in the temporary directory
        File tempFile = new File(FileUtils.getTempDirectory(), fileName);

        // Copy the content of the input stream to the target file
        try {
            FileUtils.copyInputStreamToFile(inputStream, tempFile);
            System.out.println("Successfully copied to: " + tempFile.getAbsolutePath());
        } finally {
            // Clean up resources
            inputStream.close();
        }

        // Introduce a delay
        Thread.sleep(150);

        // Delete the file after the delay
        if (tempFile.delete()) {
            System.out.println("Successfully deleted the copied file: " + tempFile.getAbsolutePath());
        } else {
            System.out.println("Failed to delete the copied file: " + tempFile.getAbsolutePath());
        }
    }

    private static void findJavaFiles(String relativePath) {
        System.out.println("\n\nListing all java files in directory " + relativePath);
        // Implementation to find .java files can be added here.
    }
}