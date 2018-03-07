package humanqp.com.keystorechain;

import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void getPivateKey() {
    /*
     * Generate a new EC key pair entry in the Android Keystore by
     * using the KeyPairGenerator API. The private key can only be
     * used for signing or verification and only with SHA-256 or
     * SHA-512 as the message digest.
     */
        KeyPairGenerator kpg = null;
        try {
            kpg = KeyPairGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_EC, "AndroidKeyStore");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                kpg.initialize(new KeyGenParameterSpec.Builder(
                        "human2p",
                        KeyProperties.PURPOSE_SIGN | KeyProperties.PURPOSE_VERIFY)
                        .setDigests(KeyProperties.DIGEST_SHA256,
                                KeyProperties.DIGEST_SHA512)
                        .build());
            }

            KeyPair kp = kpg.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }
}
