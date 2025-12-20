package com.example.fileconverter;
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
    public void convertFile(@RequestParam("file") MultipartFile file, @RequestParam("outputFormat") String outputFormat, HttpServletResponse response) throws IOException {
        String originalName = file.getOriginalFilename();
        String nameWithoutExt = originalName.substring(0, originalName.lastIndexOf('.'));
        String newFilename = nameWithoutExt + "_converted." + outputFormat;


        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + newFilename + "\"");
        converterService.convertFile(file.getInputStream(), response.getOutputStream(), originalName, outputFormat);

        response.flushBuffer();
    }
}