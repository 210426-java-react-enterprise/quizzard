package com.revature.quizzard.config;

import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.services.UserService;
import com.revature.quizzard.web.servlets.AuthServlet;
import com.revature.quizzard.web.servlets.UserServlet;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactorybean;



import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Properties;

@EnableWebMvc
@Configuration
//@EnableTransactionManagement
@PropertySource("classpath:app.properties")
@ComponentScan("com.revature.quizzard")
public class AppConfig implements WebMvcConfigurer, WebApplicationInitializer {

    @Value("${db.driverClassName}")
    private String dbDriver;

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.username}")
    private String dbUsername;

    @Value("${db.password}")
    private String dbPassword;

    //Server
    @Bean
    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(dbDriver);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("com.revature.quizzard.models");
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        return sessionFactoryBean;
    }


    private Properties hibernateProperties() {
        Properties hibernateProps = new Properties();
        hibernateProps.setProperty(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
        hibernateProps.setProperty(Environment.SHOW_SQL, "true");
        hibernateProps.setProperty(Environment.FORMAT_SQL, "true");
        hibernateProps.setProperty(Environment.HBM2DDL_AUTO, "create");
        hibernateProps.setProperty(Environment.HBM2DDL_IMPORT_FILES, "import.sql");
        return hibernateProps;
    }


    //client
    @Bean
    public ViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".html"); //was jsp
        return viewResolver;
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

//        UserDAO userDao = new UserDAO();
//        UserService userService = new UserService(userDao);

//        AuthServlet authServlet = new AuthServlet(userService);
//        UserServlet userServlet = new UserServlet(userService);

        AnnotationConfigWebApplicationContext container = new AnnotationConfigWebApplicationContext();
        container.register(AppConfig.class);

        servletContext.addListener(new ContextLoaderListener(container));
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(container));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

    }
}
