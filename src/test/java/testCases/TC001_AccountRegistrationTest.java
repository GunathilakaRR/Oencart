package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	
	
	@Test(groups={"Regression", "Master"})
	public void verify_account_registration() {
		
		logger.info("**** Starting TC001_AccountRegistrationTest ****");
		
		try {
		HomePage hp = new HomePage(driver);
		hp.cliclMyAccount();
		logger.info("Clicked on my account");
		
		hp.clickRegister();
		logger.info("Clicked on register link");
		
		
		
		AccountRegistrationPage ac = new AccountRegistrationPage(driver);
		logger.info("Providing customer details");
		
		ac.setFirstName("rusiru");
		ac.setLastName("rajitha");
		ac.setemail(randomString()+"gmail.com");
		ac.setPassword(password());
		ac.clickAgreeBtn();
		ac.clickContinueBtn();
		
		
		
		}catch(Exception e) {
			logger.error("Test failed");
			logger.debug("Debug logs");
			Assert.fail();
		}
		logger.info("****Finished TC001_AccountRegistrationTest****");
	}
	
	
	

}
