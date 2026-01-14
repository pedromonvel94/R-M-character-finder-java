package main.java.com.practiceback.rnmapipractice.utils;

import java.io.FileWriter;
import java.io.IOException;

public class JsonFileWriter {

    private JsonFileWriter() { //Ojo aqui cree este constructor PRIVADO para evitar instanciacion, osea que no puedan hacer new JsonFileWriter()

    }

    public static void saveJson(String fileName, String json) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(json);
        }
    }
}

