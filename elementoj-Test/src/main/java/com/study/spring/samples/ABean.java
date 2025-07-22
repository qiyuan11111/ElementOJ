package com.study.spring.samples;

import com.study.spring.context.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("aBean")
@Primary
public class ABean {

	private String name;

	private CBean cb;

	@Autowired  //标识使用的构造方法
	public ABean(@Value("大树")String name, @Qualifier("cbean01")  CBean cb) {
		super();
		this.name = name;
		this.cb = cb;
		System.out.println("调用了含有CBean参数的构造方法");
	}

	public ABean(String name, CCBean cb) {
		super();
		this.name = name;
		this.cb = cb;
		System.out.println("调用了含有CCBean参数的构造方法");
	}

	public ABean(CBean cb) {
		super();
		this.cb = cb;
	}

	public void doSomthing() {
		System.out.println(System.currentTimeMillis() + " " + this.name + " cb.name=" + this.cb.getName());
	}

	public void sayHello() {
		System.out.println("Hello,大哥好！");
	}

	@PostConstruct
	public void init() {
		System.out.println("ABean.init() 执行了");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("ABean.destroy() 执行了");
	}
}
