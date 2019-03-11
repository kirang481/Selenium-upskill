package com.training.dataproviders;

import java.util.List;

import org.testng.annotations.DataProvider;

import com.training.bean.LoginBean;
import com.training.bean.NewUserBean;
import com.training.dao.ELearningDAO;
import com.training.dao.NewUserDAO;
import com.training.readexcel.ApachePOIExcelRead_AddFeat;
import com.training.readexcel.ApachePOIExcelRead_AddUsers;
import com.training.readexcel.ApachePOIExcelRead_ErrorUsers;
import com.training.readexcel.ReadExcel;

public class LoginDataProviders {

	@DataProvider(name = "db-inputs")
	public Object [][] getDBData() {

		List<LoginBean> list = new ELearningDAO().getLogins(); 
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(LoginBean temp : list){
			Object[]  obj = new Object[2]; 
			obj[0] = temp.getUserName(); 
			obj[1] = temp.getPassword(); 
			
			result[count ++] = obj; 
		}
		
		
		return result;
	}
	
	@DataProvider(name = "db-inputs-user")
	public Object [][] getDBData1() {

		List<NewUserBean> list = new NewUserDAO().getUser(); 
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(NewUserBean temp : list){
			Object[]  obj = new Object[7]; 
			obj[0] = temp.getUserName(); 
			obj[1] = temp.getEmail();
			obj[2] = temp.getFirstName();
			obj[3] = temp.getLastName();
			obj[4] = temp.getwebSite();
			obj[5] = temp.getPassword();
			obj[6] = temp.getRole();
			
			result[count ++] = obj; 
		}
		
		
		return result;
	}
	
	@DataProvider(name = "excel-inputs")
	public Object[][] getExcelData(){
		String fileName ="C:\\Users\\KiranG\\Downloads\\RealEstate_ExcelInput.xlsx"; 
		return new ApachePOIExcelRead_AddUsers().getExcelContent(fileName); 
	}
	
	@DataProvider(name = "excel-inputserror")
	public Object[][] getExcelData1(){
		String fileName ="C:\\Users\\KiranG\\Downloads\\RealEstate_ExcelInput.xlsx";
		return new ApachePOIExcelRead_ErrorUsers().getExcelContent(fileName); 
	}
	
	@DataProvider(name = "excel-inputsAddfeat")
	public Object[][] getExcelData2(){
		String fileName ="C:\\Users\\KiranG\\Downloads\\RealEstate_ExcelInput.xlsx";
		return new ApachePOIExcelRead_AddFeat().getExcelContent(fileName); 
	}
	
	@DataProvider(name = "xls-inputs")
	public Object[][] getXLSData(){
		// ensure you will have the title as first line in the file 
		return new ReadExcel().getExcelData("C:\\Users\\KiranG\\Downloads\\RealEstate_ExcelInput.xlsx", "Sheet1"); 
	}
}
