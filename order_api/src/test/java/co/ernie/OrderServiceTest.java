package co.ernie;

import co.ernie.dto.CustomerDTO;
import co.ernie.repository.OrderRepository;
import co.ernie.repository.entity.CustomerEntity;
import co.ernie.service.OrderService;
import ma.glasnost.orika.MapperFacade;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @Mock
    private MapperFacade mapper;

    @InjectMocks
    private OrderService systemUnderTest;

    @Test
    public void getCustomerByOrderId_ScenarioA(){

        //arrange
        CustomerEntity entity = new CustomerEntity(1, "A", 11.11, 22.22);

        doReturn(entity).when(repository).getCustomerByOrderId(1);

        CustomerDTO customer = new CustomerDTO(1, "A", 11.11, 22.22);

        doReturn(customer).when(mapper).map(entity, CustomerDTO.class);

        // act
        CustomerDTO actual = systemUnderTest.getCustomerByOrderId(1);

        //assert

        Assert.assertThat(actual.getId(), is(1));
        Assert.assertThat(actual.getName(), is("A"));
        Assert.assertThat(actual.getLat(), is(11.11));
        Assert.assertThat(actual.getLon(), is(22.22));

        //verify
        verify(repository, times(1)).getCustomerByOrderId(1);
        verifyNoMoreInteractions(repository);
    }
}
