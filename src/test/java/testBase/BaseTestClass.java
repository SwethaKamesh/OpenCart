package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTestClass {
	
	public static WebDriver driver;
	
	public ResourceBundle rb;
	public Logger log;//logging
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters("browser")
	public void setup(String br) {
		rb=ResourceBundle.getBundle("config");
		log=LogManager.getLogger(this.getClass());//logging
		
		//ChromeOptions options=new ChromeOptions();
		//options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
		if(br.equalsIgnoreCase("chrome"))
			driver=new ChromeDriver();
		else if(br.equalsIgnoreCase("edge"))
			driver= new EdgeDriver();
		else
			driver=new FirefoxDriver();
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(rb.getString("appUrl"));
		driver.manage().window().maximize();
		
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown() {
		driver.quit();
	}
	
	public String randomeString() {
		String genString=RandomStringUtils.randomAlphabetic(5);
		return genString;
	}
	
	public String randomeNumber() {
		String getTelePhone=RandomStringUtils.randomNumeric(10);
		return getTelePhone;
	}
	
	public String alphaNumeric() {
		String st=RandomStringUtils.randomAlphabetic(4);
		String num=RandomStringUtils.randomNumeric(5);
		return(st+"@"+num);
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;

	}


}
