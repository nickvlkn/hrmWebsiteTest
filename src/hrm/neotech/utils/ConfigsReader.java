package hrm.neotech.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigsReader {
	
	private static Properties prop;
	/**
	 * 
	 * @param filePath
	 */
	public static  void readProperties(String filePath) {
		try {
			FileInputStream filesIS= new FileInputStream(filePath);
		    prop=new Properties();
			prop.load(filesIS);
			filesIS.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File path is wrong.");
		} catch (IOException e) {
			System.out.println("I couldn't read the file.");
		}
	}
	
	/**
	 * 
	 * @param key
	 */
	public static  String getProperty(String key) {
		return prop.getProperty(key);
	}
	
	
}
