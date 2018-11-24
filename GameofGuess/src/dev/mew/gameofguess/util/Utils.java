package dev.mew.gameofguess.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Mattias Viklund
 */
public class Utils {

    /**
     * Creates or Opens an already existing file for reading
     *
     * @param path location of file
     * @return BufferedReader opened at the location of the input path
     */
    public static BufferedReader loadOrCreateFile(String path) {
        FileReader reader = null;

        try {
            reader = new FileReader(path);

        } catch (FileNotFoundException e) {
            // If the path doesn't exist, make it
            try {
                File f = new File(path);
                f.createNewFile();
                
                // Set the reader to that file
                reader = new FileReader(f);

            } catch (IOException ex) {
                return null;

            }
        }

        BufferedReader bReader = new BufferedReader(reader);
        return bReader;

    }

    /**
     * Creates or Opens an already existing file for modification
     *
     * @param path location of file
     * @return BufferedWriter opened at the location of the input path
     */
    public static BufferedWriter openOrCreateFile(String path) {
        FileWriter writer = null;

        try {
            writer = new FileWriter(path, true);

        } catch (IOException ex) {
            // If the file doesn't exist, make it
            try {
                writer = new FileWriter(path);
                
            } catch (IOException ex1) {
                return null;

            }
        }
        
        BufferedWriter bWriter = new BufferedWriter(writer);
        return bWriter;

    }
}
