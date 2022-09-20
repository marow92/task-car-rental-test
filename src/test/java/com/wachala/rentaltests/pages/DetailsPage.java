package com.wachala.rentaltests.pages;

import com.wachala.rentaltests.common.DateUtils;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;

@Getter
public class DetailsPage extends BasePage {

    @FindBy(css = "#content > div > div > div.card-header")
    private WebElement carModel;

    @FindBy(css = "#content > div > div > div.card-body > h5")
    private WebElement companyName;

    @FindBy(css = "card-text#content > div > div > div.card-body > p:nth-child(2)")
    private WebElement pricePerDay;

    @FindBy(css = "#content > div > div > div.card-body > p:nth-child(3)")
    private WebElement location;

    @FindBy(css = "#content > div > div > div.card-body > p:nth-child(4)")
    private WebElement licensePlate;

    @FindBy(css = "#content > div > div > div.card-body > h6:nth-child(5)")
    private WebElement pickupDate;

    @FindBy(css = "#content > div > div > div.card-body > h6:nth-child(6)")
    private WebElement dropOffDate;

    @FindBy(css = "#content > div > div > div.card-body > a")
    private WebElement rentButton;

    public DetailsPage(WebDriver driver) {
        super(driver);
    }

    public RentPage clickRentButton() {
        this.getRentButton().click();
        return new RentPage(this.driver);
    }

    public String extractCountry() {
        return this.location.getText().substring(10).split(",")[0];
    }

    public String extractCity() {
        return this.location.getText().substring(10).split(", ")[1];
    }

    public LocalDate extractPickupDate() {
        String pickupDate = this.pickupDate.getText().split("Pickup date: ")[1];
        return DateUtils.stringToLocalDate(pickupDate);
    }

    public LocalDate extractDropOffDate() {
        String dropOffDate = this.dropOffDate.getText().split("Dropoff date: ")[1];
        return DateUtils.stringToLocalDate(dropOffDate);
    }
}
