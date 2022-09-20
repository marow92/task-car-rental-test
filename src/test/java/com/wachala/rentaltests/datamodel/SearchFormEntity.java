package com.wachala.rentaltests.datamodel;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class SearchFormEntity {
    String countryName;
    String city;
    String model;
    LocalDate pickUpDate;
    LocalDate dropOffDate;
}
