/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

/**
 *
 * @author thanhtd
 */
public class RunningMode {
    protected  long t1, t2, initTime;
    protected String mode = "Stand Alone";
    protected Generator generator;
    protected int size;
    public RunningMode()
    {
        generator = new Generator();
    }
    protected  String getResult()
    {
        return generator.toContentString();
    }
    protected  String getPerformanceReport()
    {
        String report = "";
        long stime = (t2 - t1)/1000;
        report  = "--------------------------------------------------------------\n";
        report += "Mode: " + mode + "\n";
        report += "#Numbers: " + size + "\n"; 
        report += "Generating time: " + (t2-t1) + " milliseconds\n";
//        report += "Sorting time in millisecond: " + (t2-t1) + " milliseconds\n";
//        report += "Sorting time in second: " + stime + " seconds\n";
        report += "--------------------------------------------------------------\n";
        return report;
    }
    protected  void setInitTime(long time)
    {
        initTime = time;
    }
}
