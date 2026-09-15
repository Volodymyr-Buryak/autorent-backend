package com.carhub.carrental.Fleet.domain.model.valueobject;

import java.util.Locale;
import java.util.regex.Pattern;
import com.carhub.carrental.Common.exception.DomainAssert;

public record LicensePlate(String value) {

    private static final Pattern VALID_PLATE_PATTERN = Pattern.compile("^[A-Z]{2}\\d{4}[A-Z]{2}$");

    public LicensePlate {
        DomainAssert.notBlank(
                value, "car.licensePlate.blank", "License plate cannot be null or blank"
        );

        value = value.trim().toUpperCase(Locale.ROOT);
        DomainAssert.isConditionTrue(
                VALID_PLATE_PATTERN.matcher(value).matches(),
                "car.licensePlate.invalid",
                "Invalid license plate format: '" + value + "'"
        );
    }
}