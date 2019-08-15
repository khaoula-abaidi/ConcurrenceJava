import java.lang.*;
import java.io.*;
class Alerte extends Thread{
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
		setPriority(urgence);
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
public class Pompier{
	public static void main(String[] args){
		Alerte a1  = new Alerte("Alerte numero 1");
		Alerte a2  = new Alerte("Alerte numero 2",Thread.MIN_PRIORITY);
		Alerte a3  = new Alerte("Alerte numero 3",Thread.MAX_PRIORITY);
		System.out.println("Pompier le 15");
		a1.start();
		a2.start();
		a3.start();
		System.out.println("Pompier le 15");
	}
}