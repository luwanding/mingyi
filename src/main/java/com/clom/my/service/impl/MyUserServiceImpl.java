package com.clom.my.service.impl;

import com.clom.my.annotation.ReadDataSource;
import com.clom.my.dao.MyUserMapper;
import com.clom.my.model.MyUser;
import com.clom.my.service.MyUserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by clovermmmmmmmmmmmmm on 2018/3/12 0012.
 */
@Service(value = "userService")
public class MyUserServiceImpl implements MyUserService {

    @Autowired
    private MyUserMapper userMapper;//这里会报错，但是并不会影响


    @ReadDataSource
    @Transactional(rollbackFor = Exception.class )
    @Override
    public int addUser(MyUser user)  throws Exception {
        userMapper.insertSelective(user);
        if(!"1".equals(2)){
            throw new Exception("异常回滚测试");
        }
        return userMapper.insertSelective(user);
    }

    /*
    * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
    * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
    * pageNum 开始页数
    * pageSize 每页显示的数据条数
    * */

    @ReadDataSource
    @Override
    public List<MyUser> findAllUser(int pageNum, int pageSize) throws Exception {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.selectAllUser();
    }
}
