package de.murmelmeister.lobbysystem.utils;

import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;

public final class FileUtil {
    public static File createFile(Logger logger, String path, String fileName) {
        final File file = new File(path, fileName);
        final File parent = file.getParentFile();
        if (!parent.exists()) {
            boolean success = parent.mkdirs();
            if (!success) logger.warn("Could not create directory: {}", parent.getAbsolutePath());
        }

        if (!file.exists()) {
            try {
                boolean success = file.createNewFile();
                if (!success) logger.warn("Could not create file: {}", file.getAbsolutePath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return file;
    }
}
