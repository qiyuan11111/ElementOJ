package com.study.spring.samples;

import com.study.spring.context.annotation.Autowired;
import com.study.spring.context.annotation.Component;

@Component
public class FBean {

	private String name;

	private int age;

	@Autowired
	private ABean aBean;

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

	public ABean getaBean() {
		return aBean;
	}

	public void setaBean(ABean aBean) {
		this.aBean = aBean;
	}

}
