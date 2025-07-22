package com.study.spring.context;

import com.study.spring.beans.*;
import com.study.spring.context.annotation.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
v1: 实现扫描包获得Class
v2：实现注解解析、bean定义注册
*/
public class ClassPathBeanDefinitionScanner_v2 {

    private static Log logger = LogFactory.getLog(ClassPathBeanDefinitionScanner_v2.class);

    private BeanDefinitionRegistry registry;

    //注意：当在开发时执行单元测试，测试类是另一个类目录，这里可能会导致类名截取不对。部署环境时没问题。
    private int classPathAbsLength = new File(ClassPathBeanDefinitionScanner_v2.class.getResource("/").getPath()).getAbsolutePath().length();

    public ClassPathBeanDefinitionScanner_v2(BeanDefinitionRegistry registry) {
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

    private void readAndRegisterBeanDefintion(Set<File> classFiles) throws BeanDefinitionRegistException {
        for (File classFile : classFiles) {
            String className = getClassNameFromFile(classFile);
            try {
                //加载类
                Class<?> clazz = this.getClass().getClassLoader().loadClass(className);
                Component component = clazz.getAnnotation(Component.class);
                if (component != null) {// 标注了@Component注解
                    String beanName = component.value();
                    if (StringUtils.isBlank(beanName)) {
                        beanName = this.generateBeanName(clazz);
                    }
                    GenericBeanDefinition bd = new GenericBeanDefinition();
                    bd.setBeanClass(clazz);

                    //处理Scope
                    Scope scope = clazz.getAnnotation(Scope.class);
                    if (scope != null) {
                        bd.setScope(scope.value());
                    }
                    //处理primary
                    Primary primary = clazz.getAnnotation(Primary.class);
                    if (primary != null) {
                        bd.setPrimary(true);
                    }

                    // 处理构造方法，在构造方法上找@Autowired注解，如有，将这个构造方法set到bd;
                    this.handleConstructor(clazz, bd);

                    //处理方法上的注解（找出初始化、销毁、工厂方法）
                    this.handleMethod(clazz, bd, beanName);

                    // 处理属性依赖
                    this.handlePropertyDi(clazz, bd);

                    // 注册bean定义
                    this.registry.registerBeanDefinition(beanName, bd);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private String generateBeanName(Class<?> clazz) {
        //应用名称生成规则生成beanName:  类名首字母小写
        String className = clazz.getName();
        return className.substring(0, 1).toLowerCase() + className.substring(1);
    }

    private void handleConstructor(Class<?> clazz, GenericBeanDefinition bd) {
        // 获得所有构造方法，在构造方法上找@Autowired注解，如有，将这个构造方法set到bd;
        Constructor<?>[] cs = clazz.getConstructors();
        if (cs != null && cs.length > 0) {
            for (Constructor<?> c : cs) {
                if (c.getAnnotation(Autowired.class) != null) {
                    bd.setConstructor(c);
                    //构造参数依赖处理
                    bd.setConstructorArgumentValues(this.handleMethodParamters(c.getParameters()));
                    break;
                }
            }
        }
    }

    //构造参数依赖处理方法
    private List<Object> handleMethodParamters(Parameter[] ps) {
        //遍历获取参数上的注解，及创建构造参数依赖
        List<Object> argValues = new ArrayList<>();
        for (Parameter parameter : ps) {
            //找@Value注解
            Value v = parameter.getAnnotation(Value.class);
            if (v != null) {
                argValues.add(v.value());
                continue;
            }
            //找@Qualifier
            Qualifier q = parameter.getAnnotation(Qualifier.class);
            if (q != null) {
                argValues.add(new BeanReference(q.value()));
            } else {
                argValues.add(new BeanReference(parameter.getType()));
            }
        }
        return argValues;
    }

    private void handleMethod(Class<?> clazz, GenericBeanDefinition bd, String beanName) throws BeanDefinitionRegistException {
        //遍历方法找初始化、销毁、工厂方法注解
        for (Method method : clazz.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                bd.setInitMethodName(method.getName());
            } else if (method.isAnnotationPresent(PreDestroy.class)) {
                bd.setDestroyMethodName(method.getName());
            } else if (method.isAnnotationPresent(Bean.class)) {
                this.handlerFactoryMethod(method, clazz, beanName);
            }
        }
    }

    private void handlerFactoryMethod(Method method, Class<?> clazz, String beanName) throws BeanDefinitionRegistException {
        GenericBeanDefinition bd = new GenericBeanDefinition();
        //静态工厂方法
        if (Modifier.isStatic(method.getModifiers())) {
            bd.setBeanClass(clazz);
        } else { //成员工厂方法，指定工厂Bean名
            bd.setFactoryBeanName(beanName);
        }

        bd.setFactoryMethod(method);
        bd.setFactoryMethodName(method.getName());

        //处理Scope
        Scope scope = method.getAnnotation(Scope.class);
        if (scope != null) {
            bd.setScope(scope.value());
        }
        //处理primary
        if (method.isAnnotationPresent(Primary.class)) {
            bd.setPrimary(true);
        }

        //处理@Bean注解
        Bean bean = method.getAnnotation(Bean.class);
        //BeanName的得来
        String xbeanName = bean.name();
        if (StringUtils.isBlank(xbeanName)) {
            xbeanName = method.getName();
        }
        //初始化方法、销毁方法设置
        if (StringUtils.isNotBlank(bean.initMethod())) {
            bd.setInitMethodName(bean.initMethod());
        }
        if (StringUtils.isNotBlank(bean.destroyMethod())) {
            bd.setDestroyMethodName(bean.destroyMethod());
        }

        //参数依赖处理
        bd.setConstructorArgumentValues(this.handleMethodParamters(method.getParameters()));
        // 注册Bean定义
        this.registry.registerBeanDefinition(xbeanName, bd);
    }

    private String getClassNameFromFile(File file) {
        String absPath = file.getAbsolutePath();
        String name = absPath.substring(classPathAbsLength + 1, absPath.indexOf('.'));
        return StringUtils.replace(name, File.separator, ".");
    }

    private void handlePropertyDi(Class<?> clazz, GenericBeanDefinition bd) {
        List<PropertyValue> propertyValues = new ArrayList<>();
        bd.setPropertyValues(propertyValues);
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                BeanReference beanReference = null;
                Qualifier qualifier = field.getAnnotation(Qualifier.class);
                if (qualifier != null) {
                    beanReference = new BeanReference(qualifier.value());
                } else {
                    beanReference = new BeanReference(field.getType());
                }
                propertyValues.add(new PropertyValue(field.getName(), beanReference));
            }
        }
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
