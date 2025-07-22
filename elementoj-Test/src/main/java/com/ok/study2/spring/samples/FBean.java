package com.study2.spring.samples;

import com.study.spring.samples.ABean;

public class FBean {

	private String name;

	private int age;

	private com.study.spring.samples.ABean aBean;

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

	public com.study.spring.samples.ABean getaBean() {
		return aBean;
	}

	public void setaBean(ABean aBean) {
		this.aBean = aBean;
	}

}
