package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FiletoJson {

    /**
     * Method that reads a Json file and transforms it into a String
     *
     * @param path to the Jsonfile from the root folder: src/test/resources/mymockrequest.json
     * @return String
     * @throws IOException
     */
    public static String convert(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
