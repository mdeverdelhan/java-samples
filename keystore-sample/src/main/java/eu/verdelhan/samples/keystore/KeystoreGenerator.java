
package eu.verdelhan.samples.keystore;

import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.spec.KeySpec;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.xml.bind.DatatypeConverter;

public class KeystoreGenerator {

    public static void main(String[] args) {
        try {
            // Will need the JCE (Java Cryptography Extension)

            // Secret key
            byte[] secretKeyArray = hexStringToByteArray("363842363842363842363842363842363842363842363842");
            KeySpec secretKeySpec = new DESedeKeySpec(secretKeyArray);
            SecretKey secretKey = SecretKeyFactory.getInstance("DESede").generateSecret(secretKeySpec);

            // Keystore
            char[] keystorePassword = "keystorePwd".toCharArray();
            KeyStore keyStore = KeyStore.getInstance("JCEKS");
            keyStore.load(null, keystorePassword);

            // Key entry
            String keyAlias = "my_secret_key";  
            char[] keyPassword = "keyPwd".toCharArray();
            keyStore.setKeyEntry(keyAlias, secretKey, keyPassword, null);

            // Write the keystore file
            keyStore.store(new FileOutputStream("keystore.jceks"), keystorePassword);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * @param s an hex string
     * @return the corresponding byte array
     */
    private static byte[] hexStringToByteArray(String s) {
        return DatatypeConverter.parseHexBinary(s);
    }

}
