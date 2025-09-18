package assertionDemo;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertEquals {
	
	@Test
	public void Test() {
		
//		System.out.println("Hey");
//		
//		Assert.assertEquals("hdfc","hfdc");
//		
//		System.out.println("Bye");
			
		System.out.println("welcome");
		
		SoftAssert soft = new SoftAssert(); 
		
		soft.assertEquals("hdfc","hfdc");
		
		System.out.println("byee");
		
		soft.assertTrue(true);
		
		System.out.println("see you");
		
		soft.assertAll();
	}

}
