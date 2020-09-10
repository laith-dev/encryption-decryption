package encryptdecrypt;

/**
 * Concrete Strategy -- Encrypt-Decrypt by shifting the letters by key in a circle.
 */
public class Shift implements Algorithm {

    @Override
    public String encrypt(char[] data, int key) {

        char[] encryptedData = new char[data.length];

        for (int i = 0; i < encryptedData.length; i++) {
            // Shift only letters.
            if (String.valueOf(data[i]).matches("[A-Z]")) {
                encryptedData[i] = (char) ((((data[i] % 65) + key) % 26) + 'A');
            }
            else if (String.valueOf(data[i]).matches("[a-z]")) {
                encryptedData[i] = (char) ((((data[i] % 97) + key) % 26) + 'a');
            }
            else {
                encryptedData[i] = data[i];
            }
        }

        return String.valueOf(encryptedData);
    }

    @Override
    public String decrypt(char[] data, int key) {

        char[] decryptedData = new char[data.length];

        for (int i = 0; i < decryptedData.length; i++) {
            // Shift only letters.
            if (String.valueOf(data[i]).matches("[A-Z]")) {
                decryptedData[i] = (char) ((data[i] - 65 + 26 - key) % 26 + 65);
            }
            else if (String.valueOf(data[i]).matches("[a-z]")) {
                decryptedData[i] = (char) ((data[i] - 97 + 26 - key) % 26 + 97);
            }
            else {
                decryptedData[i] = data[i];
            }
        }

        return String.valueOf(decryptedData);
    }
}
