package encryptdecrypt;

/**
 * Strategy Class.
 */
public interface EncDecAlgorithm {

    String encrypt(char[] data, int key);

    String decrypt(char[] data, int key);

}
