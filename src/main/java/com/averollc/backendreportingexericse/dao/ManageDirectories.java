package com.averollc.backendreportingexericse.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

/**
 * This class contains file handling methods.
 *
 * @author Sambhav D Sethia
 * @version 1.0
 * @since 9/12/2018
 */
public class ManageDirectories
{

    /**
     * Creates directories recursively
     *
     * @param baseDir
     * @param dirs
     */
    public static void createDirectories(final String baseDir, final String[] dirs)
    {
        for (final String dirName : dirs) {
            final Path dirPath = Paths.get(baseDir + dirName);

            if (!Files.exists(dirPath)) {
                try {
                    Files.createDirectories(dirPath);
                }
                catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Deletes files and directories recursively.
     *
     * @param baseDir
     * @throws IOException
     */
    // TODO: to be called after the Spring Boot application exits via shutdown hook!
    public static void deleteDirectories(final String baseDir) throws IOException
    {
        final Path dataPath = Paths.get(baseDir);
        Files.walk(dataPath).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
    }
}
