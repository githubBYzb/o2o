package com.test.o2o.exceptions;

import com.test.o2o.entity.ProductCategory;

public class ProductCategoryOperationException extends RuntimeException{
    private static final long serialVersionUID = -4903940423102134510L;

    public ProductCategoryOperationException(String msg){
        super(msg);
    }
}
