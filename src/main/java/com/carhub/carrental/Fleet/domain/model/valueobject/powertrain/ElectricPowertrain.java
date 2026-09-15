package com.carhub.carrental.Fleet.domain.model.valueobject.powertrain;

import lombok.*;
import com.carhub.carrental.Common.exception.DomainAssert;
import com.carhub.carrental.Fleet.domain.model.enums.DriveType;

@Getter
@EqualsAndHashCode(callSuper = true)
public final class ElectricPowertrain extends PowertrainSpecification {

    private final int range;
    private final int batteryCapacity;

    public ElectricPowertrain(int power, DriveType driveType, int range, int batteryCapacity) {
        super(power, driveType);

        DomainAssert.isConditionTrue(
                range > 0, "car.powertrain.rangeNotPositive", "Range must be positive: " + range
        );

        DomainAssert.isConditionTrue(
                batteryCapacity > 0,
                "car.powertrain.batteryCapacityNotPositive",
                "Battery capacity must be positive: " + batteryCapacity
        );

        this.range = range;
        this.batteryCapacity = batteryCapacity;
    }
}