package com.ninza.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	public int getRandomNumber() {
		
		Random random = new Random();
		int randomCount = random.nextInt(100);
		return randomCount;
	}
	
    public String getCurrentDate() { 
    	Date date = new Date();
    	SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
    	
        String currentDate = sim.format(date);
        return currentDate;	
    	
    }   
    
    public String togetRequiredDate(int expectedDate) {
    	
    	//create object of date to get todays date
    	Date date = new Date();                                       //import from java.util package
        SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
    	sim.format(date);
    	
    	Calendar cal = sim.getCalendar();
    	cal.add(cal.DAY_OF_MONTH, expectedDate);
    	
    	String requiredDate = sim.format(cal.getTime());
    	return requiredDate;
    	
    }

}
