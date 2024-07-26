package gift.service;

import gift.dto.request.OrderRequest;
import gift.dto.response.OrderResponse;
import gift.entity.Order;
import gift.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MockitoSettings
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderService orderService;

    @Test
    void saveOrder() {
        //Given
        Long optionId = 1L;
        OrderRequest orderRequest = new OrderRequest(optionId, 100, "hello!");
        Order savedOrder = mock(Order.class);

        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);
        when(savedOrder.getId()).thenReturn(1L);
        when(savedOrder.getOptionId()).thenReturn(optionId);
        when(savedOrder.getQuantity()).thenReturn(100);
        when(savedOrder.getOrderDateTime()).thenReturn(LocalDateTime.now());
        when(savedOrder.getMessage()).thenReturn("hello!");

        //When
        OrderResponse response = orderService.saveOrder(orderRequest);

        //Then
        assertThat(response).isNotNull();
        assertThat(response.message()).isEqualTo("hello!");
    }
}