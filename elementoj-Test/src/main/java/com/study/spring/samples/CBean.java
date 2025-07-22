package com.study.spring.samples;

import com.study.spring.context.annotation.Autowired;
import com.study.spring.context.annotation.Component;
import com.study.spring.context.annotation.Value;

@Component("cbean01")
public class CBean {

	@Value("cccccccc")
	private String name;

	@Autowired
	public CBean(@Value("cbean01") String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
