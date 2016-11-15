package com.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Person {

	private String name;
	
	private String url;
	boolean falg = false;
	
	Lock lock = new ReentrantLock();
	Condition con = lock.newCondition();
	
	public void setPerson(){
		lock.lock();
		if(!falg){
			this.name = "tang";
			this.url = "www.baidu.com";
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("开始生产-------------------");
			falg = true;
			con.signalAll();
		}else{
			try {
				System.out.println("开始生产-------------------等待");
				con.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		lock.unlock();
	}
	
	public void getPerson(){
		lock.lock();
		if(falg){
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"----"+this.name+"-----消费------"+this.url);
			falg = false;
			con.signalAll();
		}else{
			try {
				System.out.println(Thread.currentThread().getName()+"----消费-------------------等待");
				con.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		lock.unlock();
	}
	
	
	
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
