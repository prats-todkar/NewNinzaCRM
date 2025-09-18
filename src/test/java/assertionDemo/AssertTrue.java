package assertionDemo;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertTrue {

	@Test
	public void Test() {
		
		
		System.out.println("welcome");
		
		SoftAssert soft = new SoftAssert();
		soft.assertTrue("Java Selenium".contains("Selenium"));
		
//		Assert.assertTrue("Java Selenium".contains("Selenium"));
		
		System.out.println("see you");
		
		soft.assertAll();
	}
	
}
