package com.carhub.carrental.Fleet.domain.model;

import lombok.*;
import java.time.Year;
import java.util.List;
import java.util.UUID;
import com.carhub.carrental.Fleet.domain.model.enums.*;
import com.carhub.carrental.Common.exception.DomainAssert;
import com.carhub.carrental.Fleet.domain.model.valueobject.*;
import com.carhub.carrental.Fleet.domain.model.valueobject.powertrain.PowertrainSpecification;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CarModel {
    @EqualsAndHashCode.Include
    private UUID id;

    private Year year;
    private String brand;
    private String model;
    private int seatCount;
    private CarClass carClass;
    private List<Photo> photos;
    private String description;
    private CarBodyType bodyType;
    private List<CarFeature> features;
    private PowertrainSpecification powertrain;
    private TransmissionType transmissionType;

    @Builder
    public CarModel(
            UUID id, String brand, String model, Year year, int seatCount, CarClass carClass,
            CarBodyType bodyType, PowertrainSpecification powertrain, TransmissionType transmissionType,
            List<Photo> photos, List<CarFeature> features, String description
    ) {
        DomainAssert.notBlank(brand, "carModel.brandBlank", "Brand cannot be blank");
        DomainAssert.notBlank(model, "carModel.modelBlank", "Model cannot be blank");
        DomainAssert.notNull(year, "carModel.yearNull", "Year cannot be null");

        DomainAssert.isConditionTrue(
                seatCount > 0,
                "carModel.seatCountNotPositive", "Seat count must be positive: " + seatCount
        );

        DomainAssert.notNull(carClass, "carModel.carClassNull", "Car class cannot be null");
        DomainAssert.notNull(bodyType, "carModel.bodyTypeNull", "Body type cannot be null");
        DomainAssert.notNull(powertrain, "carModel.powertrainNull", "Powertrain cannot be null");
        DomainAssert.notNull(transmissionType, "carModel.transmissionTypeNull", "Transmission type cannot be null");

        this.year = year;
        this.brand = brand;
        this.model = model;
        this.carClass = carClass;
        this.bodyType = bodyType;
        this.seatCount = seatCount;
        this.powertrain = powertrain;
        this.description = description;
        this.transmissionType = transmissionType;
        this.id = id != null ? id : UUID.randomUUID();
        this.photos = photos != null ? List.copyOf(photos) : List.of();
        this.features = features != null ? List.copyOf(features) : List.of();
    }

}