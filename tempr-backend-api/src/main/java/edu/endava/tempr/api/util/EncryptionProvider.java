package edu.endava.tempr.api.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zsoltszabo on 17/12/2016.
 */
public class EncryptionProvider {

    public EncryptionProvider(){}

    public String hashWithSHA256(String text){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try {
            md.update(text.getBytes("UTF-8")); // Change this to "UTF-16" if needed
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return String.format("%064x", new java.math.BigInteger(1, md.digest()));
    }
}
