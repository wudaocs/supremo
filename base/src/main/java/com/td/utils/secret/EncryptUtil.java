package com.td.utils.secret;

import java.security.MessageDigest;

/**
 * Description : 加密工具类
 * Created by YW on 2020/2/3 .
 * Email : 1809267944@qq.com
 */
public class EncryptUtil {

	public static String EncoderByMd5(String param){
		char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		try {
			byte[] strTemp = param.getBytes();
			//使用MD5创建MessageDigest对象
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char[] str = new char[j * 2];
			int k = 0;
			for (byte b : md) {
				//将没个数(int)b进行双字节加密
				str[k++] = hexDigits[b >> 2 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return "";
		}
	}
}
