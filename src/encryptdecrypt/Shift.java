package encryptdecrypt;

/**
 * Concrete Strategy -- Encrypt-Decrypt by shifting the letters by key in a circle
 * (i.e. shifting z by 1 makes it a).
 */
public class Shift implements EncDecAlgorithm {

    public static final String ALGORITHM_NAME = "shift";

    /**
     * Encrypt the characters by the given key.
     *
     * @param data to be encrypted.
     * @param key  how many cells each letter in data should be moved (forwards).
     * @return the encrypted data.
     */
    @Override
    public String encrypt(char[] data, int key) {
        char[] encryptedData = new char[data.length];

        for (int i = 0; i < encryptedData.length; i++) {
            // Shift only letters, otherwise, keep the character unchanged.
            final char currentChar = data[i];
            if (String.valueOf(currentChar).matches("[A-Za-z]")) {
                char startingPointChar = Character.isUpperCase(currentChar) ? 'A' : 'a';
                encryptedData[i] = (char) ((((currentChar % startingPointChar) + key) % 26) + startingPointChar);
            } else {
                encryptedData[i] = currentChar;
            }
        }

        return String.valueOf(encryptedData);
    }

    /**
     * Decrypt the characters in data by the given key.
     *
     * @param data to be decrypted.
     * @param key  how many cells each letter in data should be moved (backwards).
     * @return the decrypted data.
     */
    @Override
    public String decrypt(char[] data, int key) {
        char[] decryptedData = new char[data.length];

        for (int i = 0; i < decryptedData.length; i++) {
            // Shift only letters, otherwise, keep the character unchanged.
            final char currentChar = data[i];
            if (String.valueOf(currentChar).matches("[A-Za-z]")) {
                char startingPointChar = Character.isUpperCase(currentChar) ? 'A' : 'a';
                decryptedData[i] = (char) ((currentChar - startingPointChar + 26 - key) % 26 + startingPointChar);
            } else {
                decryptedData[i] = currentChar;
            }
        }

        return String.valueOf(decryptedData);
    }
}