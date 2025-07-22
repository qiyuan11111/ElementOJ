package com.study.spring.samples;

import java.lang.reflect.Constructor;

public class Test {

	public Test(String a, int b, char... c) {
		System.out.println(a + b + c);
	}

	public static void main(String[] args) throws Exception {
		Class<?> c = Test.class;
		Constructor ct = c.getConstructor(new Class<?>[] { String.class, int.class, char[].class });

		System.out.println(ct);

		Object instance = ct.newInstance(new Object[] { "aaaa", 4, new char[] { 'c' } });

	}

	public void test1(){
		// Before 增强
		try{
			// 执行被增强的方法
			String msg = this.test2();
			// AfterReturn 增强
		}catch (Exception e){
			// 异常处理增强
		}finally {
			// After增强
		}
	}

	public String test2(){
		return "Hello";
	}
}
