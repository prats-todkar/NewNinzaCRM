package practice;

import org.testng.Assert;
import org.testng.annotations.Test;


public class AssertionDemoWithStatements {
	@Test
	public void Test() {
		
		System.out.println("Hey");
		
		Assert.assertEquals("hdfc","hfdc");
		
		System.out.println("Bye");
			
	}

}
