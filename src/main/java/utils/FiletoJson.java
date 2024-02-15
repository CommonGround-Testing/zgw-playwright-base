package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FiletoJson {
    public static String convert(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
