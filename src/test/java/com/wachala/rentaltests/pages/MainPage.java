package com.wachala.rentaltests.pages;

import com.wachala.rentaltests.common.DateUtils;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import com.wachala.rentaltests.datamodel.ResultEntity;
import com.wachala.rentaltests.datamodel.SearchFormEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.wachala.rentaltests.common.RentalConfig.RENTAL_URL;

@Getter
public class MainPage extends BasePage {

    @FindBy(id = "country")
    private WebElement countrySelector;

    @FindBy(id = "city")
    private WebElement citySelector;

    @FindBy(id = "model")
    private WebElement modelInput;

    @FindBy(id = "pickup")
    private WebElement pickUpDateInput;

    @FindBy(id = "dropoff")
    private WebElement dropOffDateInput;

    @FindBy(id = "search-results")
    private WebElement searchResultsTable;

    @FindBy(css = "#search_form > button")
    private WebElement searchButton;

    public MainPage(WebDriver driver) {
        super(driver);

        driver.get(RENTAL_URL);
    }

    public void selectCountry(String countryName) {
        Select countrySelector = new Select(this.getCountrySelector());
        countrySelector.selectByVisibleText(countryName);
    }

    public void selectCity(String city) {
        Select countrySelector = new Select(this.getCitySelector());
        countrySelector.selectByVisibleText(city);
    }

    public void typeModel(String model) {
        this.getModelInput().clear();
        this.getModelInput().sendKeys(model);
    }

    public void selectPickUpDate(LocalDate date) {
        this.getPickUpDateInput().click();
        this.getPickUpDateInput().sendKeys(date.format(DateUtils.dayMonthYearFormatter));
    }

    public void selectDropOffDate(LocalDate date) {
        this.getDropOffDateInput().click();
        this.getDropOffDateInput().sendKeys(date.format(DateUtils.dayMonthYearFormatter));
    }

    public void clickSearchButton() {
        this.getSearchButton().click();
    }

    public void fillOutTheSearchingForm(SearchFormEntity searchFormEntity) {
        this.selectCountry(searchFormEntity.getCountryName());
        this.selectCity(searchFormEntity.getCity());
        this.typeModel(searchFormEntity.getModel());
        this.selectPickUpDate(searchFormEntity.getPickUpDate());
        this.selectDropOffDate(searchFormEntity.getDropOffDate());
        this.clickSearchButton();
    }

    public DetailsPage selectFirstCarAndProceed() {
        List<WebElement> carList = this.getSearchResultsTable().findElements(new By.ByTagName("tr"));
        if (carList.isEmpty())
            throw new IllegalStateException("Expected at least one car but 0 found!");
        carList.get(1).findElement(new By.ByCssSelector("td > a")).click();
        return new DetailsPage(this.driver);
    }

    public List<ResultEntity> extractSearchResults() {
        List<WebElement> carList = this.getSearchResultsTable().findElements(new By.ByTagName("tr"));
        carList.remove(0);
        List<ResultEntity> resultEntities = new ArrayList<>();

        for (WebElement carElement : carList) {
            List<WebElement> carInfo = carElement.findElements(new By.ByTagName("td"));
            String carId = carElement.findElement(new By.ByTagName("th")).getText();

            //TODO consider throwing exception when less than 5 elements found

            resultEntities.add(ResultEntity.builder()
                    .id(carId)
                    .company(carInfo.get(0).getText())
                    .model(carInfo.get(1).getText())
                    .licensePlate(carInfo.get(2).getText())
                    .price(Long.parseLong(carInfo.get(3).getText().replace("$", "")))
                    .pricePerDay(Long.parseLong(carInfo.get(4).getText().replace("$", "")))
                    .build()
            );
        }
        return resultEntities;
    }
}
