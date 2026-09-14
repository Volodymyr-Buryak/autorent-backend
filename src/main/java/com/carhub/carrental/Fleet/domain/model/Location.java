package com.carhub.carrental.Fleet.domain.model;

import lombok.*;
import com.carhub.carrental.Common.exception.DomainAssert;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Location {
    @EqualsAndHashCode.Include
    Long id;

    String city;
    String street;
    String building;

    @Builder
    public Location(String city, String street, String building) {
        DomainAssert.notBlank(city, "location.city.blank", "City cannot be blank");
        DomainAssert.notBlank(street, "location.street.blank", "Street cannot be blank");
        DomainAssert.notBlank(building, "location.building.blank", "Building cannot be blank");

        this.city = city.trim();
        this.street = street.trim();
        this.building = building.trim();
    }
}
