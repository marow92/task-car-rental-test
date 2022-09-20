package com.wachala.rentaltests.common;

import com.wachala.rentaltests.datamodel.ResultEntity;
import com.wachala.rentaltests.datamodel.SearchFormEntity;
import com.wachala.rentaltests.pages.DetailsPage;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestUtils {

    public static void compareSearchResultsWithExpectedOnes(SearchFormEntity expectedEntities,
                                                            List<ResultEntity> resultEntities, WebDriver driver) {
        resultEntities
                .forEach(result -> {
                    driver.get(String.format(RentalConfig.DETAILS_URL + "%s", result.getId()));
                    DetailsPage detailsPage = new DetailsPage(driver);

                    LocalDate pickUpDate = detailsPage.extractPickupDate();
                    LocalDate dropOffDate = detailsPage.extractDropOffDate();

                    assertAll(
                            () -> assertEquals(expectedEntities.getCountryName(), detailsPage.extractCountry()),
                            () -> assertEquals(expectedEntities.getCity(), detailsPage.extractCity()),
                            () -> assertEquals(expectedEntities.getPickUpDate(), pickUpDate),
                            () -> assertEquals(expectedEntities.getDropOffDate(), dropOffDate),
                            () -> assertEquals(calculateExpectedPrice(result, pickUpDate, dropOffDate), result.getPrice()),
                            () -> assertTrue(detailsPage.getCarModel().getText().contains(expectedEntities.getModel()), detailsPage.getCarModel().getText() + " doesn't contain " + expectedEntities.getModel())
                    );
                });
    }

    private static long calculateExpectedPrice(ResultEntity resultEntity, LocalDate pickUpDate, LocalDate dropOffDate) {
        long daysToCount = ChronoUnit.DAYS.between(pickUpDate, dropOffDate) + 1;
        return daysToCount * resultEntity.getPricePerDay();
    }
}
