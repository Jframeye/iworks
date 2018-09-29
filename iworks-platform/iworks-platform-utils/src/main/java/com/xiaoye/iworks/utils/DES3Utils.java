package com.xiaoye.iworks.utils;

import java.security.Key;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 加解密工具类
 * @author yehl
 * @date 2018年6月23日
 */
public class DES3Utils {

    /**
     * 加密参数
     * @param param 待加密参数
     * @return
     */
    public static String des3EncodeStr(String param){
        return des3EncodeStr(param, 1);
    }

    /**
     * 加密参数
     * @param param 待加密参数
     * @param times 加密次数
     * @return
     */
    public static String des3EncodeStr(String param, int times) {
        try {
            if (null == param || "".equals(param.trim())) {
            	throw new RuntimeException("待加密参数不能为空");
            }
            return des3EncodeCBC(param, CHARSET_UTF8, times);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解密参数
     * @param param 待解密参数
     * @return
     */
    public static String des3DecodeStr(String param) {
        return des3DecodeStr(param, 1);
    }
    
    /**
     * 解密参数
     * @param param 待解密参数
     * @param times 解密次数
     * @return
     */
    public static String des3DecodeStr(String param, int times) {
        try {
            if (null == param || "".equals(param.trim())) {
            	throw new RuntimeException("待解密参数不能为空");
            }
            return des3DecodeCBC(param, CHARSET_UTF8, times);
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
    }
    
    public static final String CHARSET_UTF8 = "utf-8";
	
	public static final String CHARSET_GBK = "gbk";
	
	// 算法名称
	private static final String KEY_ALGORITHM = "DESEDE";

	// 算法名称/加密模式/填充方式
	private static final String CIPHER_ALGORITHM = "desede/CBC/PKCS5Padding";

	// 加密KEY
	private static final String KEY = "3X41955KP0S00O8938AS9L9YKIU2YT1MNS0S219BGU0R12C0";

	// 加密偏移量
	private static final byte[] KEY_IV = { 0, 1, 2, 3, 4, 5, 6, 7 };

	/**
	 * CBC加密
	 * @param data
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public static String des3EncodeCBC(String data, String charset) throws Exception {
		Key deskey = keyGenerator(new String(KEY.getBytes()));
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		IvParameterSpec ips = new IvParameterSpec(KEY_IV);
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] bOut = cipher.doFinal(data.getBytes(charset));
		return new String(Base64Utils.encode(bOut), charset);
	}
	
	/**
	 * CBC加密
	 * @param data
	 * @param charset
	 * @param times
	 * @return
	 * @throws Exception
	 */
	public static String des3EncodeCBC(String data, String charset, int times) throws Exception {
		if (times < 2){
			return des3EncodeCBC(data, charset);
		}
		String v_cbc = data;
		for (int i = 0; i < times; i++) {
			v_cbc = des3EncodeCBC(v_cbc, charset);
		}
		return v_cbc;
	}
	
	/**
	 * CBC解密
	 * @param data
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public static String des3DecodeCBC(String data, String charset) throws Exception {
		Key deskey = keyGenerator(new String(KEY.getBytes()));
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		IvParameterSpec ips = new IvParameterSpec(KEY_IV);
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
		byte[] bOut = cipher.doFinal(Base64Utils.decode(data));
		return new String(bOut, charset);
	}

	/**
	 * CBC解密
	 * @param data
	 * @param charset
	 * @param times
	 * @return
	 * @throws Exception
	 */
	public static String des3DecodeCBC(String data, String charset, int times) throws Exception {
		if (times < 2){
			return des3DecodeCBC(data, charset);
		} 
		String v_cbc = data;
		for (int i = 0; i < times; i++) {
			v_cbc = des3DecodeCBC(v_cbc, charset);
		}
		return v_cbc;
	}
	
	/**
	 * 生成密钥key对象
	 * @param keyStr
	 * @return
	 * @throws Exception
	 */
	private static Key keyGenerator(String keyStr) throws Exception {
		byte input[] = HexString2Bytes(keyStr);
		DESedeKeySpec KeySpec = new DESedeKeySpec(input);
		SecretKeyFactory KeyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
		return ((Key) (KeyFactory.generateSecret(((java.security.spec.KeySpec) (KeySpec)))));
	}

	private static int parse(char c) {
		if (c >= 'a')
			return (c - 'a' + 10) & 0x0f;
		if (c >= 'A')
			return (c - 'A' + 10) & 0x0f;
		return (c - '0') & 0x0f;
	}

	// 从十六进制字符串到字节数组转换
	public static byte[] HexString2Bytes(String hexstr) {
		byte[] b = new byte[hexstr.length() / 2];
		int j = 0;
		for (int i = 0; i < b.length; i++) {
			char c0 = hexstr.charAt(j++);
			char c1 = hexstr.charAt(j++);
			b[i] = (byte) ((parse(c0) << 4) | parse(c1));
		}
		return b;
	}
	
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 加密盐加密
	 * @param password 密码
	 * @param passsalt 密码盐
	 * @return
	 */
    public static String encode(String password, String passsalt) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            //加密后的字符串
            result = byteArrayToHexString(md.digest(mergePasswordAndSalt(password, passsalt).getBytes("utf-8")));
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
        return result;
    }
    
    private static String mergePasswordAndSalt(String password, String passsalt) {
    	if (password == null) {
    		password = "";
    	}
    	if ((passsalt == null) || "".equals(passsalt)) {
    		return password;
    	} else {
    		return password + "{" + passsalt.toString() + "}";
    	}
    }

    /**
     * 转换字节数组为16进制字串
     * @param b 字节数组
     * @return 16进制字串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
    
    /**
     * Md5加密
     * @param str
     * @return
     */
	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return str;
	}
	
	public static void main(String[] args) {
		System.out.println(des3EncodeStr("xiaoye+123"));
	}
}