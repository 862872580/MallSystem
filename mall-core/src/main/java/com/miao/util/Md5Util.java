package com.miao.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 采用md5加密 确保数据安全性
 */
public class Md5Util {
  public static String getMD5(String msg,String key){
      return DigestUtils.md5Hex(msg + key);
  }

  /**
   * 测试
   * @param args
   */
  public static void main(String[] args) {
    String str= getMD5("123456","12");
    System.out.println(str);
  }
}
