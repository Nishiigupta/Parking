package xyz.willz.security;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Password {
	
	public String getSecurePassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        
        byte[] salt = "harsh".getBytes();
         
        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< hash.length ;i++)
        {
            sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        return sb.toString();
    }
	
	
	
}
