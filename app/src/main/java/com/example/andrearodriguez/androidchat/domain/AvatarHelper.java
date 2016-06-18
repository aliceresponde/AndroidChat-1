package com.example.andrearodriguez.androidchat.domain;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by alice on 6/17/16.
 */

public class AvatarHelper {

    public final static  String GRAVAR_URL = "http://www.gravatar.com/avatar/";

    /**
     * Calcular URL  segun email, basados en Gravatar
     *
     * @param email
     * @return
     */
    public static String getAvatarUL(String email) {
        final String url = GRAVAR_URL + md5(email) + "?s=72";
        Log.i("finalURL" , url);
        return url;
    }


    private static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            //cerate  MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());

            byte messageDigest[] = digest.digest();

            //create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            Log.i("pego", hexString.toString());
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


}
