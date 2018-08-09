package com.test.o2o.web.shopadmin;

import com.test.o2o.dto.ProductCategoryExecution;
import com.test.o2o.dto.Result;
import com.test.o2o.entity.ProductCategory;
import com.test.o2o.entity.Shop;
import com.test.o2o.enums.ProductCategoryStateEnum;
import com.test.o2o.exceptions.ProductCategoryOperationException;
import com.test.o2o.service.ProductCategoryService;
import com.test.o2o.until.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value="/getproductcategorylist",method= RequestMethod.GET)
    @ResponseBody
    private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request){
        Shop currentShop=(Shop)request.getSession().getAttribute("currentShop");
        List<ProductCategory> list=null;
        if(currentShop!=null&&currentShop.getShopId()>0){
            list=productCategoryService.getProductCategoryList(currentShop.getShopId());
            return new Result<List<ProductCategory>>(true,list);
        }else{
            ProductCategoryStateEnum ps=ProductCategoryStateEnum.INNER_ERROR;
            return new Result<List<ProductCategory>>(false,ps.getState(),ps.getStateInfo());
        }
    }

    @RequestMapping(value="/addproductcategorys",method= POST)
    @ResponseBody
    private Map<String,Object> addProductCategorys(@RequestBody List<ProductCategory> productCategoryList,HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<>();
        Shop currentShop=(Shop)request.getSession().getAttribute("currentShop");
        for (ProductCategory pc:productCategoryList){
            pc.setShopId(currentShop.getShopId());
        }
        if(productCategoryList!=null&&productCategoryList.size()>0){
            try{
                ProductCategoryExecution pe=productCategoryService.batchAddProductCategory(productCategoryList);
                if(pe.getState()==ProductCategoryStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else{
                    modelMap.put("success",false);
                    modelMap.put("errMsg",pe.getStateinfo());
                }
            }catch (ProductCategoryOperationException e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
                return modelMap;
            }
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","请至少输入一个商品类别");
        }
        return modelMap;
    }

    @RequestMapping(value="/deleteproductcategory",method=POST)
    @ResponseBody
    private Map<String,Object> deleteProductCategory(@RequestBody List<ProductCategory> productCategoryList,HttpServletRequest request){
        Map<String,Object> modelmap=new HashMap<>();
        Shop currentShop=(Shop)request.getSession().getAttribute("currentShop");
        for(ProductCategory pc:productCategoryList){
            pc.setShopId(currentShop.getShopId());
        }
        if(productCategoryList!=null&&productCategoryList.size()>0){
            try{
                ProductCategoryExecution pe=productCategoryService.batchDeleteProductCategory(productCategoryList);
                if(pe.getState()==ProductCategoryStateEnum.SUCCESS.getState()){
                    modelmap.put("success",true);
                }else{
                    modelmap.put("success",false);
                    modelmap.put("errMsg",pe.getStateinfo());
                }
            }catch(ProductCategoryOperationException e){
                modelmap.put("success",false);
                modelmap.put("errMsg",e.toString());
                return modelmap;
            }
        }else{
            modelmap.put("success",false);
            modelmap.put("errMsg","删除发生异常，传入商品类别为空");
        }
        return modelmap;
    }
}
