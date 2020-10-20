package co.ernie.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.jms.Topic;

@Slf4j
@Service
public class ProducerClass {
    @Autowired
    private Topic topic;

    @Autowired
    JmsTemplate jmsTemplate;

    private RestTemplate restTemplate;

    @Autowired
    public ProducerClass (RestTemplate t){
        this.restTemplate = t;
    }

    public void completeOrder(int orderId){
        jmsTemplate.convertAndSend(topic, "Order:" + orderId + " completed");
        log.info("ProducerClass.jmsTemplate.convertAndSend(topic, Order: " + orderId + " completed");

        String url = "http://localhost:8080/order_api/orders/completeorder/" + orderId;
        log.info("hitting orders_api completeorders endpoint");
        HttpEntity entity = new HttpEntity(String.class);
        ResponseEntity response = this.restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
        log.info("order marked as complete in database");
    }
}
