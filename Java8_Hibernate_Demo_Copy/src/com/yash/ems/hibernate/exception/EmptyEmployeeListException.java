package com.yash.ems.hibernate.exception;

public class EmptyEmployeeListException extends RuntimeException{

    public EmptyEmployeeListException(String message)
    {
        super(message);
    }
}
