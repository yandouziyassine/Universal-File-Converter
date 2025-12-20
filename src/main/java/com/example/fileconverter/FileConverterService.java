package com.example.fileconverter;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class FileConverterService {

    public void convertDocxToPdf(InputStream inputStream, OutputStream outputStream) throws IOException {
        File tempInputFile = null;
        File tempOutputFile = null;

        try {
            // 1. Create temporary files
            tempInputFile = File.createTempFile("upload-", ".docx");
            tempOutputFile = File.createTempFile("converted-", ".pdf");


            try (FileOutputStream fileOut = new FileOutputStream(tempInputFile)) {inputStream.transferTo(fileOut);}

            DocxToPdfConverter converter = new DocxToPdfConverter();
            try {converter.convert(tempInputFile, tempOutputFile.getAbsolutePath());}
            catch (Exception e) {throw new IOException("Conversion failed inside DocxToPdfConverter", e);}

            try (FileInputStream fileIn = new FileInputStream(tempOutputFile)) {fileIn.transferTo(outputStream);}

        }
        finally {

            if (tempInputFile != null && tempInputFile.exists()) {
                tempInputFile.delete();
            }
            if (tempOutputFile != null && tempOutputFile.exists()) {
                tempOutputFile.delete();
            }
        }
    }
}