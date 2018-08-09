package com.test.o2o.service.impl;

import com.test.o2o.dao.LocalAuthDao;
import com.test.o2o.entity.LocalAuth;
import com.test.o2o.service.LocalAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalAuthServiceImpl implements LocalAuthService {

    @Autowired
    private LocalAuthDao localAuthDao;


    @Override
    public LocalAuth getLocalAuthPwdByUserName(String userName) {
        return localAuthDao.queryLocalAuthPwdByUserName(userName);
    }

    @Override
    public Integer changeLocalAuthId(String oldcount,String newcount) {
        return localAuthDao.updataLocalAuthId(oldcount,newcount);
    }

    @Override
    public Integer changeUsername(String oldname,String newname) {
        return localAuthDao.updateUsername(oldname,newname);
    }

    @Override
    public Integer changePassword(String oldpwd, String newpwd, String count) {
        return localAuthDao.updatepwd(oldpwd,newpwd,count);
    }


}
