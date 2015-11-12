/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xerial.snappy.Snappy;

/**
 *
 * @author thanhtd
 */
public class SnappyUtils {
    public static String compress(String msg)
    {
        try {
            return new String(Snappy.compress(msg, Charset.forName("UTF-8")), Charset.forName("UTF-8"));
        } catch (IOException ex) {
            Logger.getLogger(SnappyUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    public static String decompress(String compressedMsg)
    {
        try {
            return Snappy.uncompressString(compressedMsg.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(SnappyUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
