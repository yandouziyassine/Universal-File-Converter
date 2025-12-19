package com.example.fileconverter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TxtToUpperCaseConverter implements FileConverter {
    @Override
    public void convert(File source, String destinationPath) throws Exception {
        String content = new String(Files.readAllBytes(source.toPath()));
        Files.write(Paths.get(destinationPath), content.toUpperCase().getBytes());
    }
}