package com.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class piao{
	private int num = 1000;
	Lock lock = new ReentrantLock();
	Condition con = lock.newCondition();
	
	public void maiPiao(int i){
		lock.lock();
		if(this.num - i>=0){
			try {
				//Thread.sleep(500);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"--=======---"+this.num);
			this.num = this.num-i;
		}
		lock.unlock();
	}
}

class mai implements Runnable{
	private piao p ;
	int i;
	mai(piao p,int i){
		this.p = p;
		this.i = i;
	}
	
	@Override
	public void run() {
		while(true){
			p.maiPiao(i);
		}
		
	}
	
}


public class Student {
	
	public static void main(String[] args) {
		piao p = new piao();
		mai m = new mai(p,1);
		mai m2 = new mai(p,1);
		mai m3 = new mai(p,1);
		new Thread(m).start();
		new Thread(m2).start();
		new Thread(m3).start();
		new Thread(m).start();
		new Thread(m2).start();
		new Thread(m3).start();
		new Thread(m).start();
	}
}
