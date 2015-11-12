/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import sorting.algorithm.Heap;

/**
 *
 * @author thanhtd
 */
public class StandAloneMode extends RunningMode {
        
    public StandAloneMode()
    {
        super();
    }
    public String run(int num)
    {
        this.size = num;
//        System.out.println("Generating ...");
        t1 = System.currentTimeMillis();
        generator.init(num);
        String res = generator.toContentString();
        t2 = System.currentTimeMillis();
//        System.out.println("Generating done!");
        return res;
    }
    
}
