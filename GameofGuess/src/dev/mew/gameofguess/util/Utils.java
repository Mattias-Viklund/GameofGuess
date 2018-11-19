/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.mew.gameofguess.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author elev
 */
public class Utils {

    public static BufferedReader loadOrCreateFile(String path) {
        FileReader reader = null;

        try {
            reader = new FileReader(path);

        } catch (FileNotFoundException e) {
            File f = new File(path);

            try {
                f.createNewFile();
                reader = new FileReader(f);

            } catch (IOException ex) {
                return null;

            }
        }

        BufferedReader bReader = new BufferedReader(reader);
        return bReader;

    }
}
