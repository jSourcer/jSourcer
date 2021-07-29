import de.jsourcer.parser.JavaFileParser;
import de.jsourcer.parser.misc.FunctionalCharArray;
import de.jsourcer.parser.objects.javafile.JavaFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestParser {

    public static Path resource = Paths.get("src", "test", "resources");
    public static Path badCode = resource.resolve("badCode.txt");
    public static Path headerTest = resource.resolve("headerTest.txt");

    @org.junit.jupiter.api.Test
    public void test() throws IOException {
        long start = System.currentTimeMillis();
        JavaFile file = new JavaFileParser(
            FunctionalCharArray.ofString(Files.readString(headerTest)))
            .parse();
        long stop = System.currentTimeMillis();
        System.out.println("time: " + (stop-start));
        file.getClazzes().forEach(System.out::println);
    }
}
