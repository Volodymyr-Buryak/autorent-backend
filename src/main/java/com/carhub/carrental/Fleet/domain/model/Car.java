package com.carhub.carrental.Fleet.domain.model;

import lombok.*;
import java.util.UUID;
import com.carhub.carrental.Common.domain.Money;
import com.carhub.carrental.Common.exception.DomainAssert;
import com.carhub.carrental.Fleet.domain.model.valueobject.Vin;
import com.carhub.carrental.Fleet.domain.model.enums.CarStatus;
import com.carhub.carrental.Fleet.domain.model.valueobject.Mileage;
import com.carhub.carrental.Fleet.domain.model.valueobject.LicensePlate;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Car {
    @EqualsAndHashCode.Include
    private UUID id;

    private Vin vin;
    private UUID modelId;
    private Long locationId;
    private Mileage mileage;
    private CarStatus status;
    private Money dailyRentalPrice;
    private LicensePlate licensePlate;

    @Builder
    public Car(
            UUID id, Vin vin, UUID modelId, Long locationId, Mileage mileage,
            CarStatus status, Money dailyRentalPrice, LicensePlate licensePlate
    ) {
        DomainAssert.notNull(vin, "car.vinNull", "Vin cannot be null");
        DomainAssert.notNull(modelId, "car.modelIdNull", "Model id cannot be null");
        DomainAssert.notNull(locationId, "car.locationIdNull", "Location id cannot be null");
        DomainAssert.notNull(mileage, "car.mileageNull", "Mileage cannot be null");
        DomainAssert.notNull(status, "car.statusNull", "Status cannot be null");
        DomainAssert.notNull(dailyRentalPrice, "car.dailyRentalPriceNull", "Daily rental price cannot be null");
        DomainAssert.notNull(licensePlate, "car.licensePlateNull", "License plate cannot be null");

        this.id = id != null ? id : UUID.randomUUID();
        this.vin = vin;
        this.modelId = modelId;
        this.locationId = locationId;
        this.mileage = mileage;
        this.status = status;
        this.dailyRentalPrice = dailyRentalPrice;
        this.licensePlate = licensePlate;
    }

}