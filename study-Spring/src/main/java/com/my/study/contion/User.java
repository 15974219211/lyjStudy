package com.my.study.contion;

import lombok.Data;

@Data
public class User {
	private String name;

	public User(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	private Integer age;
}
