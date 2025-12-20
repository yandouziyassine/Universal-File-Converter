package com.example.fileconverter;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;

public class PdfToDocxConverter implements FileConverter {

    @Override
    public void convert(File source, String destinationPath) throws Exception {
        System.out.println("--- DÉBUT CONVERSION RÉELLE (PDF -> DOCX) ---");

        // read pdf
        try (PDDocument pdfDocument = Loader.loadPDF(source)) {
            PDFTextStripper stripper = new PDFTextStripper();
            String textContent  = stripper.getText(pdfDocument);

            // word document creation
            try(XWPFDocument wordDocument = new XWPFDocument()) {
                XWPFParagraph paragraph = wordDocument.createParagraph();
                XWPFRun run = paragraph.createRun();

                // Insert text from pdf

                run.setText(textContent);

                // save DOCX file
                try (FileOutputStream out = new FileOutputStream(destinationPath)) {
                    wordDocument.write(out);
                }
            }
        }

        System.out.println("Fichier Word créé avec succès : " + destinationPath);
    }
}