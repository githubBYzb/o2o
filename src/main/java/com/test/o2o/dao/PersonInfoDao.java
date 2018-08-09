package com.test.o2o.dao;

import com.test.o2o.entity.PersonInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PersonInfoDao {
    List<PersonInfo> queryPersonInfoList(
            @Param("personInfoCondition") PersonInfo personInfoCondition,
            @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    PersonInfo queryPersonInfoById(long userId);

    int queryPersonInfoCount(
            @Param("personInfoCondition") PersonInfo personInfoCondition);

    Integer updateName(@Param("oldname")String oldname,@Param("newname")String newname);

    Integer updateGender(@Param("oldgender")String oldgender, @Param("newgender")String newgender, @Param("name")String name);

    Integer updateEmail(@Param("oldemail")String oldemail, @Param("newemail")String newemail, @Param("name")String name);

}
