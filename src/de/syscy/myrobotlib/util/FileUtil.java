package de.syscy.myrobotlib.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {
	public static String readFile(String fileName) {
		File file = new File(fileName);
		
		if(file.exists() && file.canRead()) {
			try {
				byte[] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
				
				return new String(encoded, Charset.defaultCharset());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return "";
	}
}