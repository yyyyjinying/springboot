package com.bjsxt.pojo;

import java.util.List;

public class People {
	private int id;
	private String name;
	private int age;
//	private List<Student> list;

//	public List<Student> getList() {
//		return list;
//	}
//
//	public void setList(List<Student> list) {
//		this.list = list;
//	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "People{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
//				", list=" + list +
				'}';
	}
}
