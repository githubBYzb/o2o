package com.test.o2o.dao;

import com.test.o2o.entity.LocalAuth;
import org.apache.ibatis.annotations.Param;


public interface LocalAuthDao {
    LocalAuth queryLocalAuthPwdByUserName(@Param("userName") String userName);

    Integer updataLocalAuthId(@Param("oldcount")String oldcount,@Param("newcount")String newcount);

    Integer updateUsername(@Param("oldname")String oldname,@Param("newname")String newname);

    Integer updatepwd(@Param("oldpwd")String oldpwd, @Param("newpwd")String newpwd, @Param("count")String count);

}
