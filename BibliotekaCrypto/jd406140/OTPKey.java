package pl.edu.mimuw.crypto.jd406140;

import javax.crypto.SecretKey;
import javax.security.auth.DestroyFailedException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.security.SecureRandom;

public class OTPKey implements SecretKey {

    private byte[] key;
    private boolean destroyed;


    public OTPKey (int keyLength) throws InvalidKeySpecException {

        if (keyLength <= 0) {
            throw new InvalidKeySpecException("wrong keyLength");
        }

        this.key = new byte[keyLength];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(this.key);

        for (int index = 0; index < key.length; index++ ) {

            if((key[index] % 2) == 0) {

                key[index] = 0;
            }
            else if (((key[index] % 2) == 1) || ((key[index] % 2) == -1)){

                key[index] = 1;
            }
            else {

                throw new RuntimeException();
            }
        }
        this.destroyed = false;
    }

    @Override
    public String getAlgorithm() {

        return "OTP";
    }

    @Override
    public final String getFormat() {

        if (this.destroyed) {
            throw new IllegalStateException("This key is no longer valid");
        }

        return "RAW";
    }

    @Override
    public final byte[] getEncoded() {

        if (this.destroyed) {
            throw new IllegalStateException("This key is no longer valid");
        }

        return this.key;
    }

    @Override
    public void destroy() throws DestroyFailedException {

        if (this.destroyed) {
            throw new DestroyFailedException ("This key was already destroyed");
        }
        this.destroyed = true;
        Arrays.fill(key, (byte) 0);
    }

    @Override
    public boolean isDestroyed() {

        return this.destroyed;
    }

}
