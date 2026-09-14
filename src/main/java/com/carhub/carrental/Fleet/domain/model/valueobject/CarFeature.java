package com.carhub.carrental.Fleet.domain.model.valueobject;

import com.carhub.carrental.Common.exception.DomainAssert;
import com.carhub.carrental.Fleet.domain.model.enums.CarFeatureType;

public record CarFeature(CarFeatureType type, String customName) {

    public CarFeature {
        DomainAssert.notNull(type, "car.feature.typeNull", "Car feature type cannot be null");

        if (type == CarFeatureType.CUSTOM) {
            DomainAssert.notBlank(
                    customName,
                    "car.feature.customNameBlank",
                    "Custom feature name cannot be null or blank when type is CUSTOM"
            );
            customName = customName.trim();
        } else {
            DomainAssert.isConditionTrue(
                    customName == null,
                    "car.feature.customNameNotAllowed",
                    "Custom name must be null for predefined feature type: " + type
            );
        }
    }

    public static CarFeature of(CarFeatureType type) {
        return new CarFeature(type, null);
    }

    public static CarFeature custom(String customName) {
        return new CarFeature(CarFeatureType.CUSTOM, customName);
    }
}