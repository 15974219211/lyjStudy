package com.my.study.dao;


import com.my.study.entity.TUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TUserMapper {
    //1对多关联
     List<TUser> selectUserJobs();

     //批量in查询
    List<TUser> selectForeachIn(String[] names);

    //批量插入
    int insertForeachBatch(List<TUser> users);

    //单条插入，获取主键
    int insert2(TUser record);

    //动态sql
    List<TUser> selectChooseOper(@Param("email")String email,@Param("sex")Byte sex);
}