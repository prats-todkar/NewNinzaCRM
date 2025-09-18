package practice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ninza.crm.generic.fileutility.ExcelFileUtility;

public class DataproviderForLoop {

	@DataProvider
	public Object[][] loginDetails() throws EncryptedDocumentException, IOException{
		
		
		ExcelFileUtility ex = new ExcelFileUtility();
		
		int lastRowNum = ex.toGetTheRowCount("Campaigns");
		
		System.out.println(lastRowNum);
		
		Object[][] obj = new Object[lastRowNum][2];
		
		for(int i = 1; i < lastRowNum; i++) {
			
			
			obj[i][0]  = ex.toReadDataFromExcel("Campaigns",i,1);
			obj[i][1]  = ex.toReadDataFromExcel("Campaigns",i,2);
		
			
		}
				
		
		return obj;
	
	}
	
    @Test(dataProvider = "loginDetails")
	
	public void login(String campName, String targetSize){
		
		System.out.println(campName+ "=>" + targetSize);
	}
	
	
}
