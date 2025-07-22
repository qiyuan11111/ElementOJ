package com.study2.spring.samples;

import com.study.spring.samples.ABean;

public class ABeanFactory {

	public static com.study.spring.samples.ABean getABean(String name, CBean cb) {
		return new com.study.spring.samples.ABean(name, cb);
	}

	public com.study.spring.samples.ABean getABean2(String name, CBean cb) {
		return new ABean(name, cb);
	}
}
