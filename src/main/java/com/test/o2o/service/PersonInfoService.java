package com.test.o2o.service;

import com.test.o2o.dto.PersonInfoExecution;
import com.test.o2o.entity.PersonInfo;

public interface PersonInfoService {
    PersonInfo getPersonInfoById(Long userId);

    PersonInfoExecution getPersonInfoList(PersonInfo personInfoCondition,
                                          int pageIndex, int pageSize);

    Integer changeName(String oldname,String newname);

    Integer changeGender(String oldgender,String newgender, String name);

    Integer changeEmail(String oldemail,String newemail, String name);
}
