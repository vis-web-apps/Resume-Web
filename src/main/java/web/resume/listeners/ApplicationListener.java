package web.resume.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Component
public class ApplicationListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationListener.class);

    //Считывает свойство и записывает в переменную
    @Value("${application.production}")
    private boolean isProduction;

    public void contextInitialized(ServletContextEvent sce) {
        //Переменная записывается в атрибут контекста. Обращаемся через $ во всем приложении
        sce.getServletContext().setAttribute("production", isProduction);
        LOGGER.info("Application started. Production is '{}'", isProduction);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.info("Application destroyed");
    }

}