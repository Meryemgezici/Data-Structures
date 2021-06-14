/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package crazymarket.java;

/**
 *
 * @author HP Omen
 */
public interface MyQueue<T> extends Iterable<T>{
	
	/**kuruktaki toplam eleman sayisi*/
	int size();
	boolean isEmpty();
	/**kuyrugun sonuna item ekler*/
	boolean enqueue(T item);
	
	/** kuyrugun basindan eleman cikarir*/
	T dequeuNext();
	/** tekerleme metnini kullanarak bir sonraki elemani secer*/
	T dequeuWithCounting(String tekerleme);

}
