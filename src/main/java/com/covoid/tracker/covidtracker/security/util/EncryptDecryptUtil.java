package com.covoid.tracker.covidtracker.security.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.covoid.tracker.covidtracker.exceptions.SecurityException;

/**
 * Utility class to encrypt and decrypt
 *
 */
public class EncryptDecryptUtil
{

  /**
   * This method encrypts the given word using the password and salt
   * @param strToEncrypt
   * @param secret
   * @param salt
   * @return
   * @throws SecurityException
   */
  public static String encrypt(String strToEncrypt, String secret, String salt) throws SecurityException
  {
    byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    IvParameterSpec ivspec = new IvParameterSpec(iv);

    SecretKeyFactory factory;
    try
    {
      factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

      KeySpec spec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), 65536, 256);
      SecretKey tmp = factory.generateSecret(spec);
      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
      return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
    }
    catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException
        | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException
        | UnsupportedEncodingException e)
    {
      throw new SecurityException(e.getMessage(), e);
    }

  }

  /**
   * This method decrypts the encrypted wor using the password and the salt
   * @param strToDecrypt
   * @param secret
   * @param salt
   * @return
   * @throws SecurityException
   */
  public static String decrypt(String strToDecrypt, String secret, String salt) throws SecurityException
  {
    try
    {
      byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
      IvParameterSpec ivspec = new IvParameterSpec(iv);

      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
      KeySpec spec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), 65536, 256);
      SecretKey tmp = factory.generateSecret(spec);
      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
      return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    }
    catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException
        | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e)
    {
      throw new SecurityException(e.getMessage(), e);
    }
  }

}
