package co.ernie.configuration;

import co.ernie.dto.CustomerDTO;
import co.ernie.repository.entity.CustomerEntity;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AppConfiguration {

    @Bean
    public MapperFactory mapperFactory(){
        DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder()
                .dumpStateOnException(false)
                .useBuiltinConverters(true)
                .build();

        mapperFactory.classMap(CustomerDTO.class, CustomerEntity.class)
                .mapNulls(true)
                .byDefault()
                .register();

        mapperFactory.classMap(CustomerEntity.class, CustomerDTO.class)
                .mapNulls(true)
                .byDefault()
                .register();

        return mapperFactory;
    }

    @Bean
    public MapperFacade mapper(MapperFactory mapperFactory) {
        return mapperFactory.getMapperFacade();
    }
}
