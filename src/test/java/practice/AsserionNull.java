package practice;

import org.junit.Assert;
import org.testng.annotations.Test;

public class AsserionNull {
	
	@Test
	public void test() {
		
		System.out.println("Hey");
		String s = null;
		Assert.assertNull(s);          //output  true
		
		System.out.println("Bye");
		
		String a = "Prats";
		Assert.assertNotNull(a);
		
	}

}
