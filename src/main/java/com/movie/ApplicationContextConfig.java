package com.movie;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@SpringBootConfiguration
@EnableWebMvc
@ComponentScan("com.movie")
public class ApplicationContextConfig {

}
