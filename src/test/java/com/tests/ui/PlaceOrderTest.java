package com.tests.ui;

import com.tests.base.BaseTest;
import com.tests.pages.CartPage;
import com.tests.pages.HomePage;
import com.tests.pages.PlaceOrderModal;
import com.tests.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaceOrderTest extends BaseTest {
    @Test(description = "Verify user can purchase a laptop without login", groups = {"smoke", "regression"})
    public void userCanPurchaseProduct_withoutLogin() {
        HomePage homePage = new HomePage();
        homePage.selectCategory("Laptops");

        ProductPage productPage = homePage.openProductByName("Sony vaio i5");
        String selectedProductName = productPage.getProductTitle();

        productPage.addToCartAndAcceptAlert();
        try { Thread.sleep(1800); } catch (InterruptedException ignored) {}

        CartPage cartPage = new HomePage().goToCart();
        try { Thread.sleep(1800); } catch (InterruptedException ignored) {}
        Assert.assertTrue(cartPage.getNumberOfItems() > 0, "Cart should have at least one item after adding a product");

        int totalPrice = cartPage.getTotalPrice();
        Assert.assertTrue(totalPrice > 0, "Total price should be greater than zero");

        PlaceOrderModal orderModal = cartPage.clickPlaceOrder();
        orderModal.fillOrderForm(
                "Test User",
                "USA",
                "New York",
                "1212121221",
                "12",
                "2025"
        ).clickPurchase();
        try { Thread.sleep(1800); } catch (InterruptedException ignored) {}
        String confirmationDetails = orderModal.getConfirmationDetails();

        Assert.assertTrue(confirmationDetails.toLowerCase().contains("amount"));

        orderModal.confirmOrder();
    }
}