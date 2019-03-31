package com.baeldung.boot.bootstrapsimpleapplication.config;


import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;
//server.port argument will have Order 4
@Component
public class ServerPortConfig
        implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        factory.setPort(8080);
    }
}