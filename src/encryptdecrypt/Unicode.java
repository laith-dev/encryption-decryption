package encryptdecrypt;

/**
 * Concrete Strategy -- Encrypt-Decrypt by shifting the characters by key.
 */
public class Unicode implements EncDecAlgorithm {

    public static final String ALGORITHM_NAME = "unicode";

    @Override
    public String encrypt(char[] data, int key) {
        char[] encryptedData = new char[data.length];

        for (int i = 0; i < encryptedData.length; i++) {
            encryptedData[i] = (char) (data[i] + key);
        }

        return String.valueOf(encryptedData);
    }

    @Override
    public String decrypt(char[] data, int key) {
        char[] decryptedData = new char[data.length];

        for (int i = 0; i < decryptedData.length; i++) {
            decryptedData[i] = (char) (data[i] - key);
        }

        return String.valueOf(decryptedData);
    }
}