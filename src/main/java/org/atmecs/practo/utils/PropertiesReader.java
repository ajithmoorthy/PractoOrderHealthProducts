package org.atmecs.practo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
	public  Properties property(String path) throws IOException {
		File file = new File(path);
		Properties prop = new Properties();
		FileInputStream input = new FileInputStream(file);
		prop.load(input);
		return prop;
	}


}
