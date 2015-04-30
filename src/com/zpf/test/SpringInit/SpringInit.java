package com.zpf.test.SpringInit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringInit implements ServletContextListener {
    
    private static WebApplicationContext springContext;
  
 
    
    public SpringInit() {
        super();
    }
    
    public void contextInitialized(ServletContextEvent event) {
        springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
 
        try{
        	
    		String push_cert_file = springContext.getServletContext().getInitParameter("push_cert_file");
    		if(push_cert_file != null){
    			Constants.PUSH_Path = push_cert_file;
    		}
    		
    		String apple_apns = springContext.getServletContext().getInitParameter("apple_apns");
    		if(apple_apns != null){
    			Constants.PUSH_Host = apple_apns;
    		}
    		
        	
		}catch(Exception e){
			e.printStackTrace();
		}
 
    }
    

    public void contextDestroyed(ServletContextEvent event) {
    }
    
    public static ApplicationContext getApplicationContext() {
        return springContext;
    }

}
