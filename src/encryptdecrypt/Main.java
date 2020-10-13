package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Two algorithms for encryption/decryption:
 * <p>
 * -- Shift: shifts each letter by the specified key according to its order in the alphabet in circle.
 * -- Unicode: shift every character by the specified key.
 * <p>
 * The program works from the command line, and it has the following options:
 * 1- -alg <algorithm_name> ==> "shift" or "unicode" --> default is "shift".
 * 2- -key <key_value>                               --> default is 0.
 * 3- -mode <mode> ==> "enc" or "dec"                --> default is "enc".
 * 4- -data <text_to_be_encrypted/decrypted>         --> default is empty string.
 * 5- -in <file_name> ==> get the data to be encrypted from "file_name".
 * 6- -out <file_name> ==> write the cipher text to "file_name".
 * <p>
 * # -data overrides -in.
 * # If -out was not specified, print the cipher text to the standated output.
 * <p>
 * The Strategy Design Pattern is used.
 */
public class Main {

    public static void main(String[] args) {
        EncDecManager encDecManager = new EncDecManager();

        /*
         * Set default values.
         * */
        encDecManager.setAlgorithm(new Shift());
        encDecManager.setKey(0);
        encDecManager.setMode("enc");
        encDecManager.setInputText("");

        // Check if -data option is specified. If so, store it, otherwise keep the default value.
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-data")) {
                encDecManager.setInputText(args[i + 1]);
                break;
            }
        }

        // If -out option specified, store the file name to write the cipher text to.
        String fileToStoreData = "";

        // Each argument inserted is followed by its value.
        // i += 2 --> iterate on arguments only, not values.
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-alg":
                    if (args[i + 1].equalsIgnoreCase("shift")) {
                        encDecManager.setAlgorithm(new Shift());
                    } else if (args[i + 1].equalsIgnoreCase("unicode")) {
                        encDecManager.setAlgorithm(new Unicode());
                    } else {
                        System.out.println("EncDecAlgorithm Unknown!");
                    }
                    break;

                case "-mode":
                    encDecManager.setMode(args[i + 1]);
                    break;

                case "-key":
                    encDecManager.setKey(Integer.parseInt(args[i + 1]));
                    break;

                case "-in":
                    /* Ignore the -in option if the user has entered the text after -data option, otherwise read data
                     * from the specified file location after -in option. */
                    if (encDecManager.getInputText().isEmpty()) {
                        String fileToGetData = args[i + 1];

                        File file = new File(fileToGetData);

                        try (Scanner sc = new Scanner(file)) {
                            // Read all the text in the file as a single string.
                            if (sc.hasNextLine()) {
                                encDecManager.setInputText(readFileAsString(fileToGetData));
                            }
                        } catch (FileNotFoundException e) {
                            System.out.printf("Error. File Not Found %s.\n", file.getPath());
                        } catch (IOException e) {
                            System.out.println("Exception occurred! " + Arrays.toString(e.getStackTrace()));
                        }
                    }
                    break;

                /* If -out option is specified, store the file name to write the resulted text to. Otherwise,
                 print it on the standard output. */
                case "-out":
                    fileToStoreData = args[i + 1];
                    break;
            }
        }

        // Text after encryption/decryption
        String resultedText = encDecManager.execute();

        /* If -out is not specified, print the data to the standard output. Otherwise, store it in a file. */
        if (fileToStoreData.isEmpty()) {
            System.out.println(resultedText);
        } else {
            File file = new File(fileToStoreData);

            try (PrintWriter writer = new PrintWriter(file)) {
                writer.print(resultedText);
            } catch (FileNotFoundException e) {
                System.out.printf("Error. File Not Found %s.\n", file.getPath());
            }
        }

    }

    private static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

}
