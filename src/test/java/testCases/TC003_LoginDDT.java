package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{

	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="DataDriven")
	public void verify_loginDDT(String email, String pwd, String exp) {
		
				logger.info("**** Starting TC003_LoginDDT ****");
		
		
				try {
					
				
				logger.info("**** home page ****");
				//home page
				HomePage hp = new HomePage(driver);
				hp.cliclMyAccount();
				hp.clickLogin();
				
				
				logger.info("**** login page ****");
				//login page
				LoginPage lp = new LoginPage(driver);
				lp.setEmail(email);
				lp.setPassword(pwd);
				lp.clickLogin();
				
			
				logger.info("**** myaccount page ****");
				//my account page
				MyAccountPage macc = new MyAccountPage(driver);
				boolean targetPage = macc.isMyAccountPageExists();
				
				
//				Data is valid - login success - test pass - logout
//								login fail	- 	test fail
//								
//				Data is invalid - login success - test fail - logout
//									login fail - test pass
				
				if(exp.equalsIgnoreCase("Valid")) {
					if(targetPage==true) {
						macc.clickLogout();
						Assert.assertTrue(true);
					}else {
						Assert.assertTrue(false);
					}
				}//first part completed
				if(exp.equalsIgnoreCase("Invalid")) {
					if(targetPage==true) {
						macc.clickLogout();
						Assert.assertTrue(false);
					}else {
						Assert.assertTrue(true);
					}
				}//second part completed
			}catch(Exception e) {
				Assert.fail();
			}
				
				
				
				logger.info("**** Finished TC003_LoginDDT ****");
				
	}
	
	
	
}
