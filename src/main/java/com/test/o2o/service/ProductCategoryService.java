package com.test.o2o.service;

import com.test.o2o.dto.ProductCategoryExecution;
import com.test.o2o.entity.ProductCategory;
import com.test.o2o.enums.ProductCategoryStateEnum;
import com.test.o2o.exceptions.ProductCategoryOperationException;

import java.util.List;

public interface ProductCategoryService {
    /**
     * 查询指定的某个店铺下的所有商品类别信息
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryList(long shopId);

    /**
     * 批量添加商品类别
     * @param productCategoryList
     * @return
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
        throws ProductCategoryOperationException;

    /**
     * 批量删除商品类别
     * @param productCategoryList
     * @return
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution batchDeleteProductCategory(List<ProductCategory> productCategoryList)
        throws ProductCategoryOperationException;
}
