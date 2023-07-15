package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseTestClass;

public class TC_001_AccountRegistrationTest extends BaseTestClass{
	
	
	@Test(groups= {"Regression","Master"})
	void test_Account_Registration() {
		
		try {
			
			log.info("*********** Starting TC_001_AccountRegistrationTest ************");
		
			HomePage hp=new HomePage(driver);
			log.info("Clicked on My account link");
			hp.clickMyaccount();
			log.info("Clicked on Registration link");
			hp.clickRegister();
			
			AccountRegistrationPage arp=new AccountRegistrationPage(driver);
			
			log.info("Sending Customer Data");
			arp.setFirstName(randomeString());
			arp.setLastName(randomeString());
			arp.setEmail(randomeString()+"@gmail.com");
			arp.setTelephone(randomeNumber());
			
			String passwd=alphaNumeric();
			arp.setPassword(passwd);
			arp.setCnfPassword(passwd);
			
			arp.setPrivacyPolicy();
			arp.clickContinue();
			String act_CnfMsg=arp.getConfirmationMsg();
			
			log.info("Validating Registration Msg");
			Assert.assertEquals(act_CnfMsg, "Your Account Has Been Created!");
			log.info("**************	Finished TC_001_AccountRegistrationTest	***************");
		}
		catch(Exception e) {
			log.info("Test Failed");
			Assert.fail();
		}
		
		
		
	}

}
