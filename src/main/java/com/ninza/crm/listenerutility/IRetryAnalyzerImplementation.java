package com.ninza.crm.listenerutility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class IRetryAnalyzerImplementation implements IRetryAnalyzer{

	
	int count = 0;
	int limitCount = 5;
	
	@Override
	public boolean retry(ITestResult result) {             //at sarting result is false
		
		if(count < limitCount) {
			
			count++;
			return true;
		}
		
		
		
		return false;
	}

}
