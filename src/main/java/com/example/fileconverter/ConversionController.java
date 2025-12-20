package com.example.fileconverter;
import com.example.fileconverter.FileConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/files")
public class ConversionController {
    @Autowired
    private FileConverterService converterService;
    @PostMapping("/convert")
    public void convertFile(@RequestParam("file") MultipartFile file,
                            @RequestParam("outputFormat") String outputFormat,
                            HttpServletResponse response) throws IOException {

        if ("pdf".equalsIgnoreCase(outputFormat) && file.getOriginalFilename().endsWith(".docx")) {


            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=converted.pdf");
            converterService.convertDocxToPdf(file.getInputStream(), response.getOutputStream());

            response.flushBuffer();
        }

        else {
            throw new IllegalArgumentException("Only DOCX to PDF is supported in this example.");
        }
    }
}