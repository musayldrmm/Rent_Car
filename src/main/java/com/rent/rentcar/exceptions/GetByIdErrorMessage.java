package com.rent.rentcar.exceptions;

public class GetByIdErrorMessage extends NoSuchFieldException {
    public GetByIdErrorMessage() {
    }

    public GetByIdErrorMessage(String s) {
        super(s);
    }
}
