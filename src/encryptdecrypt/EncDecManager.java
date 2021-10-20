package encryptdecrypt;

/**
 * Context that controls the operations of encrypting/decrypting the data.
 */
public class EncDecManager {

    /**
     * The algorithm used to cipher the text data.
     */
    private EncDecAlgorithm algorithm;

    /**
     * The key by which each character in the text data is shifted.
     */
    private int key;

    /**
     * Encryption or Decryption as defined in {@link EncDecAlgorithm}.
     */
    private String mode;

    /**
     * The data text to process.
     */
    private String inputText;

    /**
     * Execute the encryption/decryption operations and return the resulted text.
     *
     * @return the resulted text after encryption/decryption.
     */
    public String execute() {
        switch (mode) {
            case EncDecAlgorithm.MODE_ENCRYPTION:
                return this.algorithm.encrypt(inputText.toCharArray(), key);

            case EncDecAlgorithm.MODE_DECRYPTION:
                return this.algorithm.decrypt(inputText.toCharArray(), key);
        }

        return null;
    }

    public void setAlgorithm(EncDecAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public String getInputText() {
        return inputText;
    }
}