package com.xiaoye.iworks.utils;


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * 功能描述: 加解密工具类
 * @auther: yehl
 * @date: 2019/1/25 20:07
 */
public class EncryptUtil {

    public static final String MD5 = "MD5";
    public static final String HmacMD5 = "HmacMD5";
    public static final String SHA1 = "SHA1";
    public static final String HmacSHA1 = "HmacSHA1";
    public static final String SHA_256 = "SHA-256";
    public static final String DES = "DES";
    public static final String AES = "AES";

    /**编码格式；默认使用uft-8*/
    public final static String charset = "utf-8";

    /**DES*/
    public final static int keysizeDES = 0;

    /**AES*/
    public final static int keysizeAES = 128;

    /**
     * 使用MessageDigest进行单向加密（无密码）
     * @param str 被加密的文本
     * @param algorithm 加密算法名称
     * @return
     */
    private static String messageDigest(String str, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] resBytes = charset == null ? str.getBytes() : str.getBytes(charset);
            return base64(md.digest(resBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用KeyGenerator进行单向/双向加密（可设密码）
     * @param str 被加密的原文
     * @param algorithm  加密使用的算法名称
     * @param key 加密使用的秘钥
     * @return
     */
    private static String keyGenerator(String str, String algorithm, String key) {
        try {
            SecretKey sk = null;
            if (key == null) {
                KeyGenerator kg = KeyGenerator.getInstance(algorithm);
                sk = kg.generateKey();
            } else {
                byte[] keyBytes = charset == null ? key.getBytes() : key.getBytes(charset);
                sk = new SecretKeySpec(keyBytes, algorithm);
            }
            Mac mac = Mac.getInstance(algorithm);
            mac.init(sk);
            byte[] result = mac.doFinal(str.getBytes());
            return base64(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用KeyGenerator双向加密，DES/AES，注意这里转化为字符串的时候是将2进制转为16进制格式的字符串，不是直接转，因为会出错
     * @param str 加密的原文
     * @param algorithm 加密使用的算法名称
     * @param key  加密的秘钥
     * @param keysize
     * @param isEncode true 加密， false 解密
     * @return
     */
    private static String keyGenerator(String str, String algorithm, String key, int keysize, boolean isEncode) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance(algorithm);
            if (keysize == 0) {
                byte[] keyBytes = charset == null ? key.getBytes() : key.getBytes(charset);
                kg.init(new SecureRandom(keyBytes));
            } else if (key == null) {
                kg.init(keysize);
            } else {
                byte[] keyBytes = charset == null ? key.getBytes() : key.getBytes(charset);
                kg.init(keysize, new SecureRandom(keyBytes));
            }
            SecretKey sk = kg.generateKey();
            SecretKeySpec sks = new SecretKeySpec(sk.getEncoded(), algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            if (isEncode) {
                cipher.init(Cipher.ENCRYPT_MODE, sks);
                byte[] resBytes = charset == null ? str.getBytes() : str.getBytes(charset);
                return parseByte2HexStr(cipher.doFinal(resBytes));
            } else {
                cipher.init(Cipher.DECRYPT_MODE, sks);
                return new String(cipher.doFinal(parseHexStr2Byte(str)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String base64(byte[] res){
        return Base64.encode(res);
    }

    /**
     * 功能描述: 将二进制转换成16进制
     * @param:
     * @return: 
     * @auther: yehl
     * @date: 2019/1/25 20:19
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 功能描述: 将16进制转换为二进制
     * @param:
     * @return:
     * @auther: yehl
     * @date: 2019/1/25 20:19
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * md5加密算法进行加密（不可逆）
     * @param str 需要加密的原文
     * @return
     */
    public static String MD5(String str) {
        return messageDigest(str, MD5);
    }

    /**
     * md5加密算法进行加密（不可逆）
     * @param str  需要加密的原文
     * @param key  秘钥
     * @return
     */
    public static String MD5(String str, String key) {
        return keyGenerator(str, HmacMD5, key);
    }

    /**
     * 使用SHA1加密算法进行加密（不可逆）
     * @param str 需要加密的原文
     * @return
     */
    public static String SHA1(String str) {
        return messageDigest(str, SHA1);
    }

    /**
     * 使用SHA1加密算法进行加密（不可逆）
     * @param str 需要加密的原文
     * @param key 秘钥
     * @return
     */
    public static String SHA1(String str, String key) {
        return keyGenerator(str, HmacSHA1, key);
    }

    /**
     * 使用SHA-256加密算法进行加密（不可逆）
     * @param str 需要加密的原文
     * @return
     */
    public static String SHA256(String str) {
        return messageDigest(str, SHA_256);
    }


    /**
     * 使用DES加密算法进行加密（可逆）
     * @param str 需要加密的原文
     * @param key 秘钥
     * @return
     */
    public static String encodeDES(String str, String key) {
        return keyGenerator(str, DES, key, keysizeDES, true);
    }

    /**
     * 对使用DES加密算法的密文进行解密（可逆）
     * @param str 需要解密的密文
     * @param key 秘钥
     * @return
     */
    public static String decodeDES(String str, String key) {
        return keyGenerator(str, DES, key, keysizeDES, false);
    }

    /**
     * 使用AES加密算法经行加密（可逆）
     * @param str 需要加密的密文
     * @param key 秘钥
     * @return
     */
    public static String encodeAES(String str, String key) {
        return keyGenerator(str, AES, key, keysizeAES, true);
    }

    /**
     * 对使用AES加密算法的密文进行解密
     * @param str 需要解密的密文
     * @param key 秘钥
     * @return
     */
    public static String decodeAES(String str, String key) {
        return keyGenerator(str, AES, key, keysizeAES, false);
    }

    /**
     * 使用异或进行加密
     * @param encode 需要加密的密文
     * @param key 秘钥
     * @return
     */
    public static String XORencode(String encode, String key) {
        byte[] bs = encode.getBytes();
        for (int i = 0; i < bs.length; i++) {
            bs[i] = (byte) ((bs[i]) ^ key.hashCode());
        }
        return parseByte2HexStr(bs);
    }

    /**
     * 使用异或进行解密
     * @param encode 需要解密的密文
     * @param key 秘钥
     * @return
     */
    public static String XORdecode(String encode, String key) {
        byte[] bs = parseHexStr2Byte(encode);
        for (int i = 0; i < bs.length; i++) {
            bs[i] = (byte) ((bs[i]) ^ key.hashCode());
        }
        return new String(bs);
    }

    /**
     * 直接使用异或（第一调用加密，第二次调用解密）
     * @param str 密文
     * @param key 秘钥
     * @return
     */
    public static int XOR(int str, String key) {
        return str ^ key.hashCode();
    }

    /**
     * 使用Base64进行加密
     * @param str 密文
     * @return
     */
    public static String base64Encode(String str) {
        return Base64.encode(str.getBytes());
    }

    /**
     * 使用Base64进行解密
     * @param str
     * @return
     */
    public static String base64Decode(String str) {
        return new String(Base64.decode(str));
    }
}