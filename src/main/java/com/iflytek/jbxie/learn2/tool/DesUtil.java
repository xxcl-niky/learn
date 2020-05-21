package com.iflytek.jbxie.learn2.tool;

/**
 * des 对称加密
 *
 * @author jbxie
 * @create 2020/01/13 11:40
 */

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

@Component
public class DesUtil{

    public byte[] bytes;

    /**
     * 生成一个DES密钥
     * @return
     */
    public static String getKey(){
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(56);
            // 生成一个Key
            SecretKey generateKey = keyGenerator.generateKey();
            // 转变为字节数组
            byte[] encoded = generateKey.getEncoded();
            // 生成密钥字符串
            String encodeHexString = Hex.encodeHexString(encoded);
            return encodeHexString;
        } catch (Exception e) {
            e.printStackTrace();
            return "密钥生成错误.";
        }
    }

    /**
     * 加密
     * @param str 明文
     * @param key 秘钥
     * @return
     */
    public static String encryptor(String str,String key){
        String s = null;
        try {
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            // 初始化密码器，用密钥 secretKey 进入加密模式
            cipher.init(Cipher.ENCRYPT_MODE, securekey);
            byte[] bytes = cipher.doFinal(str.getBytes());
            s = Base64.encodeBase64String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return "加密错误.";
        }
        return s;
    }

    /**
     * 解密
     * @param buff 密文
     * @param key 秘钥
     * @return
     */
    public static String decryptor(String buff,String key){
        String s = null;
        try {
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE,securekey);
            byte[] responseByte=cipher.doFinal(Base64.decodeBase64(buff));
            s=new String(responseByte);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return "解密错误.";
        }
    }
}