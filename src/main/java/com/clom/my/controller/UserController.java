package com.clom.my.controller;

import com.clom.my.base.APIConstants;
import com.clom.my.model.MyUser;
import com.clom.my.service.MyUserService;
import com.clom.my.util.mathUtil;
import com.clom.my.util.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by clovermmmmmmmmmmmmm on 2018/3/12 0012.
 */
@RestController
@RequestMapping(value = "/mobile/user")
public class UserController {
    @Autowired
    private MyUserService userService;

    @PostMapping(value = "/add")
    public Result<MyUser> addUser(@Valid MyUser user, BindingResult result) throws Exception{
        try {
            if(result.hasErrors()){
                StringBuffer msgBuffer = new StringBuffer();
                List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                    if(msgBuffer != null && msgBuffer.length() > 0){
                        msgBuffer.append("|");
                    }
                    msgBuffer.append(error.getDefaultMessage());
                }
                return mathUtil.error(APIConstants.API_RETURN_CODE_VALIDATOR,msgBuffer.toString());
            }
            return mathUtil.success(userService.addUser(user));
        }catch (Exception e){
            return mathUtil.error(APIConstants.API_RETURN_CODE_SERVER_ERROR,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) throws Exception{

        return userService.findAllUser(pageNum,pageSize);
    }
}
