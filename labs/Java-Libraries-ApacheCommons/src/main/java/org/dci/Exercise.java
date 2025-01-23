package org.dci;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class Exercise {
  public static void main(String[] args) throws IOException, InterruptedException {

    // Import library
    if (checksum()) {
      System.out.println("Successfully imported the Apache Commons IO library");
    } else {
      System.out.println("Wrong version of the library");
    }

    // Working with Streams
    readWebPage("https://www.google.com/");

    // Working with files
    readFile("src/main/resources/LoremIpsum.txt");
    copyFile("src/main/resources/LoremIpsum.txt");

    // Find files with extension .java in other project
    findJavaFiles("./");
  }


  private static boolean checksum() throws IOException {
    String userHome = System.getProperty("user.home");
    File file = new File(userHome + "/.m2/repository/commons-io/commons-io/2.18.0/commons-io-2.18.0.jar");

    if (!file.exists()) {
      System.out.println("JAR file not found: ");
      return false;
    }

    String expectedChecksum = "f3ca0f8d63c40e23a56d54101c60d5edee136b42d84bfb85bc7963093109cf8b";

    try (InputStream fis = new FileInputStream(file)) {
      String calculatedChecksum = DigestUtils.sha256Hex(fis);
      return calculatedChecksum.equalsIgnoreCase(expectedChecksum);
    }
  }

  private static void readWebPage(String urlString) throws IOException {
    System.out.println("\n\nReading " + urlString);

    URL url;
    InputStream in;

    try {
      url = new URL(urlString);
      in = url.openStream();
      System.out.println(IOUtils.toString(in));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }


  }

  private static void readFile(String fileName) throws IOException {
    System.out.println("\n\nReading " + fileName);

    File file = new File(fileName);
    String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    System.out.println(content);
  }

  private static void copyFile(String fileName) throws IOException, InterruptedException {
    System.out.println("\n\nCreating a copy of LoremIpsum.txt in " + FileUtils.getTempDirectory());

    File sourceFile = new File(fileName);
    File destinationFile = new File(FileUtils.getTempDirectory(), "LoremIpsum_copy.txt");
    FileUtils.copyFile(sourceFile, destinationFile);

    Thread.sleep(150);

    FileUtils.forceDelete(destinationFile);
  }

  private static void findJavaFiles(String relativePath) {
    System.out.println("\n\nListing all java files in directory " + relativePath);

    File directory = new File(relativePath);
    System.out.println("Found java files:");
    System.out.println(FileUtils.listFiles(directory, new String[]{"java"}, true));
  }
}
