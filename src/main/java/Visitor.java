import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class Visitor implements FileVisitor<Path> {
private final String fileName;
private final Path startDir;

public Visitor(String fileName, Path startDir){
    this.fileName = fileName;
    this.startDir = startDir;
}

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        String currentFileName = file.getFileName().toString();
        if(currentFileName.equals(fileName)){
            System.out.println("File found: " + file.toString());
            return FileVisitResult.TERMINATE;
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {

    if(Files.isSameFile(dir, startDir)){
        System.out.println("File: '" + fileName + "' not found");
        return FileVisitResult.TERMINATE;
    }
        return FileVisitResult.CONTINUE;
    }
}
