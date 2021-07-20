package com.seal.test.secure.secureutils;

import java.security.MessageDigest;

/**
 * SHA 单向加密
 * SHA是散列算法,不是加密算法,不存在解密的问题。
 * 对数据解密破解就是找到任意一个源数据，能够生成相同的目标数据。
 * SHA256基本上是不可破解的，即找不到（或概率极小）“碰撞”结果。
 * 网站的解密规则：
 * 网站从浏览器发送过来的信息当中选出一组加密算法与HASH算法，并将自己的身份信息以证书的形式发回给浏览器。
 * 证书里面包含了网站地址，加密公钥，以及证书的颁发机构等信息。
 */
public class SHACodec extends BasicCodec {

    private static final String ALGORITHM = "SHA";

    @Override
    public byte[] encrypt(byte[] data) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
        return messageDigest.digest(data);
    }

    @Override
    public byte[] decrypt(byte[] data) throws Exception {
        return null;
    }

    /**
     * 返回SHA单向加密后的十六进制字符串
     *
     * @param data
     * @return
     * @throws Exception
     */
    public String getEncryptForHex(byte[] data) throws Exception {
        byte[] digestData = encrypt(data);
        return parseByteArray2HexStr(digestData);
    }
}
