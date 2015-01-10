package hob.psd.todo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

/**
 * Created by lgunti on 030, Nov 30.
 */
@Configuration
@PropertySource("classpath:result-messages.properties")
public class MessageConfig {

    @Autowired
    Environment env;

    public String getMessage(String name){
        return env.getProperty(name);
    }

    public String getMessage(String name,Object... args) {
        String property = env.getProperty(name);
        return MessageFormat.format(property,args);
    }

    public Environment getEnv() {
        return env;
    }

    public void setEnv(Environment env) {
        this.env = env;
    }
}
