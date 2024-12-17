package com.yash.ems.hibernate.exception;

public class IdNotValidException  extends RuntimeException{

    public IdNotValidException(String message)
    {
        super(message);
    }
}
