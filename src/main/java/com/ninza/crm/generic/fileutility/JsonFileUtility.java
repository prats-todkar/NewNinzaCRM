package com.ninza.crm.generic.fileutility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonFileUtility {
	
	public String toGetDataFromJsonFile(String key) throws IOException, ParseException {
		
		//Read the data from json File
		//Step 1.Create a java representation of physical file
		
		FileReader fr = new FileReader("./resources/commonData.json");
				
		//Step 2. create the object of JSONparser and use parse method to pass the object of physical file
	    JSONParser jp = new JSONParser();
		Object javaObject = jp.parse(fr);
			     
		//Step 3. Convert java object to Json by Downcasting
		JSONObject obj = (JSONObject)javaObject;
			    
		String value = obj.get(key).toString();
		return value;
	    
		}

}
