/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author thanhtd
 */
public class Utils {
    public static Integer[] parseMsg(String msg)
    {
        String[] tokens = msg.split(","); 
        Integer[] res = new Integer[tokens.length];
        int i = 0;
        for (String s : tokens) {
            res[i++] = Integer.parseInt(s);
        }
        return res;
    }
    
    public static String parseNumList(Integer[] numsList)
    {
        StringBuilder sbuilder = new StringBuilder();
        sbuilder.append(numsList[0]);
                
        for (int i = 1; i < numsList.length; i++) {
            sbuilder.append("\n" + numsList[i]);
        }
        return sbuilder.toString();
    }
}
