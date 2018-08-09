package com.test.o2o.service;

import com.test.o2o.BaseTest;
import com.test.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AreaServiceTest extends BaseTest{
    @Autowired
    private AreaService areaService;
    @Test
    public void testGetAreaList(){
        List<Area> areaList=areaService.getAreaList();
        assertEquals("南区",areaList.get(0).getAreaName());
    }
}
