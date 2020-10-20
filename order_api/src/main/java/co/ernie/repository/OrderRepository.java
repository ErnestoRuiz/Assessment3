package co.ernie.repository;

import co.ernie.repository.entity.CustomerEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class OrderRepository {

    private NamedParameterJdbcTemplate template;

    @Autowired
    public OrderRepository(NamedParameterJdbcTemplate t){
        this.template = t;
    }

    public CustomerEntity getCustomerByOrderId(int orderId){
        log.info("OrderRepository.getCustomerByOrderId(" + orderId + ")");
        String query = "SELECT customers.id, customers.name, customers.lat, customers.lon from customers left join orders on customers.id = orders.customerId where orders.id = :var";
        Map<String, Object> params = new HashMap<>();
        params.put("var", orderId);
        RowMapper<CustomerEntity> rowMapper = new BeanPropertyRowMapper<>(CustomerEntity.class);

        return template.queryForObject(query, params, rowMapper);
    }

    public void closeOrder(int orderId){
        String query = "update orders set complete = 1 where Id = :var";
        Map<String, Object> params = new HashMap<>();
        params.put("var", orderId);

        template.update(query, params);
    }
}
