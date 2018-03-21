package com.clom.my.service;

import com.clom.my.model.MyUser;

import java.util.List;

/**
 * Created by clovermmmmmmmmmmmmm on 2018/3/12 0012.
 */
public interface MyUserService {
    int addUser(MyUser user) throws Exception;

    List<MyUser> findAllUser(int pageNum, int pageSize)  throws Exception;
}
