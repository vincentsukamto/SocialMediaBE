package com.instagramgaul.demo.common;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application.properties")
public class PropertiesUtil implements EnvironmentAware {
    private static Environment env;

    @Override
    public void setEnvironment(Environment environment) {
        PropertiesUtil.env = environment;
    }

    public static String load(String propertyName){
        return env.getRequiredProperty(propertyName);
    }
}
