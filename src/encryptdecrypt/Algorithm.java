package encryptdecrypt;

/**
 * Strategy
 */
public interface Algorithm {

    String encrypt(char[] data, int key);

    String decrypt(char[] data, int key);
}
