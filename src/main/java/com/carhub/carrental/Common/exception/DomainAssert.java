package com.carhub.carrental.Common.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class DomainAssert {

    public void isConditionTrue(boolean condition, String errorCode, String message) {
        if (!condition) {
            throw new DomainException(errorCode, message);
        }
    }

    public void notNull(Object value, String errorCode, String message) {
        isConditionTrue(value != null, errorCode, message);
    }

    public void notBlank(String value, String errorCode, String message) {
        isConditionTrue(value != null && !value.isBlank(), errorCode, message);
    }
}