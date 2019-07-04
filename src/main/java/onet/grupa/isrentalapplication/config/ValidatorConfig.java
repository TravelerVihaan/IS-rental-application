package onet.grupa.isrentalapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

@Configuration
@ComponentScan
public class ValidatorConfig {

    @Bean
    public Validator validator(){
        return new LocalValidatorFactoryBean();
    }

}
