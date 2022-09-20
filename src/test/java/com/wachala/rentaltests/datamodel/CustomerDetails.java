package com.wachala.rentaltests.datamodel;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CustomerDetails {
    String name;
    String lastName;
    String cardNumber;
    String email;
}
