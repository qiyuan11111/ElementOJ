package com.study.spring.samples;

public interface Driver {

	void start();

	default void stop() {

	}
}
