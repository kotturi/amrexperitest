package com.pca.test;

import java.io.IOException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.experitest.client.Client;
import jxl.read.biff.BiffException;

public class Supervisor  {
	private String host = "localhost";
	private int port = 8889;
	private String projectBaseDirectory = "C:\\Users\\Administrator\\workspace\\project3";
	protected Client client = null;

	@BeforeTest
	public void setUp() {
		client = new Client(host, port, true);
		client.setProjectBaseDirectory(projectBaseDirectory);
		client.setReporter("xml", "reports", "Untitled");
	}

	@Test(priority = 1)
	public void SuperlogIn() throws BiffException, IOException {
	    client.sleep(5000);
		client.click("WEB","xpath=(//*[@text='																													']/*[@nodeName='IMG' and @width>0])[2]",0, 1);
		client.click("WEB","xpath=//*[@text='									supervisor login								']",0, 1);
		client.elementSendText("WEB", "xpath=//*[@id='txt_super_uname']", 0, "popcorn");
		client.elementSendText("WEB", "xpath=//*[@id='txt_super_pwd']", 0, "letstest1");
		client.click("WEB", "xpath=//*[@text='								CONTINUE							']", 0, 1);
		client.sleep(5000);
		client.click("NATIVE","xpath=(//*[@class='TiUIView' and ./preceding-sibling::*[@class='TiUIImageView'] and ./parent::*[@class='TiUIView']]/*/*[@class='UIImageView' and ./parent::*[@class='TiUIImageView']])[2]",0, 1);
		client.sleep(3000);
		client.click("WEB", "xpath=//*[@text='Yes' and @width>0]", 0, 1);
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
