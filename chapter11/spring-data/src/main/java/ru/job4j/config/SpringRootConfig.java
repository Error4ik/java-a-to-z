package ru.job4j.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Configuration class.
 *
 * @author Alexey Voronin.
 * @since 09.01.2018.
 */
@Configuration
@ComponentScan("ru.job4j")
@ImportResource("classpath*:*spring-context.xml")
public class SpringRootConfig {
}
