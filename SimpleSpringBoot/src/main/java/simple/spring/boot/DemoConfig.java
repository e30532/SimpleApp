package simple.spring.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfig {
    
    @Bean
    public DemoBean getDemoBean() {
        return new DemoBean();
    }
}

