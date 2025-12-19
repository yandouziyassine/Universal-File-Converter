package com.example.fileconverter;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class HeicConverter implements FileConverter {
    @Override
    public void convert(File source, String destinationPath) throws Exception {
        ProcessBuilder builder = new ProcessBuilder(
                "magick",
                source.getAbsolutePath(),
                destinationPath
        );
        builder.redirectErrorStream(true);
        Process process = builder.start();
        boolean finished = process.waitFor(60, TimeUnit.SECONDS);
        if (!finished) {process.destroy();throw new Exception("Time limit exceeded for HEIC conversion.");
        }
        if (process.exitValue() != 0) {throw new Exception("ImageMagick failed with error code: " + process.exitValue());}
    }
}