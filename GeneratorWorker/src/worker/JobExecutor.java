/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worker;

import generator.Generator;
import utils.Utils;

/**
 *
 * @author thanhtd
 */
public class JobExecutor {
    private Generator generator;
    public JobExecutor()
    {
        generator = new Generator();
    }
    public String run(String msg)
    {
        String res = "";
        try {
            int num = Integer.parseInt(msg);
            generator.init(num);
            res = Utils.parseNumList(generator.numsList);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
        return res;
    }
}
