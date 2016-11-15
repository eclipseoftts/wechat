package com.test;

class tests implements Runnable {
	private Person p;
	private int i;
	tests(Person p,int i){
		this.p = p;
		this.i = i;
	}
	
	public void run() {
		while(i>0){
			p.setPerson();
			System.out.println(Thread.currentThread().getName()+"-------------生产者-----------"+i);
			i--;
		}
	}
}

class test2 implements Runnable {
	private Person p;
	private int i ;
	test2(Person p,int i){
		this.p = p;
		this.i = i ;
	}
	
	public void run() {
		while(i>0){
			p.getPerson();
			//System.out.println(Thread.currentThread().getName()+"-------------消费者-----------"+i);
			i--;
		}
	}
}


public class OpenPerson {
	
	public static void main(String[] args) {
		int i = 1000;
		Person p = new Person();
		tests t = new tests(p,i);
		
		test2 t2 = new test2(p,i);
		
		 new Thread(t).start();
		 new Thread(t2).start();
		 new Thread(t2).start();
		 new Thread(t2).start();
		 new Thread(t2).start();
	}
}
