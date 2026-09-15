package com.carhub.carrental.Common.exception;

import lombok.*;

@Getter
public class DomainException extends RuntimeException {
    private final String messageKey;

    public DomainException(String messageKey, String technicalMessage) {
        super(technicalMessage);
        this.messageKey = messageKey;
    }

    public DomainException(String messageKey) {
        this(messageKey, messageKey);
    }
}
