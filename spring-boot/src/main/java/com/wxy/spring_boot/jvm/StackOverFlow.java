package com.wxy.spring_boot.jvm;
/**
 * VM Args: -Xss128k -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=E:\dump\StackOverFlow
 * @author wangxiyang
 *
 */
public class StackOverFlow {
	private int stackLength = 1;
	public void stackLeak(){
		stackLength++;
		stackLeak();
	}
	public static void main(String[] args) {
		StackOverFlow sof = new StackOverFlow();
		try{
			sof.stackLeak();
		}catch(Throwable e){
			System.out.println("stackLength:"+sof.stackLength);
			throw e;
		}
	}
}
