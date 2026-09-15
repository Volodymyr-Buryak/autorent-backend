package com.carhub.carrental.Fleet.domain.model.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import com.carhub.carrental.Common.exception.DomainAssert;

public record EngineVolume(BigDecimal liters) {

    private static final int SCALE = 1;
    private static final BigDecimal MIN_VOLUME = BigDecimal.ZERO;
    private static final BigDecimal MAX_VOLUME = BigDecimal.valueOf(10.0);

    public EngineVolume {
        DomainAssert.notNull(liters, "car.engineVolume.null", "Engine volume cannot be null");

        DomainAssert.isConditionTrue(
                liters.compareTo(MIN_VOLUME) > 0,
                "car.engineVolume.notPositive",
                "Engine volume must be positive: " + liters
        );

        DomainAssert.isConditionTrue(
                liters.compareTo(MAX_VOLUME) <= 0,
                "car.engineVolume.tooLarge",
                "Engine volume exceeds maximum allowed value: " + liters
        );

        liters = liters.setScale(SCALE, RoundingMode.HALF_UP);
    }

    public static EngineVolume of(double liters) {
        return new EngineVolume(BigDecimal.valueOf(liters));
    }

}