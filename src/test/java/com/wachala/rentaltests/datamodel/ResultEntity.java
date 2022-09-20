package com.wachala.rentaltests.datamodel;

import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class ResultEntity {
    String id;
    String company;
    String model;
    String licensePlate;
    Long price;
    Long pricePerDay;
}
