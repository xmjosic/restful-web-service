package hr.xmjosic.restfulwebservice.helloworld.controller;

import hr.xmjosic.restfulwebservice.helloworld.HelloWorld;
import hr.xmjosic.restfulwebservice.helloworld.configuration.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

/**
 * Hello world rest controller.
 */
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class HelloWorldController {

    @Autowired private MessageSource messageSource;
    @Autowired private ApplicationProperties applicationProperties;

    /**
     * @return Hello world message.
     */
    @GetMapping("/hello-world")
    public ResponseEntity<Map<String, String>> helloWorld() {
        String message = this.messageSource.getMessage("hello.world", null, LocaleContextHolder.getLocale());
        return ResponseEntity.ok(Collections.singletonMap("message", message));
    }

    /**
     * @param name String
     * @return Returns Hello world message with provided name.
     */
    @GetMapping("/path-variable/{name}")
    public ResponseEntity<HelloWorld> helloWorldModel(@PathVariable String name) {
        return ResponseEntity.ok(new HelloWorld(name));
    }

    /**
     * @return Returns message from properties file.
     */
    @GetMapping("/message")
    public ResponseEntity<Map<String, String>> helloWorldPropertySource2() {
        return ResponseEntity.ok(Collections.singletonMap("message", this.applicationProperties.getGreetingMessage()));
    }
}
