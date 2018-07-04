package com.wxy.spring_boot.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool extends ThreadPoolExecutor{
	public ThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		System.out.println("beforeExecute" + r);
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		super.afterExecute(r, t);
		System.out.println("afterExecute" + r);
	}
	
	public static void main(String[] args) {
		ExecutorService pool = new ThreadPool(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors(),
	            0L, TimeUnit.MILLISECONDS,
	            new LinkedBlockingQueue<Runnable>());
		for(int i=0;i<3;i++){
			pool.submit(new Runnable(){

				@Override
				public void run() {
					System.out.println("execute" + this);
			
				}});
		}
	}
}
