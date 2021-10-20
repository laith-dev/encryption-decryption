package encryptdecrypt;

/**
 * Strategy.
 */
public interface EncDecAlgorithm {

    String MODE_ENCRYPTION = "encryption";

    String MODE_DECRYPTION = "decryption";

    String encrypt(char[] data, int key);

    String decrypt(char[] data, int key);
}