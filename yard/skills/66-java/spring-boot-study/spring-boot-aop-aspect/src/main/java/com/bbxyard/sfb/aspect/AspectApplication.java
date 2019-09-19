package com.bbxyard.sfb.aspect;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AspectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AspectApplication.class, args);
    }

}

/*
org.springframework.context.ApplicationContextException:
    Unable to start web server; nested exception is org.springframework.beans.factory.
    BeanCreationException: Error creating bean with name 'tomcatServletWebServerFactory' defined in class path resource [org/springframework/boot/autoconfigure/web/servlet/ServletWebServerFactoryConfiguration$EmbeddedTomcat.class]:
    BeanPostProcessor before instantiation of bean failed; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration': BeanPostProcessor before instantiation of bean failed; nested exception is java.lang.IllegalArgumentException:
    ProceedingJoinPoint is only supported for around advice
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.onRefresh(ServletWebServerApplicationContext.java:156) ~[spring-boot-2.1.8.RELEASE.jar:2.1.8.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:543) ~[spring-context-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:141) ~[spring-boot-2.1.8.RELEASE.jar:2.1.8.RELEASE]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:744) [spring-boot-2.1.8.RELEASE.jar:2.1.8.RELEASE]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:391) [spring-boot-2.1.8.RELEASE.jar:2.1.8.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:312) [spring-boot-2.1.8.RELEASE.jar:2.1.8.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1215) [spring-boot-2.1.8.RELEASE.jar:2.1.8.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1204) [spring-boot-2.1.8.RELEASE.jar:2.1.8.RELEASE]
	at com.bbxyard.sfb.aspect.AspectApplication.main(AspectApplication.java:11) [classes/:na]
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'tomcatServletWebServerFactory' defined in class path resource [org/springframework/boot/autoconfigure/web/servlet/ServletWebServerFactoryConfiguration$EmbeddedTomcat.class]: BeanPostProcessor before instantiation of bean failed; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration': BeanPostProcessor before instantiation of bean failed; nested exception is java.lang.IllegalArgumentException: ProceedingJoinPoint is only supported for around advice
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:510) ~[spring-beans-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:320) ~[spring-beans-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory$$Lambda$135/204805934.getObject(Unknown Source) ~[na:na]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222) ~[spring-beans-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:318) ~[spring-beans-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:204) ~[spring-beans-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.getWebServerFactory(ServletWebServerApplicationContext.java:210) ~[spring-boot-2.1.8.RELEASE.jar:2.1.8.RELEASE]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.createWebServer(ServletWebServerApplicationContext.java:179) ~[spring-boot-2.1.8.RELEASE.jar:2.1.8.RELEASE]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.onRefresh(ServletWebServerApplicationContext.java:153) ~[spring-boot-2.1.8.RELEASE.jar:2.1.8.RELEASE]
	... 8 common frames omitted
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration': BeanPostProcessor before instantiation of bean failed; nested exception is java.lang.IllegalArgumentException: ProceedingJoinPoint is only supported for around advice
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:510) ~[spring-beans-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:320) ~[spring-beans-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory$$Lambda$135/204805934.getObject(Unknown Source) ~[na:na]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222) ~[spring-beans-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:318) ~[spring-beans-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:199) ~[spring-beans-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:392) ~[spring-beans-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1321) ~[spring-beans-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1160) ~[spring-beans-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:555) ~[spring-beans-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:515) ~[spring-beans-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:320) ~[spring-beans-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory$$Lambda$135/204805934.getObject(Unknown Source) ~[na:na]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222) ~[spring-beans-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:318) ~[spring-beans-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:204) ~[spring-beans-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.aop.framework.autoproxy.BeanFactoryAdvisorRetrievalHelper.findAdvisorBeans(BeanFactoryAdvisorRetrievalHelper.java:91) ~[spring-aop-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator.findCandidateAdvisors(AbstractAdvisorAutoProxyCreator.java:109) ~[spring-aop-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator.findCandidateAdvisors(AnnotationAwareAspectJAutoProxyCreator.java:92) ~[spring-aop-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.aop.aspectj.autoproxy.AspectJAwareAdvisorAutoProxyCreator.shouldSkip(AspectJAwareAdvisorAutoProxyCreator.java:101) ~[spring-aop-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator.postProcessBeforeInstantiation(AbstractAutoProxyCreator.java:251) ~[spring-aop-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsBeforeInstantiation(AbstractAutowireCapableBeanFactory.java:1124) ~[spring-beans-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.resolveBeforeInstantiation(AbstractAutowireCapableBeanFactory.java:1097) ~[spring-beans-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:504) ~[spring-beans-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	... 16 common frames omitted
Caused by: java.lang.IllegalArgumentException: ProceedingJoinPoint is only supported for around advice
Disconnected from the target VM, address: '127.0.0.1:52945', transport: 'socket'
	at org.springframework.aop.aspectj.AbstractAspectJAdvice.maybeBindProceedingJoinPoint(AbstractAspectJAdvice.java:414) ~[spring-aop-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.aop.aspectj.AbstractAspectJAdvice.calculateArgumentBindings(AbstractAspectJAdvice.java:388) ~[spring-aop-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.aop.aspectj.annotation.ReflectiveAspectJAdvisorFactory.getAdvice(ReflectiveAspectJAdvisorFactory.java:294) ~[spring-aop-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.aop.aspectj.annotation.InstantiationModelAwarePointcutAdvisorImpl.instantiateAdvice(InstantiationModelAwarePointcutAdvisorImpl.java:149) ~[spring-aop-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.aop.aspectj.annotation.InstantiationModelAwarePointcutAdvisorImpl.<init>(InstantiationModelAwarePointcutAdvisorImpl.java:113) ~[spring-aop-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.aop.aspectj.annotation.ReflectiveAspectJAdvisorFactory.getAdvisor(ReflectiveAspectJAdvisorFactory.java:198) ~[spring-aop-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.aop.aspectj.annotation.ReflectiveAspectJAdvisorFactory.getAdvisors(ReflectiveAspectJAdvisorFactory.java:126) ~[spring-aop-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.aop.aspectj.annotation.BeanFactoryAspectJAdvisorsBuilder.buildAspectJAdvisors(BeanFactoryAspectJAdvisorsBuilder.java:110) ~[spring-aop-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator.findCandidateAdvisors(AnnotationAwareAspectJAutoProxyCreator.java:95) ~[spring-aop-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.aop.aspectj.autoproxy.AspectJAwareAdvisorAutoProxyCreator.shouldSkip(AspectJAwareAdvisorAutoProxyCreator.java:101) ~[spring-aop-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator.postProcessBeforeInstantiation(AbstractAutoProxyCreator.java:251) ~[spring-aop-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsBeforeInstantiation(AbstractAutowireCapableBeanFactory.java:1124) ~[spring-beans-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.resolveBeforeInstantiation(AbstractAutowireCapableBeanFactory.java:1097) ~[spring-beans-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:504) ~[spring-beans-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	... 39 common frames omitted


Process finished with exit code 1
 */