package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups={"Sanity", "Master"})
	public void verify_login() {
		logger.info("**** Starting login test ****");
		
		logger.info("**** home page ****");
		try {
		//home page
		HomePage hp = new HomePage(driver);
		hp.cliclMyAccount();
		hp.clickLogin();
		
		
		
		logger.info("**** login page ****");
		//login page
		LoginPage lp = new LoginPage(driver);
		lp.setEmail("admin@gmail.com");
		lp.setPassword("admin");
		lp.clickLogin();
		
		
		
		
		logger.info("**** my account page ****");
		//my account page
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();
		
		//Assert.assertEquals(targetPage, true, "Login failed.........");
		Assert.assertTrue(targetPage);
		
		}catch(Exception e){
			System.out.println(e);
			Assert.fail();
		}
		
		logger.info("**** Finished login test ****");
	}

}
