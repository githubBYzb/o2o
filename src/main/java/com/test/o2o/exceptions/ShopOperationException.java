package com.test.o2o.exceptions;

public class ShopOperationException extends RuntimeException{
    private static final long serialVersionUID = -8482737250264068210L;

    public ShopOperationException(String msg){
        super(msg);
    }
}
