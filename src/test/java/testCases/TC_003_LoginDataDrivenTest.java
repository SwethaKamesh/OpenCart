package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTestClass;

public class TC_003_LoginDataDrivenTest extends BaseTestClass{
	
	@Test(dataProvider="LoginData", dataProviderClass=utilities.DataProviders.class)
	public void testLogin(String email, String password, String exp) {
		
		
		log.info("****** Starting TC_003_LoginDataDrivenTest ******");
		try {
			HomePage hp=new HomePage(driver);
			hp.clickMyaccount();
			log.info("Clicked on MyAccount ");
			hp.clickLogin();
			log.info("Clicked on Login ");
			
			LoginPage lp=new LoginPage(driver);
			log.info(" Sending email and Password ");
			lp.setEmail(email);//getting email from config.properties file
			lp.setPassword(password);//getting password from config.properties file
			lp.clickLogin();
			
			MyAccountPage map=new MyAccountPage(driver);
			boolean status=map.isMyAccountPageExists();
			
			if(exp.equalsIgnoreCase("valid")) {
				if(status==true) {
					map.clicklogout();
					Assert.assertTrue(true);
				}
				else {
					Assert.assertTrue(false);
				}
					
			}
			
			if(exp.equalsIgnoreCase("invalid")) {
				if(status==true) {
					map.clicklogout();
					Assert.assertTrue(false);
				}else
					Assert.assertTrue(true);
			}
		}
		catch(Exception e) {
			Assert.fail();
		}
		log.info("Finished TC_003_LoginDataDrivenTest");
	}

}
