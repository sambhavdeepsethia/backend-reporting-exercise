package dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateDirectories {

	public static void createDirectories(String baseDir, String[] dirs) {
		for (String dirName : dirs) {
			Path dirPath = Paths.get(baseDir + dirName);

			if (!Files.exists(dirPath)) {
				try {
					System.out.println("Creating dir: " + dirName);
					Files.createDirectories(dirPath);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
