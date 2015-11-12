/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.util.Random;

/**
 *
 * @author thanhtd
 */
public class Generator {
    public Integer[] numsList;
    public int size;
    private StringBuilder sbuilder;
    public Random random = new Random();
    public Generator()
    {
        
    } 
           
    public long init(int num)
    {
        
        numsList = null;
        sbuilder = null;
        System.gc();
        numsList = new Integer[num];
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            numsList[i] = random.nextInt()%num;
           
        }
        long t2 = System.currentTimeMillis();
        size = numsList.length;
        return (t2-t1);
    }
    
    public void show()
    {
        for (int i : numsList) {
            System.out.println(i);
        }
    }
    
    
    /**
     *
     * @return
     */
    public String toContentString() {
        sbuilder = new StringBuilder();
        for (int i = 0; i < numsList.length; i++) {
            sbuilder.append(numsList[i] + "\n");
        }
        
        return sbuilder.toString();
    }
//    public static void main(String[] args)
//    {
//       StandAloneSorting.run(10);
//    }
}
