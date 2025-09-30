package SeleniumRevision.AbstractComponents;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import SeleniumRevision.POM.CartPage;
import SeleniumRevision.POM.OrdersHistoryPage;

public class AbstractComponent {
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink*='cart']")
	WebElement cartBtn;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersBtn;
	
	
	public void waitForElementVisibility(By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	public void waitForWebElementVisibility(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public CartPage goToCartPage() throws InterruptedException {
		Thread.sleep(1000);
		cartBtn.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrdersHistoryPage goToOrdersPage() {
		ordersBtn.click();
		OrdersHistoryPage ordersHistoryPage = new OrdersHistoryPage(driver);
		return ordersHistoryPage;
	}
}
