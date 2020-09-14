package encryptdecrypt;

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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Context context = new Context();

        /*
         * Set default values.
         * */
        context.setAlgorithm(new Shift());
        context.setKey(0);
        context.setMode("enc");
        context.setDataToBeEncrypted("");

        // Check if -data option is specified. If so, store it, otherwise keep the default value.
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-data")) {
                context.setDataToBeEncrypted(args[i + 1]);
                break;
            }
        }

        // If -out option specified, store the file name to write the cipher text to.
        String fileToWriteData = "";

        // Each argument inserted is followed by its value.
        // i += 2 --> iterate on arguments only, not values.
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {

                case "-alg":
                    if (args[i + 1].equals("shift")) {
                        context.setAlgorithm(new Shift());
                    }
                    else if (args[i + 1].equals("unicode")) {
                        context.setAlgorithm(new Unicode());
                    }
                    else {
                        System.out.println("Algorithm Unknown!");
                    }
                    break;

                case "-mode":
                    context.setMode(args[i + 1]);
                    break;

                case "-key":
                    context.setKey(Integer.parseInt(args[i + 1]));
                    break;

                case "-in":
                    // If dataToBeEncrypted is still empty, this means that -data option was not specified therefore,
                    // get the data from the file.
                    if (context.getDataToBeEncrypted().isEmpty()) {
                        String fileToReadDataFrom = args[i + 1];

                        File file = new File(fileToReadDataFrom);

                        try (Scanner sc = new Scanner(file)) {
                            // Read all the text in the file as a single string.
                            if (sc.hasNextLine()) {
                                context.setDataToBeEncrypted(readFileAsString(fileToReadDataFrom));
                            }
                        }
                        catch (FileNotFoundException e) {
                            System.out.printf("Error. File Not Found %s.\n", file.getPath());
                        }
                        catch (IOException e) {
                            System.out.println("Exception occurred! " + Arrays.toString(e.getStackTrace()));
                        }
                    }
                    break;

                // If -out option is specified, store the file name to write the encrypted data to. Otherwise,
                // print it on the standard output.
                case "-out":
                    fileToWriteData = args[i + 1];
                    break;
            }
        }

        String cipherText = ""; // text after encryption/decryption
        switch (context.getMode()) {

            case "enc":
                cipherText = context.encrypt(context.getDataToBeEncrypted().toCharArray(), context.getKey());
                break;

            case "dec":
                cipherText = context.decrypt(context.getDataToBeEncrypted().toCharArray(), context.getKey());
                break;
        }

        /* If -out is not specified, print the data to the standard output. Otherwise, store it in a file. */
        if (fileToWriteData.isEmpty()) {
            System.out.println(cipherText);
        }
        else {
            File file = new File(fileToWriteData);

            try (PrintWriter writer = new PrintWriter(file)) {
                writer.print(cipherText);
            }
            catch (FileNotFoundException e) {
                System.out.printf("Error. File Not Found %s.\n", file.getPath());
            }
        }

    }

    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

}
