package com.pca.test;

import java.io.FileInputStream;
import java.io.IOException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.experitest.client.Client;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Appflow  {

	private String host = "localhost";
	private int port = 8889;
	private String projectBaseDirectory = "C:\\Users\\Administrator\\workspace\\project3";
	public static final String data="E:\\JK_Softwares\\PCA_Projects\\AMR_Hybrid\\data.xls";
	protected Client client = null;
	FileInputStream fs=null;
	Workbook wb=null;
	
	
	public void getSheetData() throws BiffException, IOException
	{
		String FilePath = data;
		fs = new FileInputStream(FilePath);
		wb = Workbook.getWorkbook(fs);
		// TO get the access to the sheet
		/*int no = wb.getNumberOfSheets();
		String[] name= wb.getSheetNames();*/
	}
	@BeforeTest
	public void setUp() {
		client = new Client(host, port, true);
		client.setProjectBaseDirectory(projectBaseDirectory);
		client.setReporter("xml", "reports", "Untitled");
	}
	
	//@Test(groups = { "seetest" })
	@Test(priority=1)
	public void logIn() throws BiffException, IOException
	{
		client.setDevice("ios_app:PopcornApps iPad mini");//ios_app:Popcorn Apps IPad 2, ios_app:PopcornApps iPad mini
		client.launch("net.emsc.medslight", true, true);
		
		String FilePath = data;
		FileInputStream fs = new FileInputStream(FilePath);
		Workbook wb = Workbook.getWorkbook(fs);
		// TO get the access to the sheet
		int no = wb.getNumberOfSheets();
		String[] name= wb.getSheetNames();
		System.out.println(" no + name" + no + name);
		
		getSheetData();
		Sheet s = wb.getSheet("LoginOnly");
		
		for(int row = 1;row < s.getRows();row++)
		{
			 //Registered EmailId/Mobile no	
			client.click("WEB", "xpath=//*[@id='username']", 0, 1);
			String Username = s.getCell(0,row).getContents();
			System.out.println("user name :"+ Username);
			client.elementSendText("WEB", "xpath=//*[@id='username']", 0, Username);
			
			String Password = s.getCell(1,row).getContents();
			client.elementSendText("WEB", "xpath=//*[@id='password']", 0, Password);
		}
		
		
	/*	client.elementSendText("WEB", "xpath=//*[@id='username']", 0, "test");
		    //client.click("WEB", "xpath=//*[@id='password']", 0, 1);
		client.elementSendText("WEB", "xpath=//*[@id='password']", 0, "password");*/
		
		 // Unit No Selection
		/*  client.click("WEB", "xpath=//*[@id='login_page_unit_pop_DropDown']", 0, 1);
		 
		client.click("WEB", "xpath=//*[@text='E2']", 0, 1);*/
		
		if (client.isElementFound("WEB","xpath=//*[@text='										LOGIN									']", 0)) 
		{
		client.click("WEB","xpath=//*[@text='										LOGIN									']", 0,1);
		System.out.println("Login success");
		}
		else
			System.out.println("Login Failed");	
		
		client.sleep(4000);
//		if (client.waitForElement("WEB","xpath=//*[@text='									NEW incident								']", 0,10000))//New Incident
//		{
//			client.click("WEB","xpath=//*[@text='									NEW incident								']", 0,1);
//		}
	}
	
	@Test(priority=2)
	public void newIncident() throws InterruptedException, BiffException, IOException {
		client.setDevice("ios_app:PopcornApps iPad mini");
		client.sleep(3000);
		if (client.waitForElement("WEB","xpath=//*[@text='									NEW incident								']", 0,10000))//New Incident
		{
		client.click("WEB","xpath=//*[@text='									NEW incident								']",0,1);
		}
		client.click("WEB","xpath=//*[@id='txtCasenumber']",0,1);
		
		getSheetData();
		Sheet s = wb.getSheet("NewIncident");
		for(int row = 1;row < s.getRows();row++)
		{
			 //Registered EmailId/Mobile no	
			String Newincident = s.getCell(0,row).getContents();
			client.elementSendText("WEB", "xpath=//*[@id='txtCasenumber']", 0, Newincident);
			
			String Address = s.getCell(1,row).getContents();
			client.elementSendText("WEB", "xpath=//*[@id='txtAddress']", 0, Address);
			
			String Zip = s.getCell(2,row).getContents();
			client.elementSendText("WEB", "xpath=//*[@id='incident_zip']", 0, Zip);
			
		}
		client.sleep(1000);
		client.click("WEB","xpath=//*[@id='incident_city']",0,1);
		client.sleep(1000);
		client.click("WEB","xpath=//*[@text='Atherton']",0,1);
		client.sleep(1000);
		client.click("WEB","xpath=//*[@id='time_dispatched_ir']",0,1);
		client.sleep(1000);
		client.click("WEB","xpath=//*[@id='time_dispatched_ir']",0,1);
		client.click("WEB","xpath=//*[@id='time_start']",0,1);
		client.sleep(1000);
		client.click("WEB","xpath=//*[@id='time_start']",0,1);
		client.sleep(1000);
		client.click("WEB","xpath=//*[@id='time_on_scene']",0,1);
		client.sleep(1000);
		client.click("WEB","xpath=//*[@id='time_on_scene']",0,1);
		client.sleep(1000);
		client.click("WEB","xpath=//*[@id='time_at_patient_side_ir']",0,1);
		client.sleep(1000);
		client.click("WEB","xpath=//*[@id='time_at_patient_side_ir']",0,1);
		client.sleep(1000);
		client.click("WEB", "xpath=(//*[@id='done_button']/*[@id='incident_done_button'])[2]",0,1);
	}

	@Test(priority = 3)
	public void AddPCR() throws InterruptedException, BiffException, IOException {
		client.sleep(2000);
		client.click("WEB","xpath=//*[@nodeName='IMG' and @width>0 and ./parent::*[@text='									']]", 0, 1);
		client.click("WEB", "xpath=//*[@text='Lift Assist']", 0, 1);//xpath=//*[@text=' 911']
		client.click("WEB", "xpath=//*[@text='Yes']", 0, 1);
		client.click("WEB", "xpath=//*[@text='Yes' and @width>0]", 0, 1);
	}	
	
	
//	@SuppressWarnings("deprecation")
	@Test(priority =4)
	public void Dispatch()
	{
		client.sleep(3000);
		client.click("WEB", "xpath=(//*[@id='container']/*[@nodeName='DIV' and @width>0])[3]", 0, 1);
		client.sleep(1000);
		client.click("WEB", "xpath=(//*[@id='container']/*[@nodeName='DIV' and @width>0])[3]", 0, 1);
		client.sleep(1000);
		client.click("WEB", "xpath=//*[@text='																				' and ./*[@text='Crew 1 Signature']]",0,1);
		client.click("WEB", "xpath=//*[@id='newSignature']", 0, 1);
		client.dragCoordinates(800, 300, 900,920, 2000);
		client.sleep(1000);
		client.click("WEB", "xpath=//*[@id='crewSignature1_submit']", 0, 1);
		client.click("WEB", "xpath=(//*[@id='container']/*[@nodeName='DIV' and @width>0])[3]", 0, 1);
	}
	
	@Test(priority =5)
	public void DispatchIncident() throws BiffException, IOException
	{
		//Call Received
		client.click("WEB", "xpath=//*[@text='																				' and @nodeName='TD' and ./*[@text='Time Call Received']]", 0, 1);
		client.click("WEB", "xpath=//*[@id='in_inc_time_call_received']", 0, 1);
		client.sleep(1000);
		client.click("WEB", "xpath=//*[@id='in_inc_time_call_received']", 0, 1);
		
		/*//Dispatched.
		client.click("WEB", "xpath=//*[@text='																				' and @nodeName='TD' and @width>0 and ./*[@text='Time Dispatched']]", 0, 1);
		client.click("WEB", "xpath=//*[@id='in_inc_time_dispatched']", 0, 1);
		client.sleep(1000);
		client.click("WEB", "xpath=//*[@id='in_inc_time_dispatched']", 0, 1);
		
		//Entoutr
		client.click("WEB", "xpath=//*[@text='																				' and @nodeName='TD' and @width>0 and ./*[@text='Time Enroute']]", 0, 1);
		client.click("WEB", "xpath=//*[@id='in_inc_time_enroute']", 0, 1);
		client.sleep(1000);
		client.click("WEB", "xpath=//*[@id='in_inc_time_enroute']", 0, 1);
		//Pt Side
		client.click("WEB", "xpath=//*[@text='																				' and @nodeName='TD' and ./*[@text='Time at Pt Side']]",0,1);
		client.click("WEB", "xpath=//*[@id='in_inc_time_at_pt_side']", 0, 1);
		client.sleep(1000);
		client.click("WEB", "xpath=//*[@id='in_inc_time_at_pt_side']", 0, 1);
		*/
		//Available
		client.click("WEB", "xpath=//*[@text='																				' and @nodeName='TD' and @width>0 and ./*[@text='Time Available']]", 0, 1);
		client.click("WEB", "xpath=//*[@id='in_inc_time_available']", 0, 1);
		client.sleep(1000);
		client.click("WEB", "xpath=//*[@id='in_inc_time_available']", 0, 1);
		client.click("WEB", "xpath=(//*[@id='container']/*[@nodeName='DIV' and @width>0])[3]", 0, 1);
		client.sleep(1000);
		//client.click("WEB", "xpath=(//*[@id='container']/*[@nodeName='DIV' and @width>0])[3]", 0, 1);
	}
	
	
	@Test(priority =6)
	public void DispatchLocation() throws BiffException, IOException
	{
		getSheetData();
		Sheet s = wb.getSheet("DispatchLocation");
		
		for(int row = 1;row < s.getRows();row++)
		{
			 //Registered EmailId/Mobile no	
			/*String Type = s.getCell(0,row).getContents();
			System.out.println("user name :"+ Type);
			client.elementSendText("WEB", "xpath=//*[@id='location_typeDropDown']", 0, Type);*/
		
			client.click("WEB", "xpath=//*[@id='location_typeDropDown']", 0, 1);
			client.click("WEB", "xpath=//*[@text='Roadway']", 0, 1); //worng input for mail notification
			
			/*client.click("WEB", "xpath=//*[@text='																				' and ./*[@text='Pickup Address']]", 0, 1);
			String Address = s.getCell(1,row).getContents();
			System.out.println("user name :"+ Address);
			client.elementSendText("WEB", "xpath=//*[@id='in_picup_address']", 0, Address);
			client.sleep(3000);*/
		}
		client.click("WEB", "xpath=(//*[@id='container']/*[@nodeName='DIV' and @width>0])[3]", 0, 1);
	}
	
	
	@Test(priority =7)
	public void ReasonForCall() throws BiffException, IOException
	{
		//No of Patient
			client.click("WEB", "xpath=//*[@id='txt_no_of_patients']", 0, 1);
			client.click("WEB", "xpath=//*[@text='							1						']", 0, 1);
			client.sleep(1000);
			client.click("WEB", "xpath=//*[@id='txt_no_of_patients']", 0, 1);
			client.sleep(1000);
			//Initial Mode
			client.click("WEB", "xpath=//*[@text='																				' and ./*[@text='Initial Response Mode']]", 0, 1);
			client.click("WEB", "xpath=//*[@text='Lights and Siren' and @width>0]", 0, 1);
			client.sleep(3000);
			
			//Final Mode
			client.click("WEB", "xpath=//*[@text='																				' and ./*[@text='Final Response Mode']]", 0, 1);
			client.click("WEB", "xpath=//*[@text='No Lights and Siren' and @nodeName='DIV' and @width>0]", 0, 1);
			client.sleep(3000);
		    client.click("WEB", "xpath=(//*[@id='container']/*[@nodeName='DIV' and @width>0])[3]", 0, 1);
	}
	
	@Test(priority =8)
	public void Disposition() throws BiffException, IOException
	{
		//No of Patient
			client.click("WEB", "xpath=//*[@text='																				' and ./*[@text='Disposition']]", 0, 1);
			client.click("WEB", "xpath=//*[@text='Treated-Canc/Transport by Other AMR']", 0, 1);
			client.click("WEB", "xpath=(//*[@id='container']/*[@nodeName='DIV' and @width>0])[3]", 0, 1);
			client.sleep(1000);
			client.click("WEB", "xpath=(//*[@id='container']/*[@nodeName='DIV' and @width>0])[3]", 0, 1);
			client.sleep(1000);
	}
	
	@Test(priority =9)
	public void Patient() throws BiffException, IOException
	{
		//No of Patient
		    client.click("WEB", "xpath=//*[@id='txt_first_name']", 0, 1);
			client.elementSendText("WEB", "xpath=//*[@id='txt_first_name']", 0, "Eran");
			client.click("WEB", "xpath=//*[@text='																						' and @width>0 and ./*[@text='Last Name']]", 0, 1);
			client.click("WEB", "xpath=//*[@id='txt_last_name']",0,1);
			client.elementSendText("WEB", "xpath=//*[@id='txt_last_name']", 0, "Kinsbruner");
			client.closeKeyboard();
			client.sleep(1000);
			client.click("WEB", "xpath=(//*[@id='container']/*[@nodeName='DIV' and @width>0])[3]", 0, 1);
			client.sleep(1000);
			client.click("WEB", "xpath=(//*[@id='container']/*[@nodeName='DIV' and @width>0])[3]", 0, 1);
			client.sleep(1000);
			client.click("WEB", "xpath=(//*[@id='container']/*[@nodeName='DIV' and @width>0])[3]", 0, 1);
			client.sleep(1000);
	}
	
	@Test(priority =10)
	public void Assessment() throws BiffException, IOException
	{
		client.click("WEB", "xpath=//*[@text='Allergic Reaction' and @nodeName='DIV']", 0, 1);
		client.sleep(1000);
		client.click("WEB", "xpath=//*[@text='																				' and @nodeName='TD' and ./*[@text='Secondary Impression']]", 0, 1);
		client.click("WEB", "xpath=//*[@text='Altered Mental Status/Unresponsive' and @nodeName='DIV']", 0, 1);
		client.sleep(3000);
		client.click("NATIVE", "xpath=(//*[@class='TiUIView' and ./following-sibling::*[@class='TiUIWebView'] and ./parent::*[@class='TiUIView']]/*/*[@class='UIImageView' and ./parent::*[@class='TiUIImageView']])[1]", 0, 1);
	}
	
	@Test(priority =11)
	public void Park() throws BiffException, IOException
	{
		client.sleep(3000);
		client.click("WEB", "xpath=//*[@text='PARK']",0,1);
		client.sleep(1000);
		client.click("NATIVE","xpath=//*[@text='OK']",0,1);
	}
	
	@Test(priority =12)
	public void Logout() throws BiffException, IOException
	{
		client.sleep(3000);
		client.click("NATIVE", "xpath=(//*[@class='TiUIView' and ./preceding-sibling::*[@class='TiUIImageView'] and ./parent::*[@class='TiUIView']]/*/*[@class='UIImageView' and ./parent::*[@class='TiUIImageView']])[2]",0,1);
		client.sleep(3000);
		client.click("WEB", "xpath=//*[@text='Yes']",0 , 1);
		//xpath=//*[@text='Yes']
	}
	@Test(priority = 13)
	public void SuperlogIn() throws BiffException, IOException {
	    client.sleep(5000);
		client.click("WEB","xpath=(//*[@text='																													']/*[@nodeName='IMG' and @width>0])[2]",0, 1);
		client.click("WEB","xpath=//*[@text='									supervisor login								']",0, 1);
		client.elementSendText("WEB", "xpath=//*[@id='txt_super_uname']", 0, "popcorn");
		client.elementSendText("WEB", "xpath=//*[@id='txt_super_pwd']", 0, "letstest1");
		client.click("WEB", "xpath=//*[@text='								CONTINUE							']", 0, 1);
		client.sleep(5000);
		if(client.waitForElement("WEB","xpath=(//*[@class='TiUIView' and ./preceding-sibling::*[@class='TiUIImageView'] and ./parent::*[@class='TiUIView']]/*/*[@class='UIImageView' and ./parent::*[@class='TiUIImageView']])[2]", 0,10000))//New Incident
		{
		client.click("NATIVE","xpath=(//*[@class='TiUIView' and ./preceding-sibling::*[@class='TiUIImageView'] and ./parent::*[@class='TiUIView']]/*/*[@class='UIImageView' and ./parent::*[@class='TiUIImageView']])[2]",0, 1);
				client.sleep(3000);
		client.click("WEB", "xpath=//*[@text='Yes' and @width>0]", 0, 1);
		}
		else
			System.out.println("Not logged out!");
	}
	
	@AfterTest
	public void tearDown() {
		// Generates a report of the test case.
		// For more information -
		// https://docs.experitest.com/display/public/SA/Report+Of+Executed+Test
		client.generateReport(false);
		// Releases the client so that other clients can approach the agent in
		// the near future.
		client.releaseClient();
	}
}