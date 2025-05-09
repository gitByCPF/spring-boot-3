## 这是复习过程中遇到的问题及解决方案

- Copilot要引入插件，还对IDEA版本有要求

- maven用自己安装的版本，配置环境变量，IDEA引用自安装maven，使用阿里镜像，设置仓库地址

- 不建议字段注入，建议构造器注入，安全好测试

- alt+F1可以打开一个文件在项目中的位置

- 类的描述信息
  - @author 作者 符合java规范 值可以用${USER}，取用的是系统当前用户名
  - @since 版本号 符合java规范 推荐写固定值 例如17
  - @date 日期 不符合java规范 推荐使用Date ${DATE}替代
  - @description 描述信息 不符合java规范 推荐使用Description TODO 描述

- springboot配置parent只是为了引入spring-boot-dependencies的版本管理，如果要使用相关类，还需要在dependencies中引入spring-boot-starter

- springboot官网各种版本右上角的版本符号： 
  - GA表示general availability，稳定版本
  - MR表示milestone release，里程碑版本
  - RC表示release candidate，候选版本
  - PRE表示预览版本
  - SNAPSHOT表示快照版本

- IDEA创建java项目时，Catalog实际上指选择的项目模板类型：maven、gradle、javafx等

- maven要在mirror标签内配置阿里云http协议镜像仓库，默认的maven-default-http-blocker要注释掉

- Use settings from .mvn/maven.config勾选意味着如果项目中有该目录该文件，以该配置为主

- Vulnerability found in dependencies 依赖中出现了漏洞

- jackson-databind低于2.15.2版本有风险，实测spring-boot-starter 3.4.5版本无该漏洞

- Maven Helper插件可以把依赖以图的形式展示，ctrl+alt+shift+u

- IDEA File->New Projects Setup->settings for new projects中的视图是缩略版本，完整版可以close project后在欢迎页查看

- 依赖管理
  - 场景启动器
  - parent的父项目是spring-boot-dependencies，它是版本仲裁中心，规定了所有依赖的版本
  - 如果自定义版本号，利用maven的就近原则，在当前项目申明的版本号会覆盖父项目的版本号
    - 直接在properties中配置版本号
    - 在dependencies中配置版本号，这是直接写死的方式

- 自动配置机制
  - 默认的包扫描规则：
    - @SpringBootApplication注解的类所在包及其子包
      - 其中的属性scanBasePackages可以自定义包扫描路径
    - @ComponentScan注解的类所在包及其子包
  - 配置默认值
    - 配置文件中的某个配置与某个类的对象属性值是一一绑定的，这个类叫属性类
    - 我们也可以自己定义配置类，@ConfigurationProperties注解的类
  - 按需加载自动装配:
    - 导入场景启动器，触发spring-boot-autoconfigure模块的自动装配，容器就有相关场景的功能对象

- 常用注解
  - 组件注册
    - @Component
    - @Service
    - @Repository
    - @Controller
    - ioc.xml中配置
    - @Configuration(@SpringBootConfiguration)+@Bean
    - 默认单实例 scope="singleton"； scope="prototype"是变为多实例的
    - @Import直接导入第三方组件，需要在配置类中使用，容器中对象名就是全类名
  - 条件注解
    - @ConditionalOnClass：类存在
    - @ConditionalOnMissingClass：类不存在
    - @ConditionalOnBean：容器中有某个对象
    - @ConditionalOnMissingBean：容器中没有某个对象
    - @ConditionalOnProperty：配置文件中有某个属性
    - @ConditionalOnResource：资源文件存在
    - @ConditionalOnJava：java版本符合要求
  - 属性绑定
    - @ConfigurationProperties：配置文件中的属性与类的属性绑定，配置类仍需要注册到容器中
    - @EnableConfigurationProperties(SomeProps.class):
      - 1.单一职责原则
      - 2.配置类的位置随意，不需要在主类的包扫描路径下
      - 3.更解耦，方便轻量化测试
      - 导入第三方jar包时，如果jar包中有@ConfigurationProperties注解的类，只能使用这种方式

- 自动装配的源码级逻辑(SpringBoot 3.4.5为例)

  - ```java
    @SpringBootApplication -> @EnableAutoConfiguration -> @Import(AutoConfigurationImportSelector.class)
    ```

  - ```java
    // AutoConfigurationImportSelector类中的方法
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        if (!isEnabled(annotationMetadata)) {
            return NO_IMPORTS;
        }
        AutoConfigurationEntry autoConfigurationEntry = getAutoConfigurationEntry(annotationMetadata);
        return StringUtils.toStringArray(autoConfigurationEntry.getConfigurations());
    }
    ```
  
    - [ ] 需要说明的是，为什么该方法会被调用？（暂时搁置）
  
    - ```java
      // ConfigurationClassParser#processImports
      private void processImports(ConfigurationClass configClass, SourceClass currentSourceClass,
             Collection<SourceClass> importCandidates, Predicate<String> filter, boolean checkForCircularImports) {
      	// ...
          for (SourceClass candidate : importCandidates) {
              String[] importClassNames = selector.selectImports(currentSourceClass.getMetadata());
              Collection<SourceClass> importSourceClasses = asSourceClasses(importClassNames, filter);
              processImports(configClass, currentSourceClass, importSourceClasses, filter, false);
          }
          // ...
      }
      ```
  
  - ```java
    protected AutoConfigurationEntry getAutoConfigurationEntry(AnnotationMetadata annotationMetadata) {
        if (!isEnabled(annotationMetadata)) {
           return EMPTY_ENTRY;
        }
        // ...
        List<String> configurations = getCandidateConfigurations(annotationMetadata, attributes);
        // ...
    }
    ```
  
  - ```java
    // 即使有自动装配，也可以通过配置项阻止这一切
    protected boolean isEnabled(AnnotationMetadata metadata) {
        if (getClass() == AutoConfigurationImportSelector.class) {
           return getEnvironment().getProperty(EnableAutoConfiguration.ENABLED_OVERRIDE_PROPERTY, Boolean.class, true);
        }
        return true;
    }
    ```
  
  - ```java
    protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
        ImportCandidates importCandidates = ImportCandidates.load(this.autoConfigurationAnnotation,
              getBeanClassLoader());
        List<String> configurations = importCandidates.getCandidates();
        // ...
    }
    ```
  
  - ```java
    public static ImportCandidates load(Class<?> annotation, ClassLoader classLoader) {
        Assert.notNull(annotation, "'annotation' must not be null");
        // 这里得到的是AppClassLoader
        ClassLoader classLoaderToUse = decideClassloader(classLoader);
        /**
        private static final String LOCATION = "META-INF/spring/%s.imports";
        annotation.getName() = "org.springframework.boot.autoconfigure.AutoConfiguration"
        **/
        String location = String.format(LOCATION, annotation.getName());
        // 拼起来就是META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
        Enumeration<URL> urls = findUrlsInClasspath(classLoaderToUse, location);
        List<String> importCandidates = new ArrayList<>();
        while (urls.hasMoreElements()) {
           URL url = urls.nextElement();
           importCandidates.addAll(readCandidateConfigurations(url));
        }
        return new ImportCandidates(importCandidates);
    }
    ```
  
  - ```java
    // 多个ClassLoader之间的继承关系
    private static class AppClassLoader extends BuiltinClassLoader
    public class BuiltinClassLoader extends SecureClassLoader
    public class SecureClassLoader extends ClassLoader
    public abstract class ClassLoader
    ```
  
  - ```java
    private static Enumeration<URL> findUrlsInClasspath(ClassLoader classLoader, String location) {
        try {
            // ClassLoader的继承者都没有重写该方法，调用的还是ClassLoader类中写的方法
           return classLoader.getResources(location);
        }
        catch (IOException ex) {
           throw new IllegalArgumentException("Failed to load configurations from location [" + location + "]", ex);
        }
    }
    ```
  
  - ```java
    // ClassLoader.getResources
    public Enumeration<URL> getResources(String name) throws IOException {
        Objects.requireNonNull(name);
        @SuppressWarnings("unchecked")
        Enumeration<URL>[] tmp = (Enumeration<URL>[]) new Enumeration<?>[2];
        // 这里有点类似于类加载的双亲委派机制
        if (parent != null) {
            tmp[0] = parent.getResources(name);
        } else {
            tmp[0] = BootLoader.findResources(name);
        }
        tmp[1] = findResources(name);
    
        return new CompoundEnumeration<>(tmp);
    }
    ```

- [ ] 再往下深究比较复杂，暂时搁置

















































