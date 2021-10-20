package encryptdecrypt;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Two algorithms for encryption/decryption:
 * <p>
 * -- Shift: shifts each letter by the specified key according to its order in the alphabet in circle.
 * -- Unicode: shifts every character by the specified key.
 * <p>
 * This is a command-line program, and it has the following options:
 * 1) -alg <algorithm_name> ==> "shift" or "unicode" --> default is "shift".
 * 2) -key <key_value>                               --> default is 0.
 * 3) -mode <mode> ==> "enc" or "dec"                --> default is "enc".
 * 4) -data <text_to_be_encrypted/decrypted>         --> default is empty string.
 * 5) -in <file_name> ==> get the data to be encrypted/decrypted from "file_name".
 * 6) -out <file_name> ==> write the resulted text to "file_name".
 * <p>
 * # -data option overrides -in.
 * # If -out was not specified, the cipher text is printed out to the standard output.
 * <p>
 * Strategy Design Pattern is used.
 */
public class Main {

    public static void main(String[] args) {
        EncDecManager encDecManager = new EncDecManager();

        /*
         * Set default values.
         * */
        encDecManager.setAlgorithm(new Shift());
        encDecManager.setKey(0);
        encDecManager.setMode(EncDecAlgorithm.MODE_ENCRYPTION);
        encDecManager.setInputText("");

        // Check if -data option is specified. If so, store it, otherwise keep the default value.
        for (int i = 0; i < args.length; i += 2) {
            if (args[i].equals("-data")) {
                encDecManager.setInputText(args[i + 1]);
                break;
            }
        }

        // If -out option specified, store the file name to write the cipher text to.
        String outputFileName = "";

        // Each argument inserted is followed by its value.
        // i += 2 --> iterate on options only, not parameters/values.
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-alg":
                    final String algorithm = args[i + 1];
                    if (algorithm.equalsIgnoreCase(Shift.ALGORITHM_NAME)) {
                        encDecManager.setAlgorithm(new Shift());
                    } else if (args[i + 1].equalsIgnoreCase(Unicode.ALGORITHM_NAME)) {
                        encDecManager.setAlgorithm(new Unicode());
                    } else {
                        throw new IllegalArgumentException("Encryption/Decryption algorithm Unknown: " + algorithm);
                    }
                    break;

                case "-mode":
                    encDecManager.setMode(parseMode(args[i + 1]));
                    break;

                case "-key":
                    encDecManager.setKey(Integer.parseInt(args[i + 1]));
                    break;

                case "-in":
                    /* -data option overrides -in, so check first if the user has already specified -data option. */
                    if (encDecManager.getInputText().isEmpty()) {
                        // The file contains the text for encryption/decryption.
                        String dataFileName = args[i + 1];

                        File file = new File(dataFileName);
                        try (Scanner sc = new Scanner(file)) {
                            if (sc.hasNextLine()) {
                                encDecManager.setInputText(readFileAsString(dataFileName));
                            }
                        } catch (FileNotFoundException e) {
                            System.out.println("File Not Found: " + file.getPath());
                        } catch (IOException e) {
                            System.out.println("Exception occurred! " + Arrays.toString(e.getStackTrace()));
                        }
                    }
                    break;

                /* If -out option is specified, store the file name to write the resulted text to. Otherwise,
                 print it on the standard output. */
                case "-out":
                    outputFileName = args[i + 1];
                    break;
            }
        }

        // Text after encryption/decryption.
        String resultedText = encDecManager.execute();

        /* If -out is not specified, print the data to the standard output. Otherwise, store it in a file. */
        if (outputFileName.isEmpty()) {
            System.out.println(resultedText);
        } else {
            File file = new File(outputFileName);

            try (Writer writer = new FileWriter(file)) {
                writer.write(resultedText);
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found: " + file.getPath());
            } catch (IOException e) {
                System.out.println("IOException" + e.getMessage());
            }
        }
    }

    /**
     * Parses the given string to a pre-defined constant string mode.
     *
     * @param mode the mode to be parsed.
     * @return the string mode that was pre-defined otherwise throws an exception.
     */
    private static String parseMode(String mode) {
        switch (mode) {
            case "enc":
                return EncDecAlgorithm.MODE_ENCRYPTION;

            case "dec":
                return EncDecAlgorithm.MODE_DECRYPTION;

            default:
                throw new IllegalArgumentException("Unexpected mode " + mode);
        }
    }

    /**
     * Read the file given its name and returns the result as a single string.
     *
     * @param fileName the name of the file to be read.
     * @return the string contains the file's contents.
     */
    private static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Path.of(fileName)));
    }
}