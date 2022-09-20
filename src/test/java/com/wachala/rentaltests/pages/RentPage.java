package com.wachala.rentaltests.pages;

import com.wachala.rentaltests.datamodel.CustomerDetails;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class RentPage extends BasePage {

    @FindBy(id = "name")
    private WebElement name;

    @FindBy(id = "last_name")
    private WebElement lastName;

    @FindBy(id = "card_number")
    private WebElement cardNumber;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(css = "#rent_form > button")
    private WebElement rentButton;

    @FindBy(css = "#content > div > div")
    private WebElement summaryData;


    public RentPage(WebDriver driver) {
        super(driver);
    }

    public void fillOutFormWithUserData(CustomerDetails customerDetails) {
        this.name.sendKeys(customerDetails.getName());
        this.lastName.sendKeys(customerDetails.getLastName());
        this.cardNumber.sendKeys(customerDetails.getCardNumber());
        this.email.sendKeys(customerDetails.getEmail());
    }

    public FinalPage clickRentButtonAndProceed() {
        this.rentButton.click();
        return new FinalPage(this.driver);
    }
}
