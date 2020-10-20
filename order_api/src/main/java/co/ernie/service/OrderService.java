package co.ernie.service;

import co.ernie.dto.CustomerDTO;
import co.ernie.repository.OrderRepository;
import co.ernie.repository.entity.CustomerEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {
    private OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository r){
        this.repository = r;
    }

    public CustomerDTO getCustomerByOrderId(int orderId){
        log.info("OrderService.getCustomerByOrderId(" + orderId + ")");
        CustomerEntity e = repository.getCustomerByOrderId(orderId);
        return new CustomerDTO(e.getId(), e.getName(), e.getLat(), e.getLon());
    }

    public void closeOrder(int orderId){
        log.info("OrderService.closeOrder(" + orderId + ")");
        repository.closeOrder(orderId);
    }

}
