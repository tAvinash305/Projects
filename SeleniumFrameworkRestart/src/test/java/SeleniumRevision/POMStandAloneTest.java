package SeleniumRevision;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import SeleniumRevision.POM.CartPage;
import SeleniumRevision.POM.CheckoutPage;
import SeleniumRevision.POM.OrdersHistoryPage;
import SeleniumRevision.POM.ProductCatalogPage;
import SeleniumRevision.POM.ThankYouPage;
import SeleniumRevision.TestComponents.BaseTest;

public class POMStandAloneTest extends BaseTest {
	String productName = "ADIDAS ORIGINAL";
	public String id;
	
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> inputData) throws InterruptedException {
		ProductCatalogPage catalogPage = landingPage.loginToApplication(inputData.get("email"), inputData.get("password"));
		catalogPage.addProductToCart(inputData.get("product"));

		CartPage cartPage = catalogPage.goToCartPage();
		Boolean match = cartPage.verifyProductAddedIsPresentInCartPage(inputData.get("product"));
		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
		checkoutPage.selectCountry(inputData.get("partialCountryName"), inputData.get("countryName"));
		checkoutPage.applyCoupon(inputData.get("coupon"));

		ThankYouPage tyPage = checkoutPage.goToThankYouPage();
		String thankyouMSG = tyPage.getThankYouPage();
		Assert.assertTrue(thankyouMSG.equalsIgnoreCase(inputData.get("tyMSG")));
		id = tyPage.getOrderID();

		OrdersHistoryPage ordersPage = catalogPage.goToOrdersPage();
		String pName = ordersPage.verifyOrdersPage(id);
		Assert.assertEquals(pName, inputData.get("product"));
	}

	@Test(dependsOnMethods = {"submitOrder"})
	public void verifyProductInOrdersPage() throws InterruptedException {
		ProductCatalogPage catalogPage = landingPage.loginToApplication("avinash123@gmail.com", "a12345@A");
		OrdersHistoryPage ordersPage = catalogPage.goToOrdersPage();
		String pName = ordersPage.verifyProductInOrdersPage();
		Assert.assertEquals(pName, productName);
		Thread.sleep(5000);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
//		return new Object[][] {{"avinash123@gmail.com", "a12345@A", "ADIDAS ORIGINAL"}, {"avinash0062@gmail.com","Avinash0062@1", "ZARA COAT 3"}};
		
		List<HashMap<String, String>> data = getJSONDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\SeleniumRevision\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
}
