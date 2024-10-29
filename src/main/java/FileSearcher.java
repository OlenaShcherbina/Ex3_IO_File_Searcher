import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSearcher {

  private static final String ROOT = "data";

  public static void main(String[] args) {

    Reader reader = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(reader);
    String fileName = "";

    System.out.println("Type the file name you want to search for: ");
    try {
      fileName = br.readLine();
    }catch(IOException e){
      e.printStackTrace();
    }
    Path rootPath = Paths.get(ROOT);

    Visitor fileVisitor = new Visitor(fileName, rootPath);

    try {
      Files.walkFileTree(rootPath, fileVisitor);
    }catch(IOException e){
      e.printStackTrace();
    }
    }
}
