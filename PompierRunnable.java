import java.lang.*;
import java.io.*;
class Alerte implements Runnable{
	static int nbAlerte  = 0;
	String messageAlert;
	int urgence;
	Alerte(String chaine, int urgence){
		this.messageAlert = chaine;
		if((urgence<10) && (urgence>0)){
			this.urgence = urgence;
		}else{
			this.urgence  = 5;
			}
	}
	Alerte(String chaine){
		this.messageAlert  = chaine;
		this.urgence = 5;
	}
	public void run(){
		nbAlerte++;
		//setPriority(urgence);
		for(int i = 0;i<5;i++){
			System.out.println(nbAlerte + "alerte(s) en cours " 
			+ messageAlert + " de niveau " 
			+ Thread.currentThread().getPriority());
			try{
				Thread.currentThread().sleep(1);
			}catch(InterruptedException e){}
		}
	}
}
public class PompierRunnable{
	public static void main(String[] args){
		Alerte a1  = new Alerte("Alerte numero 1");
		Alerte a2  = new Alerte("Alerte numero 2");
		Alerte a3  = new Alerte("Alerte numero 3",Thread.MAX_PRIORITY);
		Thread t1  = new Thread(a1);
		Thread t2  = new Thread(a2);
		t2.setPriority(Thread.MIN_PRIORITY);
		Thread t3  = new Thread(a3);
		t3.setPriority(Thread.MAX_PRIORITY);
		System.out.println("Pompier le 15");
		t1.start();
		t2.start();
		t3.start();
		System.out.println("Pompier le 15");
	}
}