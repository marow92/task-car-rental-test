package com.wachala.rentaltests;

import com.wachala.rentaltests.common.BaseTest;
import com.wachala.rentaltests.datamodel.CustomerDetails;
import com.wachala.rentaltests.datamodel.ResultEntity;
import com.wachala.rentaltests.datamodel.SearchFormEntity;
import com.wachala.rentaltests.pages.DetailsPage;
import com.wachala.rentaltests.pages.FinalPage;
import com.wachala.rentaltests.pages.MainPage;
import com.wachala.rentaltests.pages.RentPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static com.wachala.rentaltests.common.RentalConfig.RENTAL_SUCCESS_URL;
import static com.wachala.rentaltests.common.TestUtils.compareSearchResultsWithExpectedOnes;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentalTest extends BaseTest {

    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        mainPage = new MainPage(driver);
    }

    @Test
    public void should_go_through_all_steps_and_rent_car_successfully() {
        //GIVEN
        SearchFormEntity searchData = SearchFormEntity
                .builder()
                .countryName("Poland")
                .city("Cracow")
                .model("Toyota")
                .pickUpDate(LocalDate.of(2022, 12, 24))
                .dropOffDate(LocalDate.of(2022, 12, 24))
                .build();

        //TODO: randomize user data
        CustomerDetails customerDetails = CustomerDetails
                .builder()
                .name("John")
                .lastName("Snow")
                .cardNumber("376927526470562")
                .email("john@snow.com")
                .build();

        DetailsPage detailsPage;
        RentPage rentPage;
        FinalPage finalPage;

        //WHEN
        mainPage.fillOutTheSearchingForm(searchData);
        detailsPage = mainPage.selectFirstCarAndProceed();
        rentPage = detailsPage.clickRentButton();
        rentPage.fillOutFormWithUserData(customerDetails);
        finalPage = rentPage.clickRentButtonAndProceed();

        //THEN
        //TODO: add more reliable assertions to verify that reservation was created successfully
        assertEquals(RENTAL_SUCCESS_URL, finalPage.getCurrentPageUrl());
    }

    @Test
    public void should_display_suitable_search_results() {
        //GIVEN
        SearchFormEntity searchData = SearchFormEntity
                .builder()
                .countryName("Poland")
                .city("Wroclaw")
                .model("Toyota")
                .pickUpDate(LocalDate.of(2022, 12, 24))
                .dropOffDate(LocalDate.of(2022, 12, 25))
                .build();
        List<ResultEntity> resultsEntityList;

        //WHEN
        mainPage.fillOutTheSearchingForm(searchData);
        mainPage.clickSearchButton();
        resultsEntityList = mainPage.extractSearchResults();

        //THEN
        compareSearchResultsWithExpectedOnes(searchData, resultsEntityList, driver);
    }

}
