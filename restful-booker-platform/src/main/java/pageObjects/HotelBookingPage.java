package pageObjects;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;

public class HotelBookingPage extends LoadableComponent<HotelBookingPage> {
	WebDriver driver;

	@FindBy(how = How.ID, using = "firstName")
	WebElement firstName;

	@FindBy(how = How.ID, using = "lastName")
	WebElement lastName;

	@FindBy(how = How.ID, using = "totalPrice")
	WebElement price;

	@FindBy(how = How.ID, using = "depositPaid")
	WebElement depositPaid;

	@FindBy(how = How.ID, using = "checkIn")
	WebElement checkIn;

	@FindBy(how = How.ID, using = "checkOut")
	WebElement checkOut;

	@FindBy(how = How.ID, using = "createBooking")
	WebElement createBooking;

	@FindBy(how = How.ID, using = "glyphicon glyphicon-remove exitBookingEdit")
	WebElement deleteBookingButton;

	@FindBy(how = How.ID, using = "glyphicon glyphicon-pencil bookingEdit")
	WebElement editBookingButton;

	@FindBy(how = How.ID, using = "glyphicon glyphicon-ok confirmBookingEdit")
	WebElement confirmEditBookingButton;

	public HotelBookingPage(WebDriver driver) {
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

	public void deleteHotelBooking() {
		// need to list and then select a specific Hotel listing - maybe the
		// first in the list
		// select the delete button and click it
		// verify that the Hotel is no longer listed
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		assert ExpectedConditions.invisibilityOfElementLocated((By) deleteBookingButton) != null;
		deleteBookingButton.click();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		assert ExpectedConditions.invisibilityOfElementLocated((By) deleteBookingButton) != null;
	}

	public void addNewHotelBooking() {
		for (int i = 1; i < 2; i++) {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			assert ExpectedConditions.presenceOfElementLocated((By) firstName) != null;

			firstName.sendKeys("FirstName" + i);
			assert ExpectedConditions.textToBePresentInElement(firstName, "FirstName" + i) != null;

			lastName.sendKeys("LastName" + i);
			assert ExpectedConditions.textToBePresentInElement(lastName, "LastName" + i) != null;

			int priceOfBooking = generateRandomPrice();
			price.sendKeys(Integer.toString(priceOfBooking));
			assert ExpectedConditions.textToBePresentInElement(price, (Integer.toString(priceOfBooking))) != null;

			String randomBooleon = String.valueOf(generateRandomBoolean());
			depositPaid.sendKeys(randomBooleon);
			assert ExpectedConditions.textToBePresentInElementValue(depositPaid, randomBooleon) != null;

			// generate a date to start booking - use random date function maybe
			// Date today = get todays date;
			// String dateIN = pass in todays date and generate a date >= to it
			// for dateIN
			Date todayDate = new Date();
			System.out.print("Today's date = " + todayDate.toString());
			String checkINdate = generateRandomCheckINDate(todayDate);
			checkIn.sendKeys(checkINdate);
			assert ExpectedConditions.textToBePresentInElementValue(checkIn, checkINdate) != null;

			// generate a date to start booking - use random date function maybe
			String checkOUTdate = generateRandomCheckOUTDate(checkINdate);
			checkOut.sendKeys(checkOUTdate);
			assert ExpectedConditions.textToBePresentInElementValue(checkOut, checkOUTdate) != null;

			createBooking.click();
			assert ExpectedConditions.textToBePresentInElement(createBooking, "HotelName" + i + "@yahoo.com") != null;
		}
	}

	public void addBookingToHotel() {
		// identify the Hotel to which a booking is to be added
		// click on the HotelName field
		// this should open the Booking entry page - verify this
		// enter the booking details and submit the new booking
		// verify that the booking is saved
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		assert ExpectedConditions.presenceOfElementLocated((By) deleteBookingButton) != null;
		// clickDelete.click();
		// return PageFactory.initElements(driver, hotelBookingHomePage.class);
	}

	public static int generateRandomPrice() {
		Random generate = new Random();
		int minvalue = 100;
		int maxvalue = 1000;
		int randomIntRange = generate.nextInt(maxvalue - minvalue + 1) + minvalue;
		return randomIntRange;
	}

	public boolean generateRandomBoolean() {
		Random generate = new Random();
		boolean randomBoolean = generate.nextBoolean();
		System.out.println(randomBoolean);
		return randomBoolean;
	}

	public String generateRandomCheckINDate(Date todayDate) {
		String checkINDate = "28/11/2016";
		return checkINDate;
	}

	public String generateRandomCheckOUTDate(String checkINdate) {
		String checkOUTDate = "29/11/2016";
		return checkOUTDate;
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub

	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}
}