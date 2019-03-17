package com.panavis.WordToJsonConverter;

import java.io.FileWriter;
import java.io.IOException;

public class JsonFileWriter {

    public static void createFile(String path, String jsonString) throws IOException {

        try (FileWriter file = new FileWriter(path)) {
            file.write(jsonString);
            System.out.println("It cannot be this easy");
        }
    }
}
