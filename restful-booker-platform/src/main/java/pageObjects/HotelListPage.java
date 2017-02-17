package pageObjects;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumhq.jetty9.util.log.Log;
import org.seleniumhq.jetty9.util.log.Logger;
import org.testng.Assert;

public class HotelListPage extends LoadableComponent<HotelListPage> {
	WebDriver driver;
	@FindBy(how = How.ID, using = "hotelName")
	WebElement hotelName;
	@FindBy(how = How.ID, using = "address")
	WebElement address;
	@FindBy(how = How.ID, using = "owner")
	WebElement owner;
	@FindBy(how = How.ID, using = "phone")
	WebElement phoneNumber;
	@FindBy(how = How.ID, using = "email")
	WebElement email;
	@FindBy(how = How.ID, using = "createHotel")
	WebElement createHotel;
	@FindBy(how = How.ID, using = "5")
	WebElement deleteHotelButton;
	@FindBy(how = How.ID, using = "container")
	WebElement tableOfHotels;
	@FindBy(how = How.ID, using = "row detail")
	WebElement tableRow;
	@FindBy(how = How.CLASS_NAME, using = "row")
	WebElement entryRow;

	public HotelListPage(WebDriver driver) {
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

	public void addNewHotel(String tempHotelName, String tempAddress, String tempOwner, String telNumber,
			String tempEmail) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(hotelName));
		assert ExpectedConditions.elementToBeSelected(hotelName) != null;
		hotelName.clear();
		hotelName.sendKeys(tempHotelName);
		assert ExpectedConditions.textToBePresentInElement(hotelName, tempHotelName) != null;
		address.clear();
		address.sendKeys(tempAddress);
		assert ExpectedConditions.textToBePresentInElement(address, tempAddress) != null;
		owner.clear();
		owner.sendKeys(tempOwner);
		assert ExpectedConditions.textToBePresentInElement(owner, tempOwner) != null;
		phoneNumber.clear();
		phoneNumber.sendKeys(telNumber);
		assert ExpectedConditions.textToBePresentInElementValue(phoneNumber, telNumber) != null;
		email.clear();
		email.sendKeys(tempEmail);
		createHotel.click();
		assert ExpectedConditions.textToBePresentInElement(email, tempEmail) != null;
	}

	public void addNewHotels() {
		for (int i = 0; i < 5; i++) {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(hotelName));
			try {
				hotelName.clear();
				hotelName.sendKeys("HotelName" + i);
			} catch (org.openqa.selenium.StaleElementReferenceException ex) {
				java.util.logging.Logger.getLogger("Exception in finding Hotel name");
				Log.setLog((Logger) ex);
				WebElement hotelName1 = driver.findElement(By.id("//*[@id='username']"));
				hotelName1.click();
			}
			assert ExpectedConditions.elementToBeSelected(hotelName) != null;
			System.out.println("got to here3.5");
			hotelName.clear();
			hotelName.sendKeys("HotelName" + i);
			assert ExpectedConditions.textToBePresentInElement(hotelName, "HotelName" + i) != null;
			address.clear();
			address.sendKeys("Address" + i);
			assert ExpectedConditions.textToBePresentInElement(address, "Address" + i) != null;
			owner.clear();
			owner.sendKeys("Owner" + i);
			assert ExpectedConditions.textToBePresentInElement(owner, "Owner" + i) != null;
			String randomPhoneNumber = String.valueOf(generateRamdomPhoneNumber(null));
			phoneNumber.clear();
			phoneNumber.sendKeys(randomPhoneNumber);
			assert ExpectedConditions.textToBePresentInElementValue(phoneNumber, randomPhoneNumber) != null;
			email.clear();
			email.sendKeys("HotelName" + "@yahoo.com");
			createHotel.click();
			assert ExpectedConditions.textToBePresentInElement(email, "HotelName" + "@yahoo.com") != null;
		}
	}

	public void deleteHotel(String nameOfHotelToDelete) {
		System.out.println(ExpectedConditions.textToBePresentInElement(hotelName, nameOfHotelToDelete) != null);
		System.out.println("gee2");
		// get the index of the row of the Hotel I want to delete
		List<WebElement> tableRows = driver.findElements(By.className("hotelRow"));
		System.out.println(tableRows.size());
		// find the index of the row that has the hotel record we want to delete
		// NB: hotel row index starts at 2
		for (int i = 2; i < tableRows.size() + 2; i++) {
			String hotelNameValue = driver.findElement(By.xpath("//html/body/div[1]/div[" + i + "]/div[1]/div[1]/p"))
					.getText();
			if (hotelNameValue.equals(nameOfHotelToDelete)) {
				System.out.println("Index of row to delete: " + i);
				// At this point we would find the delete button of the row and
				// then click it, if the delete button was clickable
				WebElement deleteButton = driver.findElement(By.xpath("//html/body/div[1]/div[" + i + "]/div[2]/span"));
				System.out.println("assert ExpectedConditions.elementToBeClickable(deleteButton) != null");
				deleteButton.click();
				break;
			}
		}

	}

	public void addBookingToHotel() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(hotelName));
		assert ExpectedConditions.elementToBeClickable(deleteHotelButton) != null;
		// return PageFactory.initElements(driver, hotelBookingHomePage.class);
	}

	public static String generateRamdomPhoneNumber(String[] args) {
		int num1, num2, num3; // 3 numbers in area code
		int set2, set3; // sequence 2 and 3 of the phone number

		Random generator = new Random();
		// Area code number; Will not print 8 or 9
		num1 = generator.nextInt(7) + 1; // add 1 so there is no 0 to begin
		num2 = generator.nextInt(8); // randomize to 8 becuase 0 counts as a
										// number in the generator
		num3 = generator.nextInt(8);

		// Sequence two of phone number
		// the plus 100 is so there will always be a 3 digit number
		// randomize to 643 because 0 starts the first placement so if i
		// randomized up to 642 it would only go up yo 641 plus 100
		// and i used 643 so when it adds 100 it will not succeed 742
		set2 = generator.nextInt(643) + 100;

		// Sequence 3 of numebr
		// add 1000 so there will always be 4 numbers
		// 8999 so it wont succed 9999 when the 1000 is added
		set3 = generator.nextInt(8999) + 1000;

		// System.out.println ( "(" + num1 + "" + num2 + "" + num3 + ")" + "-" +
		// set2 + "-" + set3 );
		String randomPhoneNumber = ("(" + num1 + "" + num2 + "" + num3 + ")" + "-" + set2 + "-" + set3);
		return randomPhoneNumber;

	}

	@Override
	protected void load() {
		driver.get("http://localhost:3003/");
	}

	@Override
	protected void isLoaded() throws Error {
		Assert.assertTrue(driver.getCurrentUrl().contains("localhost:3003"));
	}
}
