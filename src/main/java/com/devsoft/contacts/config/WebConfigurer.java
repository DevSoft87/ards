package com.devsoft.contacts.config;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;

/**
 * Created by Sergey on 30.04.2016.
 */

public class WebConfigurer implements ServletContextListener {
    
    public ApplicationContext context;

    public void setContext(ApplicationContext context) {
        this.context = context;
    }
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        ServletContext servletContext = sce.getServletContext();

        ApplicationContext rootContext;

        if (context == null) {
            AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
            ctx.register(ApplicationConfiguration.class);
            ctx.setServletContext(servletContext);
            ctx.refresh();
            rootContext = ctx;
        } else {
            rootContext = context;
        }
        
        servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, rootContext);
        initSpring(servletContext, rootContext);
    }
    
    /**ф
     * Initializes Spring and Spring MVC.
     */
    private ServletRegistration.Dynamic initSpring(ServletContext servletContext,
                                                   ApplicationContext rootContext) {

        AnnotationConfigWebApplicationContext dispatcherServletConfiguration = new AnnotationConfigWebApplicationContext();
        dispatcherServletConfiguration.setParent(rootContext);
        dispatcherServletConfiguration.register(DispatcherServletConfiguration.class);

        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherServletConfiguration));
        dispatcherServlet.addMapping("/api/*");
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.setAsyncSupported(true);

        return dispatcherServlet;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
        AnnotationConfigWebApplicationContext gwac = (AnnotationConfigWebApplicationContext) ac;
        gwac.close();
    }
}
