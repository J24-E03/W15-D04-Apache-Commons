import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import static java.lang.System.in;

public class Exercise {
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
    // Note: Got 403 with DCI. Used Google instead.
    readWebPage("https://google.com/");

    // Working with files
    readFile(LOREM_IPSUM_FILE);
    copyFile(LOREM_IPSUM_FILE);

    // Find files with extension .java in other project
    findJavaFiles("./");
  }

  /**
   * @return true if the checksum is correct
   */
  private static boolean checksum() throws IOException {
    Long checksum = FileUtils.checksumCRC32(new File(LOREM_IPSUM_FILE));

    return checksum == 2449403980L;
  }

  private static void readWebPage(String url) throws IOException {
    System.out.println("\n\nReading " + url);
    try (InputStream in = new URL(url).openStream()) {
      System.out.println(IOUtils.toString(in, StandardCharsets.UTF_8));
    }
  }

  private static void readFile(String fileName) throws IOException {
    System.out.println("\n\nReading LoremIpsum.txt:");

    File file = new File(fileName);
    try (InputStream in = FileUtils.openInputStream(file)) {
      List<String> lines = IOUtils.readLines(in, StandardCharsets.UTF_8);
      lines.forEach(System.out::println);
    }
  }

  private static void copyFile(String fileName) throws IOException, InterruptedException {
    System.out.println("\n\nCreating a copy of LoremIpsum.txt in " + FileUtils.getTempDirectory());
    File file = new File(fileName);
    File tmpFile = new File(TMP_FILE);

    try (InputStream in = FileUtils.openInputStream(file)) {
      FileUtils.copyToFile(in, tmpFile);
      readFile(TMP_FILE);
      Thread.sleep(150);
      FileUtils.deleteQuietly(tmpFile);
    }
  }

  private static void findJavaFiles(String relativePath) {
    System.out.println("\n\nListing all java files in directory " + relativePath);
    Collection<File> files = FileUtils.listFiles(new File(relativePath), new String[]{"java"}, true);
    files.forEach(file -> System.out.println(FilenameUtils.getName(file.getName())));
  }
}
