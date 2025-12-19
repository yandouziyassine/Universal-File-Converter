package com.example.fileconverter;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class PdfToImageConverter implements FileConverter {
    @Override
    public void convert(File source, String destinationPath) throws Exception {
        try (PDDocument document = Loader.loadPDF(source)) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            BufferedImage bim = pdfRenderer.renderImageWithDPI(0, 300, ImageType.RGB);
            String formatName = destinationPath.substring(destinationPath.lastIndexOf(".") + 1);
            ImageIO.write(bim, formatName, new File(destinationPath));
        }
    }
}