package com.test.o2o.service.impl;

import com.test.o2o.dao.PersonInfoDao;
import com.test.o2o.dto.PersonInfoExecution;
import com.test.o2o.entity.PersonInfo;
import com.test.o2o.enums.PersonInfoStateEnum;
import com.test.o2o.service.PersonInfoService;
import com.test.o2o.until.PageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonInfoServiceImpl implements PersonInfoService {

    @Autowired
    private PersonInfoDao personInfoDao;

    @Override
    public PersonInfo getPersonInfoById(Long userId) {
        return personInfoDao.queryPersonInfoById(userId);
    }

    @Override
    public PersonInfoExecution getPersonInfoList(
            PersonInfo personInfoCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<PersonInfo> personInfoList = personInfoDao.queryPersonInfoList(
                personInfoCondition, rowIndex, pageSize);
        int count = personInfoDao.queryPersonInfoCount(personInfoCondition);
        PersonInfoExecution se = new PersonInfoExecution();
        if (personInfoList != null) {
            se.setPersonInfoList(personInfoList);
            se.setCount(count);
        } else {
            se.setState(PersonInfoStateEnum.INNER_ERROR.getState());
        }
        return se;
    }

    @Override
    public Integer changeName(String oldname,String newname) {
        return personInfoDao.updateName(oldname,newname);
    }

    @Override
    public Integer changeGender(String oldgender, String newgender, String name) {
        return personInfoDao.updateGender(oldgender,newgender,name);
    }

    @Override
    public Integer changeEmail(String oldemail, String newemail, String name) {
        return personInfoDao.updateEmail(oldemail,newemail,name);
    }

}
