package com.carhub.carrental.Fleet.domain.model.valueobject;

import java.util.Locale;
import java.util.regex.Pattern;
import com.carhub.carrental.Common.exception.DomainAssert;

public record Vin(String value) {

    private static final Pattern VALID_VIN_PATTERN = Pattern.compile("^[A-HJ-NPR-Z0-9]{17}$");

    public Vin {
        DomainAssert.notBlank(
                value, "car.vin.blank", "VIN cannot be null or blank"
        );

        value = value.trim().toUpperCase(Locale.ROOT);
        DomainAssert.isConditionTrue(
                VALID_VIN_PATTERN.matcher(value).matches(),
                "car.vin.invalid", "Invalid VIN format: '" + value + "'"
        );
    }
}



