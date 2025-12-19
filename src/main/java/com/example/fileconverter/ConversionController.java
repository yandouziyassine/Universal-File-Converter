package com.example.fileconverter;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/api")
public class ConversionController {

    // Notez le 's' à "files" et l'utilisation de List<>
    @PostMapping("/convert")
    public ResponseEntity<Resource> convertMultipleFiles(@RequestParam("files") List<MultipartFile> files,
                                                         @RequestParam("format") String targetFormat) {
        try {
            // 1. VÉRIFICATION DE SÉCURITÉ (Limite de 15)
            if (files.size() > 15) {
                return ResponseEntity.badRequest().body(null); // Trop de fichiers
            }

            // 2. PRÉPARATION DU FICHIER ZIP DE SORTIE
            // On crée un fichier zip vide temporaire
            String zipName = "converted-files-" + System.currentTimeMillis() + ".zip";
            File zipFile = new File(System.getProperty("java.io.tmpdir"), zipName);

            // On ouvre le flux pour écrire dans le zip
            try (FileOutputStream fos = new FileOutputStream(zipFile);
                 ZipOutputStream zos = new ZipOutputStream(fos)) {

                // 3. BOUCLE SUR CHAQUE FICHIER
                for (MultipartFile file : files) {


                    String originalName = file.getOriginalFilename();
                    if (originalName == null) continue;

                    String extension = originalName.substring(originalName.lastIndexOf(".") + 1);
                    //
                    String baseName = originalName.substring(0, originalName.lastIndexOf("."));
                    String outputName = baseName + "." + targetFormat;
                    File tempSource = File.createTempFile("upload-", "." + extension);
                    file.transferTo(tempSource);

                    File tempOutput = File.createTempFile("converted-", "." + targetFormat);

                    try {
                        FileConverter converter = ConverterFactory.getConverter(extension, targetFormat);
                        converter.convert(tempSource, tempOutput.getAbsolutePath());


                        ZipEntry zipEntry = new ZipEntry(outputName);
                        zos.putNextEntry(zipEntry);


                        byte[] bytes = Files.readAllBytes(tempOutput.toPath());
                        zos.write(bytes, 0, bytes.length);
                        zos.closeEntry();

                    } catch (Exception e) {
                        System.err.println("Erreur sur le fichier " + originalName + ": " + e.getMessage());

                    }
                }
            }

            InputStreamResource resource = new InputStreamResource(new FileInputStream(zipFile));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + zipName)
                    .contentLength(zipFile.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);

        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}