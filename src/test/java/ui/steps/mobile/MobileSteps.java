package ui.steps.mobile;

import ui.models.mobile.MobileModule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class MobileSteps {
	public WebDriver driver;
	MobileModule mobileModule;

	@BeforeTest
	@Parameters({"browser", "headless"}) // Add "headless" as a parameter
	public void navigateToURL(String browser, String headless) {
		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-infobars");
			options.addExtensions(new File("src/test/resources/Buster-Captcha-Solver-for-Humans.crx"));
			if (headless.equalsIgnoreCase("true")) {
				options.setHeadless(true);
			}
			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			if (headless.equalsIgnoreCase("true")) {
				options.setHeadless(true);
			}
			driver = new FirefoxDriver(options);
		} else if (browser.equalsIgnoreCase("edge")) { // Add MS Edge condition
			EdgeOptions options = new EdgeOptions();
			driver = new EdgeDriver(options);

		}

		driver.get("https://poshmark.com/");
		driver.manage().window().maximize();
		mobileModule = new MobileModule(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}


	@Test(priority = 1)
	@Parameters({"Usernam", "Passwor"})
	public void Login(String Usernam,String Passwor) throws InterruptedException {

		mobileModule.Login(Usernam,Passwor);
	}

}
