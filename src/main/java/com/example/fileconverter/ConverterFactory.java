package com.example.fileconverter;

import java.util.Arrays;
import java.util.List;

public class ConverterFactory {

    private static final List<String> IMAGE_FORMATS = Arrays.asList("jpg", "jpeg", "png", "bmp", "gif", "tiff", "tif", "heic");

    public static FileConverter getConverter(String sourceType, String targetType) {
        String src = sourceType.toLowerCase();
        String tgt = targetType.toLowerCase();

        if(src.equals("heic") || tgt.equals("heic")) {
            return new HeicConverter();
        }
        if(IMAGE_FORMATS.contains(src) && IMAGE_FORMATS.contains(tgt)) {
            return new ImageConverter();
        }
        if(IMAGE_FORMATS.contains(src) && tgt.equals("pdf")) {
            return new ImageToPdfConverter();
        }
        if(src.equals("pdf") && IMAGE_FORMATS.contains(tgt)) {
            return new PdfToImageConverter();

        }
        if (src.equals("pdf") && tgt.equals("docx")) {
            return new PdfToDocxConverter();
        }
        if(src.equals("txt") && tgt.equals("txt")) {
            return new TxtToUpperCaseConverter();
        }


        throw new IllegalArgumentException("Conversion not supported: " + src + " to " + tgt);
    }
}