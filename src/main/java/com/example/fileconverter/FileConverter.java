package com.example.fileconverter;
import java.io.File;

public interface FileConverter {

    void convert(File source, String destinationPath) throws Exception;
}