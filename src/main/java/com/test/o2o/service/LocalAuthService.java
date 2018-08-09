package com.test.o2o.service;

import com.test.o2o.entity.LocalAuth;

public interface LocalAuthService {
    LocalAuth getLocalAuthPwdByUserName(String userName);

    Integer changeLocalAuthId(String oldcount,String newcount);

    Integer changeUsername(String oldname,String newname);

    Integer changePassword(String oldpwd,String newpwd,String count);


}
