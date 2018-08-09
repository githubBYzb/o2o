package com.test.o2o.dao;

import com.test.o2o.BaseTest;
import com.test.o2o.entity.Product;
import com.test.o2o.entity.ProductCategory;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;


    @Test
    @Ignore
    public void testQueryByShopId() throws Exception{
        long shopId=1;
        List<ProductCategory> productCategoryList=productCategoryDao.queryProductCategoryList(shopId);
        System.out.println("该店铺自定义类别数为："+productCategoryList.size());
    }

    @Test
    @Ignore
    public void testBatchInsertProductCategory(){
        ProductCategory productCategory=new ProductCategory();
        productCategory.setProductCategoryName("奶茶1");
        productCategory.setPriority(1);
        productCategory.setCreateTime(new Date());
        productCategory.setShopId(1L);
        ProductCategory productCategory2=new ProductCategory();
        productCategory2.setProductCategoryName("奶茶2");
        productCategory2.setPriority(2);
        productCategory2.setCreateTime(new Date());
        productCategory2.setShopId(1L);
        List<ProductCategory> productCategoryList=new ArrayList<>();
        productCategoryList.add(productCategory);
        productCategoryList.add(productCategory2);
        int effectedNum=productCategoryDao.batchInsertProductCategory(productCategoryList);
        assertEquals(2,effectedNum);
    }

    @Test
    public void testBatchDeleteProductCategory() throws Exception{
        ProductCategory productCategory=new ProductCategory();
        productCategory.setProductCategoryId(2L);
        productCategory.setShopId(1L);
        ProductCategory productCategory2=new ProductCategory();
        productCategory2.setProductCategoryId(3L);
        productCategory2.setShopId(1L);
        List<ProductCategory> productCategoryList=new ArrayList<>();
        productCategoryList.add(productCategory);
        productCategoryList.add(productCategory2);
        int effectedNum=productCategoryDao.batchDeleteProductCategory(productCategoryList);
        System.out.println(effectedNum);
        assertEquals(productCategoryList.size(),effectedNum);
    }
}
