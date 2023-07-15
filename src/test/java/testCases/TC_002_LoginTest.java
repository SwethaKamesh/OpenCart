package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTestClass;

public class TC_002_LoginTest extends BaseTestClass{
	
	@Test(groups= {"Sanity","Master"})
	public void testlogin() {
		
		try {
			log.info("****** Starting TC_002_LoginTest ******");
			HomePage hp=new HomePage(driver);
			hp.clickMyaccount();
			log.info("Clicked on MyAccount ");
			hp.clickLogin();
			log.info("Clicked on Login ");
			
			LoginPage lp=new LoginPage(driver);
			log.info(" Sending Valid email and Password ");
			lp.setEmail(rb.getString("email"));//getting email from config.properties file
			lp.setPassword(rb.getString("password"));//getting password from config.properties file
			lp.clickLogin();
			
			MyAccountPage map=new MyAccountPage(driver);
			log.info(" Validating MyAccountPage exists after Login ");
			boolean status=map.isMyAccountPageExists();
			
			Assert.assertEquals(status, true);
			log.info("****** Starting TC_002_LoginTest ******");
		}
		catch(Exception e) {
			
			Assert.fail();
			log.info("Test failed");
			
		}
	}
}
