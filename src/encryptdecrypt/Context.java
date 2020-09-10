package encryptdecrypt;

public class Context {

    private Algorithm algorithm;
    private int key;
    private String mode;
    private String dataToBeEncrypted;

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setDataToBeEncrypted(String dataToBeEncrypted) {
        this.dataToBeEncrypted = dataToBeEncrypted;
    }

    public String getDataToBeEncrypted() {
        return dataToBeEncrypted;
    }

    public String getMode() {
        return mode;
    }

    public int getKey() {
        return key;
    }

    public String encrypt(char[] data, int key) {
        return this.algorithm.encrypt(data, key);
    }

    public String decrypt(char[] data, int key) {
        return this.algorithm.decrypt(data, key);
    }

}
