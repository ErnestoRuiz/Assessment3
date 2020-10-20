package co.ernie;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.web.client.RestTemplate;

import javax.jms.Topic;

@Slf4j
@EnableJms
@SpringBootApplication
public class App {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public Topic topic(){
        return new ActiveMQTopic("assessment3-complete");
    }

    public static void main( String[] args ) {
        SpringApplication.run(App.class);
    }

}
