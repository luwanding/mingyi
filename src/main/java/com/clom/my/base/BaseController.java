package com.clom.my.base;

import com.clom.my.util.mathUtil;
import com.clom.my.util.result.Result;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * Created by clovermmmmmmmmmmmmm on 2018/3/22 0022.
 */
public class BaseController {
    protected Result checkValid(BindingResult result){
        if(result.hasErrors()){
            StringBuffer msgBuffer = new StringBuffer();
            List<ObjectError> errorList = result.getAllErrors();
            if(errorList.size() > 0){
                msgBuffer.append(errorList.get(0).getDefaultMessage());
            }
            return mathUtil.error(APIConstants.API_RETURN_CODE_VALIDATOR,msgBuffer.toString());
        }else{
            return null;
        }
    }
}
