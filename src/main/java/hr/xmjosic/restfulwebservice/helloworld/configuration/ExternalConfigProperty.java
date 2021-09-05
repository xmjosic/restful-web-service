package hr.xmjosic.restfulwebservice.helloworld.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "external.file")
@PropertySource(value = "classpath:external-file.yml", factory = YamlPropertySourceFactory.class)
@Getter
@Setter
public class ExternalConfigProperty {
    private String message;
}
