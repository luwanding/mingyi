package com.clom.my.util;

import java.io.*;
import java.util.Properties;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ImageUtil {
	
	/**
	 * @Descriptionmap 对字节数组字符串进行Base64解码并生成图片
	 * @author jx_xudelin
	 * @Date 2015-01-26
	 * @param base64 图片Base64数据
	 * @param path 图片路径
	 * @return
	 */
	public static boolean base64ToImage(String base64, String path) {// 对字节数组字符串进行Base64解码并生成图片
	    if (base64 == null){ // 图像数据为空
	        return false;
	    }
	    BASE64Decoder decoder = new BASE64Decoder();
	    try {
	        // Base64解码
	        byte[] bytes = decoder.decodeBuffer(base64);
	        for (int i = 0; i < bytes.length; ++i) {
	            if (bytes[i] < 0) {// 调整异常数据
	                bytes[i] += 256;
	            }
	        }
	        // 生成jpeg图片
	        OutputStream out = new FileOutputStream(path);
	        out.write(bytes);
	        out.flush();
	        out.close();
	        return true;
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return false;
	    }
	}


	/**
	 * @Descriptionmap 对字节数组字符串进行Base64解码
	 * @author jx_xudelin
	 * @Date 2015-01-26
	 * @param base64 图片Base64数据
	 * @return
	 */
	public static byte[] base64ToImage(String base64) {// 对字节数组字符串进行Base64解码
		if (base64 == null){ // 图像数据为空
			return null;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] bytes = decoder.decodeBuffer(base64);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			return bytes;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static byte[] decoderBase64File(String base64Code)
			throws Exception {
		byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
		return buffer;
	}
	/**
	 * @Descriptionmap 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	 * @author jx_xudelin
	 * @Date 2015-01-26
	 * @param path 图片路径
	 * @return
	 */
	public static String imageToBase64(String path) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		if(path !=null && path.length() > 0){
		    byte[] data = null;
		    // 读取图片字节数组
		    try {
		        InputStream in = new FileInputStream(path);
		        data = new byte[in.available()];
		        in.read(data);
		        in.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    // 对字节数组Base64编码
		    BASE64Encoder encoder = new BASE64Encoder();
		    String[] src = encoder.encode(data).split("\r\n");// 返回Base64编码过的字节数组字符串
		    if(src.length == 1){//linux 系统换行符
		    	src = encoder.encode(data).split("\n");
		    }
		    StringBuilder str = new StringBuilder();
		    for(int i = 0; i < src.length; i ++){
		    	str.append(src[i]);
		    }
		    return str.toString();
		}else{
			return "";
		}
	}
	/**
	 * @Descriptionmap 将InputStream 转成 byte数组
	 * @author luwanding
	 * @Date 2018-2-28
	 * @param is 流
	 * @return byte[]
	 */
	public static byte[] getStreamBytes(InputStream is) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = is.read(buffer)) != -1) {
			baos.write(buffer, 0, len);
		}
		byte[] b = baos.toByteArray();
		is.close();
		baos.close();
		return b;
	}
	/**
	 * @Descriptionmap 对字节数组字符串进行Base64解码
	 * @author jx_xudelin
	 * @Date 2015-01-26
	 * @param base64 图片Base64数据
	 * @return
	 */
	public static String InputToBase64(InputStream in) throws Exception{// 对字节数组字符串进行Base64解码
		byte[] b = ImageUtil.getStreamBytes(in);
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(b);
	}
}
