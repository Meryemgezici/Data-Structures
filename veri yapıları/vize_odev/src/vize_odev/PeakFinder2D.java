/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vize_odev;

import java.util.Random;

/**
 *
 * @author HP Omen
 */
public class PeakFinder2D {
    static int[][] a;
    static int nrow;
    static int ncol;
     
    /* public PeakFinder2D(){
         nrow=5;
         ncol=5;
         a = new int[nrow][ncol];
     }*/

    public PeakFinder2D(int x, int y){// Random olarak dizimize değer atadık.
        nrow=x;
        ncol=y;
        a = new int[x][y];
         Random r = new Random();
        for(int i=0; i<x; ++i){
            for(int j=0; j<y; ++j){
           a[i][j] = r.nextInt(); 
            }
        }
            
    }
    public static  int greedyAlg(){
         int peak = a[0][0];
        for(int row = 0; row< nrow; row++){
            for(int col = 0; col < ncol; col++){
                //TODO
                if(col+1<ncol){
                    if(peak<a[row][col+1]){
                        peak=a[row][col+1];
                    }
                    
                }
                else if( col-1>=0){
                     if(peak<a[row][col-1])
                    peak=a[row][col-1];
                }
                else if(row+1<nrow){
                    if(peak<a[row+1][col]){
                        peak=a[row+1][col];
                    }
                    
                }
                else if(row-1>=0){
                    if(peak<a[row-1][col]){
                        peak=a[row-1][col];
                    }
                }
                else {
                    peak=peak;
                }
            }
        }
        return peak;
    }
    
    public static int findMax(int[] b){
        int imax = 0;
        for(int i = 0; i < b.length; i++ ){
            if(b[i]>b[imax]){
                imax = i;
            }
        }
        return imax;
    }
    public static int findMaxOnCol(int col){//sütündaki en büyük olan değerin satırdaki indexini bulur.
        int imax = 0;
        for(int i = 0; i < nrow; i++ ){
            if(a[i][col]>a[imax][col]){
                imax = i;
            }
        }
        return imax;
    }
    public static int findMaxOnRow(int row){//satırdaki en büyük değerin sütündaki indexini bulur.
        int imax = 0;
        for(int i = 0; i < ncol; i++ ){
            if(a[row][i]>a[row][imax]){
                imax = i;
            }
        }
        return imax;
    }
    public static int divideAndConquer1(int startcol, int endcol){//Sütünü ikiye bolerek peak bul.
        //TODO correct and complete the code
        int peak=a[0][0];
        int midcol =(int)(startcol + endcol)/2; //sütünü ikiye bolduk.
        int imax = findMaxOnCol(midcol); // number of rows: m
        ///base case TODO: boundary conditions
        if(midcol+1<ncol && midcol-1>=0){
        if(a[imax][midcol] >= a[imax][midcol+1] && a[imax][midcol] >= a[imax][midcol-1]  ){ //sütündaki en büyük değer sağındaki ve solundaki sayıdan büyük ise peakdir.
            peak= a[imax][midcol];
        }
        }
        if(midcol+1<ncol){
        if(a[imax][midcol] < a[imax][midcol+1] )//sutundaki en buyuk değerin sağındaki değer daha büyükse artık sağ tarfta peak ara 
            return  divideAndConquer1(midcol, endcol);
        }
        if(midcol-1>=0){
        if(a[imax][midcol] < a[imax][midcol-1]  )//sutundaki en buyuk değerin solundaki değer daha büyükse artık sol tarfta peak ara
            return divideAndConquer1(startcol, midcol);
        }
    return peak;
    }
    
    

    /** derste anlatilan divide and conquer  yontemini kullanarak O(n+m)
     *  zamanda peak bulan algoritmanin implemantasyonunu yaziniz*/
    public static int divideAndConquer2(int startrow, int startcol, int endrow, int endcol){
        //TODO
        int peak=a[0][0];
        if(endcol-startcol+1>=endrow-startrow+1){//satır ve sutunu sayısını karşılatır.sutun buyukse sutunu ikiye bol.
            
        int midcol =(int)(startcol + endcol)/2; //1 tane
        int imax = findMaxOnCol(midcol); // number of rows: m
        ///base case TODO: boundary conditions
        if(midcol+1<ncol && midcol-1>=0){
        if(a[imax][midcol] >= a[imax][midcol+1] && a[imax][midcol] >= a[imax][midcol-1]  ){ //2 tane
           peak= a[imax][midcol];
        }
        }
        if(midcol+1<ncol){
        if(a[imax][midcol] < a[imax][midcol+1] ){
            startcol=midcol;
            return  divideAndConquer2(startrow,midcol, endrow,endcol);
        }
        
        }
        if(midcol-1>=0){
        if(a[imax][midcol] < a[imax][midcol-1]  ){
            endcol=midcol;
            return divideAndConquer2(startrow,startcol,endrow, midcol);
        }
        }
        
        }
        else{//satır sutundan buyukse
            
            
        int midrow =(int)(startrow + endrow)/2; //1 tane
        int imax = findMaxOnRow(midrow); // number of rows: m
        ///base case TODO: boundary conditions
        if(midrow+1<nrow && midrow-1>=0){
        if(a[midrow][imax] >= a[midrow+1][imax]&& a[midrow][imax] >= a[midrow-1][imax]  ){ //2 tane
           peak= a[midrow][imax];
        }
        }
        if(midrow+1<nrow){
        if(a[midrow][imax] < a[midrow+1][imax] ){
            startcol=midrow;
            return  divideAndConquer2(midrow,startcol, endrow,endcol);
        }
        }
        if(midrow-1>=0){
        if(a[midrow][imax] < a[midrow-1][imax]  ){
            endrow=midrow;
            return divideAndConquer2(startrow,startcol,midrow, endcol);
            
        }
        }
        
        }
        
        return peak;
    }
    
    
    

    /** prints elements of a */
    void printArray(){
        //TODO
        for(int i=0; i<nrow; ++i){
            for(int j=0; j<ncol; ++j){
          System.out.println("["+i+"]"+"["+j+"]"+":"+a[i][j]) ; 
            }
        }
    }
    static void testGreedyAlg(){
         //TODO
         
        System.out.println("peakGreedy:"+greedyAlg());
    }
   static void testDivideAndConq1(int startcol,int endcol){
        //TODO
        System.out.println("peakDivide1:"+divideAndConquer1(startcol,endcol));
    }
    static  void testDivideAndConq2(int startrow, int startcol, int endrow, int endcol){
        //TODO
         System.out.println("peakDivide2:"+divideAndConquer2(startrow,startcol,endrow,endcol));
    }

    
}
