package com.carhub.carrental.Fleet.domain.model.valueobject;

import com.carhub.carrental.Common.exception.DomainAssert;

public record Mileage(long value) {

    public Mileage {
        DomainAssert.isConditionTrue(
                value >= 0, "car.mileage.negative", "Mileage cannot be negative: " + value
        );
    }

    public static Mileage zero() {
        return new Mileage(0);
    }

    public Mileage add(long mileageToAdd) {
        DomainAssert.isConditionTrue(
                mileageToAdd >= 0,
                "car.mileage.addNegative",
                "Mileage to add cannot be negative: " + mileageToAdd
        );
        return new Mileage(this.value + mileageToAdd);
    }
}
