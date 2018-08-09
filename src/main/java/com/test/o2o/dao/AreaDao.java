package com.test.o2o.dao;

import com.test.o2o.entity.Area;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AreaDao {
    /*
    *列出区域列表
    * @return areaList
    */
    List<Area> queryArea();
}
