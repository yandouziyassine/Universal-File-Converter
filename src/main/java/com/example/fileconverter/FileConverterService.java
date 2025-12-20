package com.example.fileconverter;
import org.springframework.stereotype.Service;
import java.io.*;

@Service
public class FileConverterService {
    public void convertFile(InputStream inputStream, OutputStream outputStream, String originalFilename, String targetFormat) throws IOException {
        File tempInputFile = null;
        File tempOutputFile = null;

        try {
            String inputExtension = "";
            int i = originalFilename.lastIndexOf('.');
            if (i > 0) {inputExtension = originalFilename.substring(i + 1).toLowerCase();}

            tempInputFile = File.createTempFile("upload-", "." + inputExtension);
            tempOutputFile = File.createTempFile("converted-", "." + targetFormat);


            try (FileOutputStream fileOut = new FileOutputStream(tempInputFile)) {inputStream.transferTo(fileOut);}
            FileConverter converter = ConverterFactory.getConverter(inputExtension, targetFormat);
            try {converter.convert(tempInputFile, tempOutputFile.getAbsolutePath());}
            catch (Exception e) {throw new IOException("Conversion failed: " + e.getMessage(), e);}
            try (FileInputStream fileIn = new FileInputStream(tempOutputFile)) {fileIn.transferTo(outputStream);}

        }
        finally {
            if (tempInputFile != null && tempInputFile.exists()) tempInputFile.delete();
            if (tempOutputFile != null && tempOutputFile.exists()) tempOutputFile.delete();
        }
    }
}