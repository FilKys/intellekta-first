package config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"ru.api.jpa"})
@ComponentScan(basePackages = {"ru.api.service.impl"})
@EntityScan(basePackages = {"ru.api.entity"})
public class TestConfig {

}
