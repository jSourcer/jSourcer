import de.jsourcer.parser.JavaFileParser;
import de.jsourcer.parser.misc.IndexedCharArray;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HeaderTest {
    public static Path resource = Paths.get("src", "test", "resources");

    @Test
    public void headerTest() throws IOException {
        new JavaFileParser(IndexedCharArray.ofString(Files.readString(resource.resolve("headerTest.txt")))).parse();
    }
}
