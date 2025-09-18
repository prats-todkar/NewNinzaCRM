package practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Dataprovider {
	
	@DataProvider
	public Object[][] loginDetails(){
		
		Object[][] obj = new Object[3][2];
		
		obj[0][0] = "Pratiksha";	
		obj[0][1] = "prati@123";
		obj[1][0] = "Sanika";
		obj[1][1] = "sanu@123";
		obj[2][0] = "Vaishnvi" ;
		obj[2][1] = "vaishu@123" ;
		
		return obj;
	}
	
	@Test(dataProvider = "loginDetails")
	
	public void login(String un, String pwd){
		
		System.out.println(un+ "==>" + pwd);
	}
		
		

}
