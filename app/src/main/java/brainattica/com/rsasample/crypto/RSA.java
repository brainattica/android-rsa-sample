package brainattica.com.rsasample.crypto;

import android.util.Base64;
import android.util.Log;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.spec.RSAKeyGenParameterSpec;

import javax.crypto.Cipher;

import brainattica.com.rsasample.utils.Preferences;


/**
 * Created by javiermanzanomorilla on 24/12/14.
 */
public class RSA {

    static {
        Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(), 1);
    }

    private static final String TAG = RSA.class.getSimpleName();

    private static final int KEY_SIZE = 1024;


    public static KeyPair generate() {
        try {
            SecureRandom random = new SecureRandom();
            RSAKeyGenParameterSpec spec = new RSAKeyGenParameterSpec(KEY_SIZE, RSAKeyGenParameterSpec.F4);
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "SC");
            generator.initialize(spec, random);
            return generator.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static byte[] encrypt(Key publicKey, byte[] toBeCiphred) {
        try {
            Cipher rsaCipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding", "SC");
            rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return rsaCipher.doFinal(toBeCiphred);
        } catch (Exception e) {
            Log.e(TAG, "Error while encrypting data: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static String encryptToBase64(Key publicKey, String toBeCiphred) {
        byte[] cyphredText = RSA.encrypt(publicKey, toBeCiphred.getBytes());
        return Base64.encodeToString(cyphredText, Base64.DEFAULT);
    }

    public static byte[] decrypt(Key privateKey, byte[] encryptedText) {
        try {
            Cipher rsaCipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding", "SC");
            rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);
            return rsaCipher.doFinal(encryptedText);
        } catch (Exception e) {
            Log.e(TAG, "Error while decrypting data: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static String decryptFromBase64(Key key, String cyphredText) {
        byte[] afterDecrypting = RSA.decrypt(key, Base64.decode(cyphredText, Base64.DEFAULT));
        return stringify(afterDecrypting);
    }

    public static String encryptWithKey(String key, String text) {
        try {
            PublicKey apiPublicKey = Crypto.getRSAPublicKeyFromString(key);
            return encryptToBase64(apiPublicKey, text);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String encryptWithStoredKey(String text) {
        String strippedKey = Crypto.stripPublicKeyHeaders(Preferences.getString(Preferences.RSA_PUBLIC_KEY));
        return encryptWithKey(strippedKey, text);
    }

    public static String decryptWithStoredKey(String text) {
        try {
            String strippedKey = Crypto.stripPrivateKeyHeaders(Preferences.getString(Preferences.RSA_PRIVATE_KEY));
            PrivateKey privateKey = Crypto.getRSAPrivateKeyFromString(strippedKey);
            return decryptFromBase64(privateKey, text);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static class FixedRand extends SecureRandom {

        MessageDigest sha;
        byte[] state;

        FixedRand() {
            try {
                this.sha = MessageDigest.getInstance("SHA-1");
                this.state = sha.digest();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("can't find SHA-1!");
            }
        }

        public void nextBytes(byte[] bytes) {

            int off = 0;

            sha.update(state);

            while (off < bytes.length) {
                state = sha.digest();

                if (bytes.length - off > state.length) {
                    System.arraycopy(state, 0, bytes, off, state.length);
                } else {
                    System.arraycopy(state, 0, bytes, off, bytes.length - off);
                }

                off += state.length;

                sha.update(state);
            }
        }
    }


    public static String stringify(byte[] bytes) {
        return stringify(new String(bytes));
    }

    private static String stringify(String str) {
        String aux = "";
        for (int i = 0; i < str.length(); i++) {
            aux += str.charAt(i);
        }
        return aux;
    }


}
