package com.study.spring.context;

import com.study.spring.beans.BeanDefinitionRegistry;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/*
v1: 实现扫描包获得Class
*/
public class ClassPathBeanDefinitionScanner_v1 {

	private static Log logger = LogFactory.getLog(ClassPathBeanDefinitionScanner_v1.class);

	private BeanDefinitionRegistry registry;

	public ClassPathBeanDefinitionScanner_v1(BeanDefinitionRegistry registry) {
		super();
		this.registry = registry;
	}

	public void scan(String... basePackages) throws Throwable {
		if (basePackages != null && basePackages.length > 0) {
			for (String p : basePackages) {
				/*
				 1 递归扫描包目录下的.class文件
				 2 组合包路径+class文件名 得到全限定类名
				 3 ClassLoad.load("类名") 得到 Class 对象
				 4 解析Class上的注解，获得Bean定义信息，注册Bean定义
				 */

				//1 递归扫描包目录下的.class文件
				Set<File> classFiles = this.doScan(p);
				//2 得到Class对象，并解析注解、注册Bean定义
				this.readAndRegisterBeanDefintion(classFiles);
			}
		}
	}

	private void readAndRegisterBeanDefintion(Set<File> classFiles) {

	}


	private Set<File> doScan(String basePackage) throws IOException {
		// 扫描包下的类
		// 将包名转为路径名
		String basePackagePath = "/" + StringUtils.replace(basePackage, ".", "/");
		// 得到包对应的目录
		File rootDir = new File(this.getClass().getResource(basePackagePath).getPath());

		// 存放找到的类文件的集合
		Set<File> scanedClassFiles = new HashSet<>();
		// 调用doRetrieveMatchingFiles来扫描class文件
		this.doRetrieveClassFiles(rootDir, scanedClassFiles);
		return scanedClassFiles;
	}


	/**
	 * 递归找指定目录下的所有类,规则：.class结尾。
	 *
	 * @param dir
	 * @param result
	 * @throws IOException
	 */
	protected void doRetrieveClassFiles(File dir, Set<File> result) throws IOException {
		for (File file : dir.listFiles()) {
			if (file.isDirectory() && file.canRead()) {
				doRetrieveClassFiles(file, result);

			}
			if (file.getName().endsWith(".class")) {
				result.add(file);
			}
		}
	}

	public BeanDefinitionRegistry getRegistry() {
		return registry;
	}

	public void setRegistry(BeanDefinitionRegistry registry) {
		this.registry = registry;
	}
}
