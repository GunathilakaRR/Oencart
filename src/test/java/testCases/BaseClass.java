package testCases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClass {

public static WebDriver driver;
public Logger logger;
public Properties p;
	
	@BeforeClass(groups= {"Sanity", "Regression", "Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException {
		
		//loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		Properties p = new Properties();
		p.load(file);
		
		//logging
		logger = LogManager.getLogger(this.getClass());
		
		
		//if execution environment remote
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN10);
			}else if(os.equalsIgnoreCase("linux")){
				capabilities.setPlatform(Platform.LINUX);
			}else if(os.equalsIgnoreCase("mac")){
				capabilities.setPlatform(Platform.MAC);
			}else {
				System.out.println("No matching");
				return;
			}
			
			//browser
			switch(br.toLowerCase()) {
				case "chrome":capabilities.setBrowserName("chrome");
				break;
				case "firefox":capabilities.setBrowserName("firefox");
				break;
				case "edge":capabilities.setBrowserName("MicrosoftEdge");
				break;
				default: System.out.println("no matchinf browser");
			}
			
			driver = new RemoteWebDriver(new URL("http://192.168.137.1:4444/wd/hub"),capabilities);
		}
		
		
		//if execution environment is local
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			//browser selection
			switch(br.toLowerCase()) {
				case "chrome":driver = new ChromeDriver(); break;
				case "edge":driver = new EdgeDriver(); break;
				default : System.out.println("invalid browser"); return;
			}
		}
		
		
	
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL2"));
		driver.manage().window().maximize();
		
//		driver = new ChromeDriver();
//		driver.manage().deleteAllCookies();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.get("http://localhost/OpenCart/");
//		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Sanity", "Regression", "Master"})
	public void teardown() {
		driver.quit();
	}
	
	
	
	//method to generate random emails
		public String randomString() {
			String generatedString = RandomStringUtils.randomAlphabetic(5);
			return generatedString;
		}
		
		
		//method to generate random tele number
//		public String randomNumber() {
//			String number = RandomStringUtils.randomNumeric(10)
//			return number;
//		}
		
		
		//method to generate password 
		public String password() {
			String generatedString = RandomStringUtils.randomAlphabetic(3);
			String number = RandomStringUtils.randomNumeric(3);
			return(generatedString+"@"+number);
		}
	
		
		
		public String captureScreen(String tname) throws IOException{
			String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
			
			String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
			File targetFile = new File(targetFilePath);
			
			sourceFile.renameTo(targetFile);
			
			return targetFilePath;
		}
	
	
	
}
