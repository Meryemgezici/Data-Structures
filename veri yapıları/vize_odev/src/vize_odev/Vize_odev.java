/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vize_odev;

/**
 *
 * @author HP Omen
 */
public class Vize_odev {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PeakFinder2D  p1=new PeakFinder2D(2,4);
         p1.printArray();
         p1.testGreedyAlg();
         p1.testDivideAndConq1(0, 3);
         p1.testDivideAndConq2(0, 0, 1, 3);
         System.out.println("\n");
         
         PeakFinder2D  p2=new PeakFinder2D(3,4);
         p2.printArray();
         p2.testGreedyAlg();
         p2.testDivideAndConq1(0, 3);
         p2.testDivideAndConq2(0, 0, 0, 3);
         System.out.println("\n");
         
         PeakFinder2D  p3=new PeakFinder2D(1,1);
         p3.printArray();
          p3.testGreedyAlg();
          p3.testDivideAndConq1(0, 0);
         p3.testDivideAndConq2(0, 0, 0, 0);
        
         
       
        
    }
    
}
