package com.rent.rentcar.exceptions;

public class RemoveByIdErrorMessage extends NoSuchFieldException {
    public RemoveByIdErrorMessage() {
    }

    public RemoveByIdErrorMessage(String s) {
        super(s);
    }
}
