package com.study.spring.samples;

import com.study.spring.context.annotation.Bean;
import com.study.spring.context.annotation.Component;
import com.study.spring.context.annotation.Value;

@Component
public class ABeanFactory {

	@Bean
	public static ABean getABean(@Value("aaaa") String name, CBean cb) {
		return new ABean(name, cb);
	}

	@Bean
	public ABean getABean2(@Value("bbbb") String name, CBean cb) {
		return new ABean(name, cb);
	}
}
