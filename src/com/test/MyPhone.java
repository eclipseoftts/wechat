package com.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyPhone {

	private int number = 100;
	private boolean falg = false;
	
	Lock lock = new ReentrantLock();
	Condition newCondition = lock.newCondition();

	public MyPhone() {
	}

	
	public int setNumber(int num){
		lock.lock();
		if(this.number-num>0){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.number = this.number-num;
			System.out.println(Thread.currentThread().getName()+"================="+this.number);
		}
		lock.unlock();
		return this.number;
	}
	
	public int getNumber(){
		return this.number;
	}
	
	
}
