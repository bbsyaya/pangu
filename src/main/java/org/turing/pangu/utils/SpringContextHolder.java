package org.turing.pangu.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * Spring context 的支持类
 * 
 * @author Freud
 */
public class SpringContextHolder implements ApplicationContextAware
{
    
    private static ApplicationContext applicationContext;
    
    public void setApplicationContext(ApplicationContext applicationContext)
    {
        SpringContextHolder.applicationContext = applicationContext;
    }
    
    /**
     * 得到Spring 上下文环境
     * 
     * @return
     */
    public static ApplicationContext getApplicationContext()
    {
        checkApplicationContext();
        return applicationContext;
    }
    
    /**
     * 通过Spring Bean name 得到Bean
     * 
     * @param name bean 上下文定义名称
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name)
    {
        checkApplicationContext();
        return (T)applicationContext.getBean(name);
    }
    
    /**
     * 通过类型得到Bean
     * 
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz)
    {
        checkApplicationContext();
        return applicationContext.getBean(clazz);
    }
    
    private static void checkApplicationContext()
    {
        if (applicationContext == null)
        {
            throw new IllegalStateException("applicaitonContext未注入,请在application-context.xml中定义SpringContextHolder");
        }
    }
    
}