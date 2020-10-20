package co.ernie.consumer;

import co.ernie.dto.CustomerDTO;
import co.ernie.dto.OrderDTO;
import co.ernie.producer.ProducerClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class ConsumerClass {
    private RestTemplate restTemplate;

    @Autowired
    public ConsumerClass(RestTemplate t) {
        this.restTemplate = t;
    }

    @Autowired
    ProducerClass producer;

    @JmsListener(destination = "assessment3-positions")
    public void listen(String order) throws Exception{
        ObjectMapper obj = new ObjectMapper();
        OrderDTO orderDTO =obj.readValue(order, OrderDTO.class);
        log.info("Order Received: " + orderDTO.toString());

        String url = "http://localhost:8080/order_api/orders/orderId/" + orderDTO.getOrderId();
        log.info("hitting order_api endpoint: getCustomerByOrderId()");

        CustomerDTO customerDTO = restTemplate.getForObject(url, CustomerDTO.class);
        log.info("DTO FROM API MAPPED TO OBJECT");

        if(orderDTO.getLat() == customerDTO.getLat() & orderDTO.getLon() == orderDTO.getLon()){
            log.info("COMPARING ORDER CORDS TO CUSTOMER CORDS");
            producer.completeOrder(orderDTO.getOrderId());
        }

    }
}
