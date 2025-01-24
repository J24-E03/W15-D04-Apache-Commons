import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

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
    readWebPage("https://google.com");

    // Working with files
    readFile("LoremIpsum.txt");
    copyFile("LoremIpsum.txt");

    // Find files with extension .java in other project
    findJavaFiles("./");
  }


  private static boolean checksum() throws IOException {
    // TODO: Add your code here
    File file = new File("labs/Java-Libraries-ApacheCommons/src/main/resources/LoremIpsum.txt");
    long checksum = FileUtils.checksumCRC32(file);

    return checksum == 2449403980L;
  }

  private static void readWebPage(String url) throws IOException {
    System.out.println("\n\nReading " + url);
    InputStream in = new URL(url).openStream();

    // TODO: Add your code here
    String content = IOUtils.toString(in, StandardCharsets.UTF_8);
    System.out.println(content);
  }

  private static void readFile(String fileName) throws IOException {
    System.out.println("\n\nReading LoremIpsum.txt:");
    // TODO: Add your code here
    File file = new File("labs/Java-Libraries-ApacheCommons/src/main/resources/LoremIpsum.txt");
    String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    System.out.println(content);
  }

  private static void copyFile(String fileName) throws IOException, InterruptedException {
    File sourceFile = new File("labs/Java-Libraries-ApacheCommons/src/main/resources/LoremIpsum.txt");
    File destDir= FileUtils.getTempDirectory();
    File destFile = new File(destDir, fileName);

    System.out.println("\n\nCreating a copy of LoremIpsum.txt in " + destDir.getAbsolutePath());
    FileUtils.copyFileToDirectory(sourceFile, destDir);

    Thread.sleep(150);

    System.out.println("Deleting the copied file");
    FileUtils.forceDelete(destFile);

    // TODO: Add your code here
  }

  private static void findJavaFiles(String relativePath) {
    System.out.println("\n\nListing all java files in directory " + relativePath);
    System.out.println(FileUtils.listFiles(new File(relativePath), new String[]{"java"}, true));
    }
  }

