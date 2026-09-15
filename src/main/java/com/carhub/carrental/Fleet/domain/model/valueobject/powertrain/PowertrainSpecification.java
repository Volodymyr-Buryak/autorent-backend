package com.carhub.carrental.Fleet.domain.model.valueobject.powertrain;

import lombok.*;
import com.carhub.carrental.Common.exception.DomainAssert;
import com.carhub.carrental.Fleet.domain.model.enums.DriveType;

@Getter
@EqualsAndHashCode
public abstract sealed class PowertrainSpecification
        permits ElectricPowertrain, HybridPowertrain, InternalCombustionPowertrain {

    private final int power;
    private final DriveType driveType;

    protected PowertrainSpecification(int power, DriveType driveType) {
        DomainAssert.isConditionTrue(
                power > 0,
                "car.powertrain.powerNotPositive", "Power must be positive: " + power
        );

        DomainAssert.notNull(
                driveType, "car.powertrain.driveTypeNull", "Drive type cannot be null"
        );

        this.power = power;
        this.driveType = driveType;
    }
}