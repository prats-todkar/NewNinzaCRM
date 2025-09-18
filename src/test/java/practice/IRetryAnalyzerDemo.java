package practice;

import org.testng.annotations.Test;

import junit.framework.Assert;

public class IRetryAnalyzerDemo {
	
	@Test(retryAnalyzer = com.ninza.crm.listenerutility.IRetryAnalyzerImplementation.class)
	public void add() {
		
		System.out.println("Hello");
		Assert.assertEquals("hdfc","hfdc");
	}

}
