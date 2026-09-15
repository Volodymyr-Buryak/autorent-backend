package com.carhub.carrental.Common.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import com.carhub.carrental.Common.exception.DomainException;

public record Money(BigDecimal amount) {

    private static final int SCALE = 2;

    public Money {
        if (amount == null) {
            throw new DomainException("car.money.null", "Amount cannot be null");
        }
        if (amount.signum() < 0) {
            throw new DomainException("car.money.negative", "Amount cannot be negative: " + amount);
        }
        amount = amount.setScale(SCALE, RoundingMode.HALF_UP);
    }

    public static Money zero() {
        return new Money(BigDecimal.ZERO);
    }

    public static Money of(double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public Money add(Money other) {
        return new Money(this.amount.add(other.amount));
    }

    public Money subtract(Money other) {
        return new Money(this.amount.subtract(other.amount));
    }

    public Money multiply(int quantity) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(quantity)));
    }
}


