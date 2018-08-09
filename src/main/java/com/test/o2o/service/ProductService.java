package com.test.o2o.service;

import com.test.o2o.dto.ImageHolder;
import com.test.o2o.dto.ProductExecution;
import com.test.o2o.entity.Product;
import com.test.o2o.exceptions.ProductOperationException;

import java.io.InputStream;
import java.util.List;

public interface ProductService {
    /**
     * 添加商品信息及图片处理
     *
     * @param product
     * @param thnumbnail
     * @param productImgHolderList
     * @return
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product, ImageHolder thnumbnail,
                                List<ImageHolder> productImgHolderList)
            throws ProductOperationException;

    /**
     * 通过商品Id查询唯一的商品信息
     *
     * @param productId
     * @return
     */
    Product getProductById(long productId);

    /**
     * 修改商品信息以及图片处理
     *
     * @param product
     * @param thumbnail
     * @param productImgHolderList
     * @return
     * @throws ProductOperationException
     */
    ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
            throws ProductOperationException;

    /**
     * 查询商品列表并分页，可输入条件有：商品名（模糊），商品状态，店铺Id，商品类别
     * @param productCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ProductExecution getProductList(Product productCondition,int pageIndex,int pageSize);
}
