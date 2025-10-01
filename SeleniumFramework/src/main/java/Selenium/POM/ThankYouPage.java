package Selenium.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ThankYouPage {
	WebDriver driver;
	public ThankYouPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="td h1")
	WebElement pageTitle;
	
	@FindBy(xpath="(//td[@class='em-spacer-1']/label)[2]")
	WebElement orderID;
	
	public String getThankYouPage() {
		String thankyouMSG = pageTitle.getText();
		return thankyouMSG;
	}
	
	public String getOrderID() {
		String id = orderID.getText();
		return id.split(" ")[1].split(" ")[0];
	}
}
