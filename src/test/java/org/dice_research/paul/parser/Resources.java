package org.dice_research.paul.parser;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class Resources {

	public final static Path DIRECTORY_RESOURCES = Paths.get("src", "test", "resources");

	public final static String FILE_ENGLISH = "english.txt";
	public final static String FILE_GERMAN = "german.txt";

	public static File getResource(String resource) {
		String path = DIRECTORY_RESOURCES.toAbsolutePath().toString() + File.separator + resource;
		File file = new File(path);
		if (!file.canRead()) {
			throw new RuntimeException("Can not read resource: " + path);
		}
		return file;
	}

}
