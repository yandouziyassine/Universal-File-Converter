package com.example.fileconverter;
import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class DocxToPdfConverter implements FileConverter {

    @Override
    public void convert(File source, String destinationPath) throws Exception {

        WordprocessingMLPackage wordMLPackage = Docx4J.load(source);

        try (OutputStream out = new FileOutputStream(destinationPath)) {
            Docx4J.toPDF(wordMLPackage, out);
        }
    }
}