package com.ninza.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtility {
	
public String toGetDataFromPropertiesFile(String key) throws IOException {
		
		FileInputStream fs = new FileInputStream("./src/test/resources/commonData.properties");
		
		Properties p = new Properties();
		
		p.load(fs);
		
		String value = p.getProperty(key);
		
		return value;
	}


}
