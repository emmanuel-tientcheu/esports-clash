package com.example.esport_clash.auth.application.exception;

import com.example.esport_clash.core.domain.exceptions.BadRequestException;

public class EmailUnavailableException extends BadRequestException {
    public EmailUnavailableException() {
        super("Email is already is use");
    }
}
