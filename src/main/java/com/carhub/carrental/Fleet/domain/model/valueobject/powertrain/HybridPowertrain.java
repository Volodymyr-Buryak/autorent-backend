package com.carhub.carrental.Fleet.domain.model.valueobject.powertrain;

import lombok.*;
import com.carhub.carrental.Fleet.domain.model.enums.*;
import com.carhub.carrental.Common.exception.DomainAssert;
import com.carhub.carrental.Fleet.domain.model.valueobject.EngineVolume;

@Getter
@EqualsAndHashCode(callSuper = true)
public final class HybridPowertrain extends PowertrainSpecification {

    private final int range;
    private final FuelType fuelType;
    private final int batteryCapacity;
    private final EngineVolume engineVolume;

    public HybridPowertrain(
            int power, DriveType driveType, int range,
            FuelType fuelType, int batteryCapacity, EngineVolume engineVolume
    ) {
        super(power, driveType);

        DomainAssert.isConditionTrue(
                range > 0,
                "car.powertrain.rangeNotPositive", "Range must be positive: " + range
        );

        DomainAssert.notNull(
                fuelType, "car.powertrain.fuelTypeNull", "Fuel type cannot be null"
        );

        DomainAssert.isConditionTrue(
                batteryCapacity > 0,
                "car.powertrain.batteryCapacityNotPositive",
                "Battery capacity must be positive: " + batteryCapacity
        );

        DomainAssert.notNull(
                engineVolume, "car.powertrain.engineVolumeNull", "Engine volume cannot be null"
        );

        this.range = range;
        this.fuelType = fuelType;
        this.batteryCapacity = batteryCapacity;
        this.engineVolume = engineVolume;
    }
}