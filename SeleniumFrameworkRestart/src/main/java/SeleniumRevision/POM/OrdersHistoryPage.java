package SeleniumRevision.POM;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersHistoryPage {
	WebDriver driver;
	public OrdersHistoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tbody tr")
	List<WebElement> rows;
	
	@FindBy(xpath="(//tr[@class='ng-star-inserted']/td)[2]")
	WebElement productName;
	
	public String verifyOrdersPage(String orderID) {
		List<WebElement> r = rows;
		WebElement re= r.stream().filter(pr -> pr.findElement(By.cssSelector("th")).getText().equalsIgnoreCase(orderID)).findFirst().orElse(null);
		String prodName = re.findElement(By.xpath("(//tr[@class='ng-star-inserted']/td)[2]")).getText();
		return prodName;
	}
	
	public String verifyProductInOrdersPage() {
		String prodName = driver.findElement(By.xpath("(//tr[@class='ng-star-inserted']/td)[2]")).getText();
		return prodName;
	}
}
