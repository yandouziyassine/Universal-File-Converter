package com.example.fileconverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageConverter implements FileConverter {

    @Override
    public void convert(File source, String destinationPath) throws Exception {
        System.out.println("--- DÉBUT CONVERSION IMAGE ---");

        // 1. Lire l'image source en mémoire
        BufferedImage image = ImageIO.read(source);

        if (image == null) {
            throw new IllegalArgumentException("Impossible de lire l'image. Format non supporté ou fichier corrompu.");
        }

        // 2. Déterminer le format de sortie (ex: extraire "png" de "fichier.png")
        String formatName = destinationPath.substring(destinationPath.lastIndexOf(".") + 1);

        // 3. Écrire la nouvelle image
        // ImageIO s'occupe de tout l'encodage complexe !
        boolean result = ImageIO.write(image, formatName, new File(destinationPath));

        if (!result) {
            throw new Exception("Erreur: Le format " + formatName + " n'est pas supporté par Java ImageIO.");
        }

        System.out.println("Image convertie avec succès : " + destinationPath);
    }
}