import java.lang.*;
import java.io.*;
import java.util.*;
class Calcul extends Thread{
	int tablo[];
	int start;
	int end;
	int best;
 Calcul(int[] t, int s, int e){
	 this.tablo = t;
	 this.start = e;
	 }
	 public int getResutl(){
		 return best;
	 }
	 public void run(){
		 best = tablo[start];
		for(int i = start; i < end; i++){
			if(tablo[i]>best){
				best = tablo[i];
				}
		}
	 }
}
public class Parallel{
	public static void main(String[] args){
		int tabloEntier[] = new int[5000000];
		Random rand  = new Random();
		for(int i = 0; i<tabloEntier.length;i++){
			tabloEntier[i] = rand.nextInt();
		}
		// ----- Calcul Séquentiel
		long debut = System.currentTimeMillis();
		int best = tabloEntier[0];
		for(int i = 0; i < tabloEntier.length;i++){
			if(tabloEntier[i]>best){
				best = tabloEntier[i];
			}
		}
		long fin = System.currentTimeMillis();
		System.out.println("Plus grand "+best+" en "+(fin - debut));
		// ----- Calcul en parallèle
		Calcul c1 = new Calcul(tabloEntier, 0, tabloEntier.length/2);
		Calcul c2 = new Calcul(tabloEntier, tabloEntier.length/2, tabloEntier.length);
		debut = System.currentTimeMillis();
		c1.start();
		c2.start();
		try{
			c1.join();
			c2.join();
		}catch(InterruptedException e){}
		int res1 = c1.getResutl();
		int res2 = c2.getResutl();
		int res  = (res1< res2) ? res2 : res1;
		fin = System.currentTimeMillis();
		System.out.println("Plus grand "+best+" en "+(fin - debut));
	}
}
	