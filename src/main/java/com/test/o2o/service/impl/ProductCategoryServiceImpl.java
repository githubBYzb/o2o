package com.test.o2o.service.impl;

import com.test.o2o.dao.ProductCategoryDao;
import com.test.o2o.dao.ProductDao;
import com.test.o2o.dto.ProductCategoryExecution;
import com.test.o2o.entity.Product;
import com.test.o2o.entity.ProductCategory;
import com.test.o2o.enums.ProductCategoryStateEnum;
import com.test.o2o.exceptions.ProductCategoryOperationException;
import com.test.o2o.exceptions.ProductOperationException;
import com.test.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{
    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Autowired
    private ProductDao productDao;

    @Override
    public List<ProductCategory> getProductCategoryList(long shopId){
        return productCategoryDao.queryProductCategoryList(shopId);
    }

    @Override
    @Transactional
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException{
        if(productCategoryList!=null && productCategoryList.size()>0){
            try{
                int effectedNum=productCategoryDao.batchInsertProductCategory(productCategoryList);
                if (effectedNum<=0){
                    throw new ProductCategoryOperationException("商品类别创建失败");
                }else{
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }
            }catch (Exception e){
                throw new ProductCategoryOperationException("batchAddProductCategory error"+e.getMessage());
            }
        }else{
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }

    @Override
    @Transactional
    public ProductCategoryExecution batchDeleteProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        if(productCategoryList!=null&&productCategoryList.size()>=0){
            try {
                for (ProductCategory pc : productCategoryList) {
                    int effectedNum=productDao.updateProductCategoryToNull(pc.getProductCategoryId());
                    if(effectedNum<0){
                        throw new ProductOperationException("在删除商品类别时，解除商品与该类别的联系时发生错误");
                    }
                }
            }catch (Exception e){
                throw  new ProductCategoryOperationException("batchDeleteCategory error:"+e.toString());
            }
            try{
                int effectnum=productCategoryDao.batchDeleteProductCategory(productCategoryList);
                if(effectnum<=0){
                    throw new ProductCategoryOperationException("商品类别删除失败");
                }else{
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }
            }catch(Exception e){
                throw new ProductCategoryOperationException(" batchDeleteProductCategory error"+e.getMessage());
            }
        }else{
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }
}
