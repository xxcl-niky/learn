package com.iflytek.jbxie.learn2.encryption;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * des加密
 *
 * @author jbxie
 * @create 2020/01/13 14:04
 */

public class DesTests {

    public byte[] bytes;

    public static void main(String[] args) {
//        String key = getKey();
//        String key = "iflytek!@#";
//        System.out.println("DES密钥:" + key);
//        String text = "jbxiewanli";
//        String encryptorContent = encryptor(text, key);
//        System.out.println("加密:" + encryptorContent);
//        String decryptorContent = decryptor(encryptorContent, key);
//        System.out.println("解密:" + decryptorContent);


//        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
/*        try {
            // SHA1PRNG随机数算法
            SecureRandom rng = SecureRandom.getInstance("SHA1PRNG");
//            rng.setSeed(21);

            // 生成随机数
            int numberToGenerate = 20;
            byte randNumbers[] = new byte[numberToGenerate];
            rng.nextBytes(randNumbers);

            // 打印随机数
            for (int j = 0; j < numberToGenerate; j++) {
                System.out.print(randNumbers[j] + " ");
            }
            System.out.println("字符串:" + new String(randNumbers));
            System.out.println("base64:" + Base64.encodeBase64String(randNumbers));
        } catch (Exception e) {
        }*/
//        long a = 1607587227707L;
//        long b = 1605234811403L;
//        int c = (int)(a - b);
//        int d = 1000*3600*24;
//        int t =  (int)(a - b)/(1000*3600*24);
//        int t1 = (int)((a - b)/(1000*3600*24));
//        int t2 =  ((int)(a - b))/(1000*3600*24);
//        int t3 =  c/d;
//        long t4 = (a - b)/(1000*3600*24);
//        System.out.println("t:" + t);
//        System.out.println("t1:" + t1);
//        System.out.println("t2:" + t2);
//        System.out.println("t3:" + t3);
//        System.out.println("t4:" + t4);
//        System.out.println("c:" + c);
////        t:-22
////        t1:27
////        t2:-22
////        t3:-22
////        t4:27
////        c:-1942550992
////        c1:2352416304
////        d:86400000
//        // java中int的取值范围为-2147483648到+-2147483648
//        // 2352416304
//        System.out.println("c1:" + (a - b));
//        System.out.println("d:" + d);


//        int inputType = 664;
//        // 险种
//        Set<Integer> typeInsuranceSet = new HashSet<Integer>(){{
//            add(663);
//            add(664);
//            add(665);
//            add(673);
//            add(674);
//            add(675);
//        }};
//        /***
//         * 判断购买的险种在不在指定险种中
//         */
//        // 险种在指定险种中
//        if (typeInsuranceSet.contains(inputType)) {
//            System.out.println("t0");
//            // 医保所在城市可参考 险种状况判断 TODO
//        } else { // 险种在指定险种中  你自己完成  TODO
//            System.out.println("t1");
//        }
/*
        String t = "";
        String q = "tuiguang2_1";
        Set<String> channels = Stream.of(t.split(",")).collect(Collectors.toSet());

        String t1 = "tuiguang2";
        Set<String> channels1 = Stream.of(t1.split(",")).collect(Collectors.toSet());

        String t2 = "tuiguang2,tuiguang2_1";
        Set<String> channels2 = Stream.of(t2.split(",")).collect(Collectors.toSet());

        Set s = new HashSet();

        System.out.println("channels:" + channels + " isEmpty:" +  channels.isEmpty() + " isContain:" +  channels.contains(q));
        System.out.println("channels1:" + channels1 + " isEmpty:" +  channels1.isEmpty() + " isContain:" +  channels1.contains(q));
        System.out.println("channels2:" + channels2 + " isEmpty:" +  channels2.isEmpty() + " isContain:" +  channels2.contains(q));
        System.out.println("set isEmpty:" +  s.isEmpty() + " isContain:" +  s.contains(q));
*/
        long nanoSecond = 100000;
        long setMillSecond = nanoSecond / 1000000;
        long setNanoSecond = nanoSecond % 1000000;

        System.out.println(setMillSecond);
        System.out.println(setNanoSecond);

    }

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
