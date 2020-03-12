package web.resume.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
@ComponentScan({
        "web.resume.services.impl",
        "web.resume.controllers",
        "web.resume.filters",
        "web.resume.listeners"
})
public class ServiceConfig {
    //Properties init. Static. Стандартный. Прыгает из проекта в проект.
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() throws IOException {
        PropertySourcesPlaceholderConfigurer conf = new PropertySourcesPlaceholderConfigurer();
        conf.setLocations(getResources());
        return conf;
    }

    private static Resource[] getResources() {
        return new Resource[]{new ClassPathResource("application.properties")};
    }
}
