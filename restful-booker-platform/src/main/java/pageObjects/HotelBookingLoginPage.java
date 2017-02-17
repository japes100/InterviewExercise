package pageObjects;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HotelBookingLoginPage extends LoadableComponent<HotelBookingLoginPage> {
	WebDriver driver;
	@FindBy(how = How.LINK_TEXT, using = "Login")
	WebElement loginButtonOnHomePage;

	@FindBy(how = How.ID, using = "Login")
	WebElement loginButtonOnLoginPopup;

	@FindBy(how = How.ID, using = "logout")
	static WebElement logoutButtonOnHomePage;

	@FindBy(how = How.ID, using = "username")
	WebElement usernameField;

	@FindBy(how = How.ID, using = "password")
	WebElement passwordField;

	@FindBy(how = How.ID, using = "doLogin")
	WebElement submitLogin;

	@FindBy(how = How.ID, using = "btn btn-default")
	WebElement closeLoginPopup;

	public HotelBookingLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		driver.manage().window().maximize();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HotelListPage login() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(loginButtonOnHomePage));
		assert ExpectedConditions.elementToBeClickable(loginButtonOnHomePage) != null;
		loginButtonOnHomePage.click();
		System.out.println("got to here3.3");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.getWindowHandle();
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles(); // get all window
															// handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler); // switch to popup window
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='username']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;",
				driver.findElement(By.xpath("//*[@id='username']")));
		// perform operations on popup
		driver.findElement(By.xpath("//*[@id='username']")).clear();
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys("admin");
		driver.findElement(By.xpath("//*[@id='password']")).clear();
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("password");
		System.out.println("got to here3.4");
		submitLogin.click();
		return PageFactory.initElements(driver, HotelListPage.class);
	}

	@Override
	protected void load() {
		driver.get("http://localhost:3003/");
	}

	@Override
	protected void isLoaded() throws Error {
		Assert.assertTrue(driver.getCurrentUrl().contains("localhost:3003"));
	}

	public WebElement getLogoutButtonOnHomePage() {
		return logoutButtonOnHomePage;
	}

	public void setLogoutButtonOnHomePage(WebElement logoutButtonOnHomePage) {
		HotelBookingLoginPage.logoutButtonOnHomePage = logoutButtonOnHomePage;
	}
}
