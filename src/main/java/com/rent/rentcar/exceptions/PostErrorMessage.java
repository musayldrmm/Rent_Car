package com.rent.rentcar.exceptions;

public class PostErrorMessage extends RuntimeException {
    public PostErrorMessage() {
    }

    public PostErrorMessage(String message) {
        super(message);
    }
}
