package co.ernie.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity implements Serializable {
    private int id;
    private String name;
    private double lat;
    private double lon;
}
