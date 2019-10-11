package com.seal.util.encryptionutil;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/10/10 14:01
 * @description java实现HMACSHA256（md5私钥key）加密签名
 * HMAC SHA256散列算法
 **/
@Slf4j
public class Sha256Security {

    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static void main(String[] args) {
        try {
            // 秘钥
            String secret = "91b980153904d488";
            String message = "123456";

            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            System.out.println(sha256_HMAC.doFinal(message.getBytes()));

            String hash = Sha256Security.bytesToHexFun16(sha256_HMAC.doFinal(message.getBytes()));
            System.out.println(Sha256Security.bytesToHexFun1(sha256_HMAC.doFinal(message.getBytes())));
            System.out.println(Sha256Security.bytesToHexFun2(sha256_HMAC.doFinal(message.getBytes())));
            System.out.println(hash);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    /**
     * 方法一：
     * byte[] to hex string
     *
     * @param bytes
     * @return
     */
    public static String bytesToHexFun1(byte[] bytes) {
        // 一个byte为8位，可用两个十六进制位标识
        char[] buf = new char[bytes.length * 2];
        int a = 0;
        int index = 0;
        // 使用除与取余进行转换
        for (byte b : bytes) {
            if (b < 0) {
                a = 256 + b;
            } else {
                a = b;
            }
            buf[index++] = HEX_CHAR[a / 16];
            buf[index++] = HEX_CHAR[a % 16];
        }
        return new String(buf);
    }


    /**
     * 方法二：
     * byte[] to hex string
     *
     * @param bytes
     * @return
     */
    public static String bytesToHexFun2(byte[] bytes) {
        char[] buf = new char[bytes.length * 2];
        int index = 0;
        // 利用位运算进行转换，可以看作方法一的变种
        for (byte b : bytes) {
            buf[index++] = HEX_CHAR[b >>> 4 & 0xf];
            buf[index++] = HEX_CHAR[b & 0xf];
        }

        return new String(buf);
    }


    /**
     * 将字节转换成16位字符串
     *
     * @param bytes
     * @return
     */
    public static String bytesToHexFun16(byte[] bytes) {
        StringBuilder buf = new StringBuilder(bytes.length * 2);
        // 使用String的format方法进行转换
        for (byte b : bytes) {
            buf.append(String.format("%02x", new Integer(b & 0xff)));
        }

        return buf.toString();
    }

    /**
     * 将16位字符串转换位字节
     *
     * @param str
     * @return
     */
    public static byte[] Hex16toBytes(String str) {
        if (str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }
        return bytes;
    }


}