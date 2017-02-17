package Tests;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import pageObjects.HotelBookingLoginPage;
import pageObjects.HotelListPage;

public class hotelBookingTests {
	private WebDriver driver;

	@Before
	public void setVariables() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("autoDismissAlerts", true);
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void start() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("autoDismissAlerts", true);
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	// Login to homepage
	@Test(dependsOnMethods = { "start" })
	public void loginToHotelPage() {
		driver.get("http://localhost:3003/");
		HotelBookingLoginPage loginPage = new HotelBookingLoginPage(driver).get();
		loginPage.login();
	}

	// Add a new Hotels
	// @Test(dependsOnMethods = { "loginToHotelPage" })
	public void addNewHotels() {
		HotelListPage hotelListPage = new HotelListPage(driver).get();
		hotelListPage.addNewHotels();
	}

	// Add a new Hotel
	@Test(dependsOnMethods = { "loginToHotelPage" })
	public void addNewHotel() {
		HotelListPage hotelListPage = new HotelListPage(driver).get();
		hotelListPage.addNewHotel("tempHotelName", "tempAddress", "tempOwner", "02081234123", "tempEmail@yahoo.com");
	}

	// Delete Hotel
	@Test(dependsOnMethods = { "addNewHotel" })
	public void deleteAHotel() {
		HotelListPage hotelListPage = new HotelListPage(driver).get();
		hotelListPage.deleteHotel("tempHotelName");
	}

	// @AfterTest
	public void tearDown() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (driver != null) {
			driver.quit();
		}
	}

}
