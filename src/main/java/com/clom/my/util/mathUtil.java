package com.clom.my.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.clom.my.base.APIConstants;
import com.clom.my.util.result.Result;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by clovermmmmmmmmmmmmm on 2018/1/11 0011.
 */
public class mathUtil {

    private static final String souce_Code = "UTF-8";
    private static final String new_Code = "UTF-8";

    public static boolean checkDouble (String sContentValue){
        Boolean bCheckResult = Boolean.TRUE;
        try
        {
            Double dCheckValue = Double.parseDouble(sContentValue);
            if (dCheckValue instanceof Double == false)
            {
                bCheckResult = false;
            }
        }
        catch(NumberFormatException e)
        {
            bCheckResult = false;
        }
        return bCheckResult;
    }
    public static Map<String,Object> operationResult(boolean result, String msg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", result);
        map.put("msg", msg);
        return map;
    }
    public static String operationResultStr(boolean result, String ... msg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", result);
        map.put("msg", msg);
        return map.toString();
    }
    public static void getJson(Map<String,Object> content, HttpServletResponse res){
        try{
            res.setHeader("Content-type", "text/html;charset=UTF-8");
            String json = JSON.toJSONString(content,SerializerFeature.WriteNullListAsEmpty,SerializerFeature.WriteNullStringAsEmpty,
                    SerializerFeature.WriteNullBooleanAsFalse);
            json = json.replaceAll("\r\n","\\r\\n");
            res.setContentLength(json.getBytes(new_Code).length);
            ServletOutputStream out = res.getOutputStream();
            int off = 0 ;
            short len = 8019;
            byte[] bt = json.getBytes(new_Code);
            res.setBufferSize(bt.length);
            while(off < bt.length){
                boolean wsize = false;
                int wsize1 ;
                if(off + len > bt.length){
                    wsize1 = bt.length - off;
                }else{
                    wsize1 = len;
                }
                out.write(bt,off,wsize1);
                off += wsize1;
                out.flush();
            }
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Map<String,String> getParamters(HttpServletRequest request){
        Map<?,?> paramterMap = request.getParameterMap();
        Map<String ,String> paraMap = new ConcurrentHashMap<String, String>();
        for (Map.Entry entry :
                paramterMap.entrySet()) {
            if (Array.getLength(entry.getValue())<1|| StringUtils.isEmpty(((String)(Array.get(entry.getValue(),0))))){
                paraMap.put((String)entry.getKey(),null);
            }else {
                if (request.getMethod().equalsIgnoreCase("get")){
                    try {
                        String value = new String(((String)(Array.get(entry.getValue(),0))).getBytes(souce_Code),new_Code);
                        paraMap.put((String)entry.getKey(),value);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }else {
                    paraMap.put((String)entry.getKey(),(String)(Array.get(entry.getValue(),0)));
                }
            }
        }
        return paraMap;
    }

    /**
     * 从request中获得参数Map，并返回可读的Map
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

    public static synchronized Map<String,Object> getContent(HttpServletRequest req) throws Exception{
        try{
            if(req == null){
                throw new NullPointerException("req is nulll...");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream(), new_Code));
            int size = req.getContentLength();
            if(size <= 0){
                throw new NullPointerException("content is null,,");
            }
            char[] b = new char[8196];
            int readLen = reader.read(b);
            int readMaxLen = readLen;
            StringBuilder readStr = new StringBuilder();
            readStr.append(new String(b, 0, readLen));

            while(readMaxLen < size) {
                b = new char[8196];
                readLen = reader.read(b);
                if(readLen <= -1) {
                    break;
                }

                readMaxLen += readLen;
                readStr.append(new String(b, 0, readLen));
            }
            //e.close();
            String readStr1 = new String(readStr);
            Map contentVal = JSONObject.parseObject(readStr1);
            return contentVal;
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 将一个对象转换为另一个对象
     * @param <T1> 要转换的对象
     * @param <T2> 转换后的类
     * @param orimodel 要转换的对象
     * @param castClass 转换后的类
     * @return 转换后的对象
     */
    public static  <T1,T2> T2 convertBean(T1 orimodel, Class<T2> castClass) {
        T2 returnModel = null;
        try {
            returnModel = castClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("创建"+castClass.getName()+"对象失败");
        }
        List<Field> fieldlist = new ArrayList<Field>(); //要转换的字段集合
        while (castClass != null && //循环获取要转换的字段,包括父类的字段
                !castClass.getName().toLowerCase().equals("java.lang.object")) {
            fieldlist.addAll(Arrays.asList(castClass.getDeclaredFields()));
            castClass = (Class<T2>) castClass.getSuperclass(); //得到父类,然后赋给自己
        }
        for (Field field : fieldlist) {
            PropertyDescriptor getpd = null;
            PropertyDescriptor setpd = null;
            try {
                getpd= new PropertyDescriptor(field.getName(), orimodel.getClass());
                setpd=new PropertyDescriptor(field.getName(), returnModel.getClass());
            } catch (Exception e) {
                continue;
            }
            try {
                Method getMethod = getpd.getReadMethod();
                Object transValue = getMethod.invoke(orimodel);
                Method setMethod = setpd.getWriteMethod();
                setMethod.invoke(returnModel, transValue);
            } catch (Exception e) {
                throw  new RuntimeException("cast "+orimodel.getClass().getName()+"to "
                        +castClass.getName()+" failed");
            }
        }
        return returnModel;
    }

    public static Map<String, Object>  buildPageDBParamter(String pageNo,String pageSize) {
        Integer pageNum =10;
        if(StringUtils.isNotBlank(pageSize)){
            pageNum = Integer.valueOf(pageSize);
        }
        Map<String, Object> param = new HashMap<String,Object>();
        if (StringUtils.isBlank(pageNo)) {
            pageNo = "1";
        }
        Integer startIndex = (Integer.valueOf(pageNo) -1) * pageNum;
        param.put("pageNo", pageNo);
        param.put("pageNum", pageNum);
        param.put("startIndex", startIndex);
        return param;

    }

    public static Map<String, Object>  buildPageDBParamter(HttpServletRequest req) {
        Integer pageNum = 10;
        Map<String, Object> param = new HashMap<String,Object>();
        String pageNo = (String)req.getParameter("pageNo");
        if (StringUtils.isBlank(pageNo)) {
            pageNo = "1";
        }

        Integer startIndex = (Integer.valueOf(pageNo) -1) * pageNum;
        param.put("pageNo", pageNo);
        param.put("pageNum", pageNum);
        param.put("startIndex", startIndex);
        return param;

    }
    /*
     * 判断数组中是否有重复的值
     */
    public static boolean cheakIsRepeat(int[] array) {
        HashSet<Integer> hashSet = new HashSet<Integer>();
        for (int i = 0; i < array.length; i++) {
            hashSet.add(array[i]);
        }
        if (hashSet.size() == array.length) {
            return true;
        } else {
            return false;
        }
    }
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(APIConstants.API_RETURM_CODE_SUCCESS);
        result.setMsg("成功");
        result.setData(object);
        return  result;
    }
    public static Result successNoData(){
        return  success(null);
    }
    public static Result error(String code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return  result;
    }
}
