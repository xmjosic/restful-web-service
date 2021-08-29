package hr.xmjosic.restfulwebservice.helloworld.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "test")
@Getter
@Setter
public class ApplicationProperties {
    private String greetingMessage;
}
