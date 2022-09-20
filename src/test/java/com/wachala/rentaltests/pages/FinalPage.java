package com.wachala.rentaltests.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class FinalPage extends BasePage {

    @FindBy(css = "#info > p")
    private WebElement infoParagraph;

    public FinalPage(WebDriver driver) {
        super(driver);
    }

    public String extractMessageFromInfo() {
        return this.infoParagraph.getText();
    }
}
