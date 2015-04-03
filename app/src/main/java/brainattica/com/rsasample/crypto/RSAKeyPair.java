package brainattica.com.rsasample.crypto;

import java.security.KeyPair;

/**
 * Created by javiermanzanomorilla on 25/12/14.
 */
public class RSAKeyPair {

    private String publicKey;

    private String privateKey;

    private KeyPair pair;


    public static RSAKeyPair create(KeyPair pair, String publicKey, String privateKey) {
        RSAKeyPair keyPair = new RSAKeyPair();
        keyPair.pair = pair;
        keyPair.privateKey = privateKey;
        keyPair.publicKey = publicKey;
        return keyPair;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public KeyPair getPair() {
        return pair;
    }
}
