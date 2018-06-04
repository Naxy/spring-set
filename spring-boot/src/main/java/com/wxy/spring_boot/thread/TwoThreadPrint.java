package com.wxy.spring_boot.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TwoThreadPrint {
	ReentrantLock lock = new ReentrantLock();
	Condition aCondition = lock.newCondition();
	Condition bCondition = lock.newCondition();
	public static void main(String[] args) {
		TwoThreadPrint twoThreadPrint = new TwoThreadPrint();
		A a = twoThreadPrint.new A();
		B b = twoThreadPrint.new B();
		a.start();
		b.start();
	}
	class A extends Thread{

		@Override
		public void run() {
			while(true){
				lock.lock();
				System.out.println("A");
				bCondition.signal();
				try {
					aCondition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lock.unlock();
			}
		}
	}
	class B extends Thread{

		@Override
		public void run() {
			while(true){
				lock.lock();
				System.out.println("B");
				aCondition.signal();
				try {
					bCondition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lock.unlock();
			}
		}
		
	}
}
