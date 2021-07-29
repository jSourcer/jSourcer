import de.jsourcer.parser.JavaFileParser;
import de.jsourcer.parser.misc.FunctionalCharArray;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

public class HeaderTest {

    public static Path resource = Paths.get("src", "test", "resources");
    public static Path badCode = resource.resolve("badCode.txt");
    public static Path headerTest = resource.resolve("headerTest.txt");

    @Test
    public void headerTest() throws IOException {
        long start = System.currentTimeMillis();
        new JavaFileParser(
            FunctionalCharArray.ofString(Files.readString(badCode)))
            .parse();
        long stop = System.currentTimeMillis();
        System.out.println("time: " + (stop-start));
    }
}
