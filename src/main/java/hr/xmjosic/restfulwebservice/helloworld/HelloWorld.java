package hr.xmjosic.restfulwebservice.helloworld;

import lombok.Getter;

/**
 * Test class.
 */
@Getter
public class HelloWorld {
    /**
     * Text property.
     */
    private String text;

    public HelloWorld(String name){
        this.text = "Hello world, " + name + "!";
    }
}
