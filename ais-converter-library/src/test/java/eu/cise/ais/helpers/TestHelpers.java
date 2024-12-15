package eu.cise.ais.helpers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestHelpers {


    public static String readResource(String resourcePath) throws IOException, URISyntaxException {
        Path path = Paths.get(TestHelpers.class.getClassLoader().getResource(resourcePath).toURI());
        return Files.readString(path);
    }


}
