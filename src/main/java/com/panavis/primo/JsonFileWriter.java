package com.panavis.primo;

import java.io.FileWriter;
import java.io.IOException;

class JsonFileWriter {

    static void createFile(String path, String jsonString) throws IOException {

        try (FileWriter file = new FileWriter(path)) {
            file.write(jsonString);
            System.out.println("Created file: "+ path);
        }
    }
}
