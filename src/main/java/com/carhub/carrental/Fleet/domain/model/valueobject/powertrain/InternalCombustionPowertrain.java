package com.carhub.carrental.Fleet.domain.model.valueobject.powertrain;

import lombok.*;
import com.carhub.carrental.Fleet.domain.model.enums.*;
import com.carhub.carrental.Common.exception.DomainAssert;
import com.carhub.carrental.Fleet.domain.model.valueobject.EngineVolume;

@Getter
@EqualsAndHashCode(callSuper = true)
public final class InternalCombustionPowertrain extends PowertrainSpecification {

    private final FuelType fuelType;
    private final EngineVolume engineVolume;

    public InternalCombustionPowertrain(int power, DriveType driveType, FuelType fuelType, EngineVolume engineVolume) {
        super(power, driveType);

        DomainAssert.notNull(
                fuelType, "car.powertrain.fuelTypeNull", "Fuel type cannot be null"
        );

        DomainAssert.notNull(
                engineVolume, "car.powertrain.engineVolumeNull", "Engine volume cannot be null"
        );

        this.fuelType = fuelType;
        this.engineVolume = engineVolume;
    }
}