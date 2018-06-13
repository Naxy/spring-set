package com.wxy.spring_boot.user.domain;

public enum UserSexEnum {
	MAN(0),
	WOMAN(1);
	private int value;
	UserSexEnum(int e){
		this.value = e;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
}
