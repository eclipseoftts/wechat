package com.test;

public class MyphontService implements Runnable{

	MyPhone my;
	int number;
	
	public MyphontService(MyPhone my,int num){
		this.my = my;
		this.number = num;
	}

	@Override
	public void run() {
		while(my.getNumber()-number>0){
			int num = my.setNumber(number);
		}
	}

}
