package com.test.o2o.enums;

import com.test.o2o.entity.ProductCategory;

public enum ProductCategoryStateEnum {
    SUCCESS(1,"操作成功"),
    INNER_ERROR(-1001,"内部系统错误"),
    EMPTY_LIST(-1002,"添加数小于1");


    private int state;
    private String stateInfo;

    private ProductCategoryStateEnum(int state,String stateInfo){
        this.state=state;
        this.stateInfo=stateInfo;
    }


    /*
 依据传入的state返回相应的enum值
  */
    public static ProductCategoryStateEnum stateOf(int state){
        for(ProductCategoryStateEnum stateEnum:values()){
            if(stateEnum.getState()==state){
                return  stateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
