package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\Opencart_LoginData.xlsx";//taking xl file from testData
		
		ExcelUtility xlUtil=new ExcelUtility(path);//creating an object for XLUtility
		
		int totalRows=xlUtil.getRowCount("Sheet1");
		int totalCols=xlUtil.getCellCount("Sheet1",1);
				
		String logindata[][]=new String[totalRows][totalCols];//created for two dimension array which can store the data user and password
		
		for(int i=1;i<=totalRows;i++)  //1   //read the data from xl storing in two deminsional array
		{		
			for(int j=0;j<totalCols;j++)  //0    i is rows j is col
			{
				logindata[i-1][j]= xlUtil.getCellData("Sheet1",i, j);  //1,0
			}
		}
	return logindata;//returning two dimension array
				
	}
	
	//DataProvider 2
	
	//DataProvider 3
	
	//DataProvider 4
}
