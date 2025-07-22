package com.study.spring.samples;

public class DBean {

	static {
		System.out.println("sssssss");
	}

	private EBean ebean;

	public DBean(EBean ebean) {
		super();
		this.ebean = ebean;
	}
}
