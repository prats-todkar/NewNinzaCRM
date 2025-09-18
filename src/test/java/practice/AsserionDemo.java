package practice;

import org.testng.annotations.Test;
public class AsserionDemo {
    
	@Test
	public void Test() {
		
		if("hdfc".equals("hfdc")) {
			System.out.println("same");
		}else {
			
			System.out.println("Not Same");
		}
	}

}


//here its not same but in result will get test run as passed. so if else not fails testcase.