package cc.flyflow.im.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * 加密工具类
 */
public class EncryptUtil {
    
    static {
        Security.addProvider(new BouncyCastleProvider());
    }
    
    /**
     * RSA算法
     */
    private static final String RSA_ALGORITHM = "RSA";
    
    /**
     * 对称加密算法
     */
    private static final String AES_ALGORITHM = "AES";
    
    /**
     * RSA加密
     * @param data 待加密数据
     * @param publicKey 公钥
     * @return 加密后的数据
     * @throws Exception 异常
     */
    public static String rsaEncrypt(String data, String publicKey) throws Exception {
        byte[] decodedPublicKey = Base64.getDecoder().decode(publicKey);
        PublicKey key = KeyFactory.getInstance(RSA_ALGORITHM).generatePublic(new X509EncodedKeySpec(decodedPublicKey));
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }
    
    /**
     * RSA解密
     * @param data 待解密数据
     * @param privateKey 私钥
     * @return 解密后的数据
     * @throws Exception 异常
     */
    public static String rsaDecrypt(String data, String privateKey) throws Exception {
        byte[] decodedPrivateKey = Base64.getDecoder().decode(privateKey);
        PrivateKey key = KeyFactory.getInstance(RSA_ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(decodedPrivateKey));
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(data));
        return new String(decrypted);
    }
    
    /**
     * AES加密
     * @param data 待加密数据
     * @param key 密钥
     * @return 加密后的数据
     * @throws Exception 异常
     */
    public static String aesEncrypt(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, generateKey(key));
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }
    
    /**
     * AES解密
     * @param data 待解密数据
     * @param key 密钥
     * @return 解密后的数据
     * @throws Exception 异常
     */
    public static String aesDecrypt(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, generateKey(key));
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(data));
        return new String(decrypted);
    }
    
    /**
     * 生成AES密钥
     * @param key 密钥字符串
     * @return 密钥
     * @throws Exception 异常
     */
    private static SecretKey generateKey(String key) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES_ALGORITHM);
        SecureRandom secureRandom = SecureRandom.getInstanceStrong();
        secureRandom.setSeed(key.getBytes());
        keyGenerator.init(128, secureRandom);
        return keyGenerator.generateKey();
    }
    
    /**
     * 生成RSA密钥对
     * @return 密钥对
     * @throws Exception 异常
     */
    public static KeyPair generateRsaKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }
    
    /**
     * 获取公钥字符串
     * @param publicKey 公钥
     * @return 公钥字符串
     */
    public static String getPublicKeyString(PublicKey publicKey) {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }
    
    /**
     * 获取私钥字符串
     * @param privateKey 私钥
     * @return 私钥字符串
     */
    public static String getPrivateKeyString(PrivateKey privateKey) {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }
}
