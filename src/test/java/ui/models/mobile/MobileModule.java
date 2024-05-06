package ui.models.mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MobileModule {

WebDriver driver;

	@FindBy(xpath="//a[@href='/login']")
	public
	WebElement Login;

	@FindBy(xpath="//input[@id='login_form_username_email']")
	public
	WebElement Username;

	@FindBy(xpath="//input[@id='login_form_password']")
	public
	WebElement Password;

	@FindBy(xpath="//button[@data-pa-name='login']")
	public
	WebElement LoginButton;

	@FindBy(xpath="//iframe[@title='reCAPTCHA']")
	public
	WebElement Captcha;

	@FindBy(xpath="//div[@id='rc-imageselect']")
	public
	WebElement imageCaptcha;

	@FindBy(xpath="//img[@class='user-image user-image--s']")
	public
	WebElement AccountImage;

	@FindBy(xpath="//div[@class='recaptcha-checkbox-border']")
	public
	WebElement captchbutton;

	public MobileModule(WebDriver d){
		this.driver=d;
		PageFactory.initElements(d,this);
	}

	public void Login(String Usernam,String Passwor) throws InterruptedException {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		Login.click();
		Username.click();
		Thread.sleep(2000);
		Username.sendKeys(Usernam);
		Password.click();
		Thread.sleep(2000);
		Password.sendKeys(Passwor);
		Thread.sleep(2000);
		LoginButton.click();
		wait.until(ExpectedConditions.visibilityOf(Captcha));
		driver.switchTo().frame(Captcha);
		wait.until(ExpectedConditions.elementToBeClickable(captchbutton));
		captchbutton.click();
		Thread.sleep(15000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement shadowHost = driver.findElement(By.id("rc-imageselect"));
		WebElement shadowRoot = expandRootElement(shadowHost);
		WebElement solverButton = shadowRoot.findElement(By.cssSelector(".button-holder #solver-button"));
		wait.until(ExpectedConditions.elementToBeClickable(solverButton));
		solverButton.click();
		wait.until(ExpectedConditions.visibilityOf(AccountImage));
	}
	private WebElement expandRootElement(WebElement element) {
		WebElement ele = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].shadowRoot", element);
		return ele;
	}


}