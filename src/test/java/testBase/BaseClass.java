package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;



public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;	//Log4j
	public Properties p;	//propertiesfile
	
	
	@BeforeClass(groups= {"Sanity", "Regression", "Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException {
		
		//loading properties file
		FileInputStream file = new FileInputStream("./src//test//resources//config.properties");
		
		p=new Properties();
		p.load(file);
		
		logger= LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")){
			
			
			DesiredCapabilities  capabilities = new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}
			
			else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}
			
			else if(os.equalsIgnoreCase("Linux")) {
				capabilities.setPlatform(Platform.LINUX);
			}
			
			else {
				System.out.println("No matching operating systems");
				return;
			}
			
			//browser
			switch(br.toLowerCase()) {
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox": capabilities.setBrowserName("firefox"); break;
			default: System.out.println("No matching browsers"); return;
			}
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
		
		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		
		EdgeOptions options1 = new EdgeOptions();
		options1.setAcceptInsecureCerts(true);
		
		FirefoxOptions options2 = new FirefoxOptions();
		options2.setAcceptInsecureCerts(true);
		
		switch(br.toLowerCase()) {
		case "chrome": driver = new ChromeDriver(options); break;
		case "edge": driver = new EdgeDriver(options1); break;
		case "firefox": driver = new FirefoxDriver(options2); break;
		default:System.out.println("Invalid input for browser"); return;
		
		}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));	//reading URL from properties file
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Sanity", "Regression", "Master"})
	public void teardown() {
		driver.quit();
		
	}
	
	public String randomstring(){
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
		
	}
	
	public String randomnumber(){
		String generatednumber = RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
	
	public String randomalphanumeric() {
		String generatedstring = RandomStringUtils.randomAlphabetic(4);
		String generatednumber = RandomStringUtils.randomNumeric(3);
		return (generatedstring+generatednumber);
	}
	
	public String captureScreen(String tname) throws IOException{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+ tname +"_" + timeStamp +".png";
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}
	

}
