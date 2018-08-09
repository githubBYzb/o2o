package com.test.o2o.dto;

import com.test.o2o.entity.Product;
import com.test.o2o.entity.ProductCategory;
import com.test.o2o.enums.ProductStateEnum;

import java.util.List;

public class ProductExecution {
    //结果状态
    private int state;

    //状态标识
    private String stateInfo;

    //商品数量
    private int count;

    //操作的product(增删改商品时使用)
    private Product product;

    //获取的product列表(查询商品列表时使用)
    private List<Product> productList;

    public ProductExecution(){}

    //失败时的构造器
    public ProductExecution(ProductStateEnum stateEnum){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
    }

    //成功时的构造器
    public ProductExecution(ProductStateEnum stateEnum,Product product){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
        this.product=product;
    }

    //成功时的构造器
    public ProductExecution(ProductStateEnum stateEnum,List<Product> productList){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
        this.productList=productList;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Product getProduct() {
        return product;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
