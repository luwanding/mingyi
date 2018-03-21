package com.clom.my.dao;

import com.clom.my.model.MyUser;

import java.util.List;

public interface MyUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MyUser record);

    int insertSelective(MyUser record);

    MyUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MyUser record);

    int updateByPrimaryKey(MyUser record);

    List<MyUser> selectAllUser();
}