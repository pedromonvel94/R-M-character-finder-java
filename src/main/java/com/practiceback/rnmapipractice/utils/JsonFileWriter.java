package main.java.com.practiceback.rnmapipractice.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonFileWriter {

    private JsonFileWriter() { //Ojo aqui cree este constructor PRIVADO para evitar instanciacion, osea que no puedan hacer new JsonFileWriter()

    }

    public static void saveJson(String fileName, String json) throws IOException {
        File output = resolveOutputFile(fileName);
        try (FileWriter writer = new FileWriter(output)) {
            writer.write(json);
        }
        System.out.println("JSON guardado en: " + output.getAbsolutePath());
    }

    private static File resolveOutputFile(String fileName) {
        File resources = new File("src/main/resources");
        if (resources.exists() && resources.isDirectory()) {
            return new File(resources, fileName);
        }
        return new File(fileName);
    }
}

