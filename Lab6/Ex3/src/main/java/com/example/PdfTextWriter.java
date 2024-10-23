package com.example;

import java.io.FileWriter;
import java.io.IOException;

public class PdfTextWriter implements TextWriter {
    @Override
    public void write(String fileName, String text) {
        try (FileWriter writer = new FileWriter(fileName + ".pdf")) {
            writer.write(text);
            System.out.println("Text written to " + fileName + ".pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
