import java.lang.*;
class MyData{
	String  name;
	String adress;
	synchronized public void update(String name, String adress, int n){
		this.name = name;
		this.adress = adress;
		if(n>=2)
			try{
				Thread.currentThread().sleep(100);
			}catch(InterruptedException e){}
	}
}
class UpdateData extends Thread{
	static MyData c;
	static int i = 0;
	String myname;
	String myadress;
	private int me;
	UpdateData(String n, String a, int m){
		myname = n;
		myadress = a;
		me = m;
	}
	public void run(){
		c.update(myname, myadress, me);
		System.out.println("Les données de "+ me + " partagées :"+c.name+" habite à "+c.adress);
	}
	public static void main(String[] args){
		Thread t1 = new UpdateData("Khaoula", "Villeurbanne", 1);
		Thread t2 = new UpdateData("Julie", "Paris", 2);
		c = new MyData();
		t1.start();
		t2.start();
		try{
			t1.join();
			t2.join();
		}catch(InterruptedException e){}
		System.out.println("Mes données : "+ c.name+" à "+c.adress);
	}
}