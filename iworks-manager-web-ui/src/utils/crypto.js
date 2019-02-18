import CryptoJS from 'crypto-js'

export default {//加密
  encrypt(word, keyStr) {
    keyStr = keyStr ? keyStr : 'abcdefgabcdefg12';
    var key = CryptoJS.enc.Utf8.parse(keyStr);
    var srcs = CryptoJS.enc.Utf8.parse(word);
    var encrypted = CryptoJS.AES.encrypt(srcs, key, {mode: CryptoJS.mode.ECB, padding: CryptoJS.pad.Pkcs7});
    return encrypted.toString();
  },
  //解密
  decrypt(word, keyStr) {
    keyStr = keyStr ? keyStr : 'abcdefgabcdefg12';
    var key = CryptoJS.enc.Utf8.parse(keyStr);//Latin1 w8m31+Yy/Nw6thPsMpO5fg==
    var decrypt = CryptoJS.AES.decrypt(word, key, {mode: CryptoJS.mode.ECB, padding: CryptoJS.pad.Pkcs7});
    return CryptoJS.enc.Utf8.stringify(decrypt).toString();
  }

}

const key = CryptoJS.enc.Utf8.parse("1234123412ABCDEF");  //十六位十六进制数作为密钥
const iv = CryptoJS.enc.Utf8.parse('ABCDEF1234123412');   //十六位十六进制数作为密钥偏移量

/**
 * AES 加密
 * @param str
 * @returns {string}
 */
export const aes_encrypt = (str) => {
  let encrypted = ''
  if (typeof(str) == 'string') {
    let srcs = CryptoJS.enc.Utf8.parse(str);
    encrypted = CryptoJS.AES.encrypt(srcs, key, {
      iv: iv,
      mode: CryptoJS.mode.CBC,
      padding: CryptoJS.pad.Pkcs7
    });
  } else if (typeof(str) == 'object') {//对象格式的转成json字符串
    let data = JSON.stringify(str);
    let srcs = CryptoJS.enc.Utf8.parse(data);
    encrypted = CryptoJS.AES.encrypt(srcs, key, {
      iv: iv,
      mode: CryptoJS.mode.CBC,
      padding: CryptoJS.pad.Pkcs7
    })
  }
  return encrypted.ciphertext.toString();
}

/**
 * AES 解密
 * @param str
 * @returns {string}
 */
export const aes_decrypt = (str) => {
  let encryptedHexStr = CryptoJS.enc.Hex.parse(str);
  let srcs = CryptoJS.enc.Base64.stringify(encryptedHexStr);
  let decrypt = CryptoJS.AES.decrypt(srcs, key, {
    iv: iv,
    mode: CryptoJS.mode.CBC,
    padding: CryptoJS.pad.Pkcs7
  });
  let decryptedStr = decrypt.toString(CryptoJS.enc.Utf8);
  return decryptedStr.toString();
}

/**
 * MD5 加密
 * @param str
 * @returns {*}
 */
export const md5 = (str) => {
  return CryptoJS.MD5(str).toString();
}
