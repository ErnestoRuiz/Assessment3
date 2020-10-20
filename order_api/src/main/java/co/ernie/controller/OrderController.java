package co.ernie.controller;

import co.ernie.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService service;

    @Autowired
    public OrderController(OrderService s){
        this.service = s;
    }
    @GetMapping(value = "/orderId/{orderId}", produces = "application/json")
    public ResponseEntity getCustomerByOrderId(@PathVariable int orderId){
        log.info("OrderController.getCustomerById("+ orderId + ")");
        return ResponseEntity.status(HttpStatus.OK).body(service.getCustomerByOrderId(orderId));
    }

    @PutMapping(value = "/completeorder/{orderId}")
    public ResponseEntity completeOrder(@PathVariable int orderId){
        log.info("OrderController.completeOrder(" + orderId + ")");
        service.closeOrder(orderId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
