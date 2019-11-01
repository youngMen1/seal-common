package com.seal.util.encryptionutil;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Map;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/10/11 11:25
 * @description HMACSHA256加密
 * 散列算法
 **/
@Slf4j
public class HmacSha256 {

    /**
     * 算法
     */
    private static final String KEY_ALGORITHM = "HmacSHA256";

    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static void main(String[] args) {
        try {
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
     * sha256_HMAC加密
     *
     * @param message 消息
     * @param secret  秘钥
     * @return 加密后字符串
     */
    public static String sha256_HMAC(String message, String secret) {
        String hash = "";
        try {
            Mac sha256_HMAC = Mac.getInstance(KEY_ALGORITHM);
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), KEY_ALGORITHM);
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(message.getBytes());
            hash = byteArrayToHexString(bytes);
        } catch (Exception e) {
            log.info("Error HmacSHA256 ===========" + e.getMessage());
        }
        return hash;
    }

    /**
     * 生成sign HMAC-SHA256 或 MD5 签名
     *
     * @param map         map
     * @param paternerKey paternerKey
     * @return sign
     */
    public static String generateSign(Map<String, String> map, String paternerKey) {
        return generateSign(map, null, paternerKey);
    }

    /**
     * 生成sign HMAC-SHA256 或 MD5 签名
     *
     * @param map         map
     * @param sign_type   HMAC-SHA256 或 MD5
     * @param paternerKey paternerKey
     * @return sign
     */
    public static String generateSign(Map<String, String> map, String sign_type, String paternerKey) {
        Map<String, String> tmap = AbstractMapUtil.order(map);
        if (tmap.containsKey("sign")) {
            tmap.remove("sign");
        }
        String str = AbstractMapUtil.mapJoin(tmap, false, false);
        if (sign_type == null) {
            sign_type = tmap.get("sign_type");
        }
        if ("HMAC-SHA256".equalsIgnoreCase(sign_type)) {
            try {
                Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
                SecretKeySpec secret_key = new SecretKeySpec(paternerKey.getBytes("UTF-8"), "HmacSHA256");
                sha256_HMAC.init(secret_key);
                return Hex.encodeHexString(sha256_HMAC.doFinal((str + "&key=" + paternerKey).getBytes("UTF-8"))).toUpperCase();
            } catch (Exception e) {
                log.error("", e);
            }
            return null;
        } else {
            // default MD5
            return DigestUtils.md5Hex(str + "&key=" + paternerKey).toUpperCase();
        }
    }

    /**
     * 将加密后的字节数组转换成字符串
     *
     * @param b 字节数组
     * @return 字符串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) {
                hs.append('0');
            }
            hs.append(stmp);
        }
        return hs.toString().toLowerCase();
    }

    /**
     * hash
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


}
