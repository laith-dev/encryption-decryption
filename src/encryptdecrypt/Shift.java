package encryptdecrypt;

/**
 * Concrete Strategy -- Encrypt-Decrypt by shifting the letters by key in a circle
 * (i.e. shifting z by 1 makes it a).
 */
public class Shift implements EncDecAlgorithm {

    /**
     * Encrypt the characters in data by the specified key.
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
            if (String.valueOf(data[i]).matches("[A-Z]")) {
                encryptedData[i] = (char) ((((data[i] % 65) + key) % 26) + 'A');
            } else if (String.valueOf(data[i]).matches("[a-z]")) {
                encryptedData[i] = (char) ((((data[i] % 97) + key) % 26) + 'a');
            } else {
                encryptedData[i] = data[i];
            }
        }

        return String.valueOf(encryptedData);
    }

    /**
     * Decrypt the characters in data by the specified key.
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
            if (String.valueOf(data[i]).matches("[A-Z]")) {
                decryptedData[i] = (char) ((data[i] - 65 + 26 - key) % 26 + 65);
            } else if (String.valueOf(data[i]).matches("[a-z]")) {
                decryptedData[i] = (char) ((data[i] - 97 + 26 - key) % 26 + 97);
            } else {
                decryptedData[i] = data[i];
            }
        }

        return String.valueOf(decryptedData);
    }
}
