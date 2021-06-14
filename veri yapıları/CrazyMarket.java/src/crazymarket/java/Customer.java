/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package crazymarket.java;

import java.util.Iterator;

/**
 *
 * @author HP Omen
 */
public class Customer {
	//datafield tiplerini degistirebilirsiniz
        String item;//musterinin ismi için
        int i;//kacıncı musteri
	int arrivalTime; //musteri gelis zamani-
	//bekleme zamanini hesaplamada kullanabilirsiniz
	int removalTime;//musterilerin ayrılma zamanı
        Customer(String item,int i){
            this.item=item;
            this.i=i;
            
        }
        Customer(){
            item=" ";
            
        }
       public String toString() { 
        return String.format(item+ " :" +i); 
    } 

    
        
}