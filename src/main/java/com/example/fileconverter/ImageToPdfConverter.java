package com.example.fileconverter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import java.io.File;

public class ImageToPdfConverter implements FileConverter {
    @Override
    public void convert(File source, String destinationPath) throws Exception {
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            doc.addPage(page);
            PDImageXObject pdImage = PDImageXObject.createFromFile(source.getAbsolutePath(), doc);
            try (PDPageContentStream contentStream = new PDPageContentStream(doc, page)) {
                float pageWidth = page.getMediaBox().getWidth();
                float pageHeight = page.getMediaBox().getHeight();
                float imgWidth = pdImage.getWidth();
                float imgHeight = pdImage.getHeight();


                float scale = Math.min(pageWidth / imgWidth, pageHeight / imgHeight);
                float newWidth = imgWidth * scale;
                float newHeight = imgHeight * scale;
                float startX = (pageWidth - newWidth) / 2;
                float startY = (pageHeight - newHeight) / 2;


                contentStream.drawImage(pdImage, startX, startY, newWidth, newHeight);
            }
            doc.save(destinationPath);
        }
    }
}