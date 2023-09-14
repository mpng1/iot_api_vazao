package br.com.pointer.sii.api.infra.security;

import org.springframework.boot.web.server.Ssl;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

@Component
public class SSLCustomizer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setSsl(getSsl());
        factory.setPort(8443);
    }

    private Ssl getSsl() {
        Ssl ssl = new Ssl();
        ssl.setKeyStore("classpath:sii.p12");
        ssl.setKeyStorePassword("P0rt0$13");
        ssl.setKeyStoreType("PKCS12");
        ssl.setKeyAlias("sii");
        return ssl;
    }
}
