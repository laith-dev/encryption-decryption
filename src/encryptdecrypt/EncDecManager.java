package encryptdecrypt;

/**
 * Context Class that controls the operations of encrypting/decrypting the data.
 */
public class EncDecManager {

    private EncDecAlgorithm algorithm;

    private int key;
    private String mode;
    private String inputText;

    /**
     * Execute the encryption/decryption operations and return the resulted text.
     *
     * @return the resulted text after encryption/decryption.
     */
    public String execute() {
        switch (mode) {
            case "enc":
                return this.algorithm.encrypt(inputText.toCharArray(), key);

            case "dec":
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
