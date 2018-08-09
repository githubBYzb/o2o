package com.test.o2o.dto;

import com.test.o2o.entity.ProductCategory;
import com.test.o2o.entity.Shop;
import com.test.o2o.enums.ProductCategoryStateEnum;

import java.util.List;

public class ProductCategoryExecution {
    //结果状态
    private int state;
    //状态标识
    private String stateinfo;

    private List<ProductCategory> productCategoryList;

    private ProductCategoryExecution(){}
    //操作失败的时候使用的构造器
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum){
        this.state=stateEnum.getState();
        this.stateinfo=stateEnum.getStateInfo();
    }
    //操作成功的时候使用的构造器
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum,List<ProductCategory> productCategoryList){
        this.state=stateEnum.getState();
        this.stateinfo=stateEnum.getStateInfo();
        this.productCategoryList=productCategoryList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateinfo() {
        return stateinfo;
    }

    public void setStateinfo(String stateinfo) {
        this.stateinfo = stateinfo;
    }

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }
}
