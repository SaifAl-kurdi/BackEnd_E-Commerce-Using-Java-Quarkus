package OrderDetails.service;

import OrderDetails.dto.OrderDetailsDTO;
import OrderDetails.repository.OrderDetailsRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class OrderDetailsService {

    @Inject
    OrderDetailsRepository orderDetailsRepository;

    public List<Object[]> getOrderDetails() {
        return orderDetailsRepository.getOrderDetails();
    }

    public OrderDetailsDTO getOrderDetailsByOrderId(int id) {
        return new OrderDetailsDTO(orderDetailsRepository.getOrderDetailsByOrderId(id));
    }

    public OrderDetailsDTO getOrderDetailsByCustomerId(int customerId) {
        return new OrderDetailsDTO(orderDetailsRepository.getOrderDetailsByCustomerId(customerId));
    }

    public OrderDetailsDTO createOrderDetails(int customerId) {
        return new OrderDetailsDTO(orderDetailsRepository.createOrderDetails(customerId));
    }

    public OrderDetailsDTO deleteOrderDetails(int id) {
        return new OrderDetailsDTO(orderDetailsRepository.deleteOrderDetails(id));
    }

    public OrderDetailsDTO updateOrderDetails(int id, float total, int customerId) {
        return new OrderDetailsDTO(orderDetailsRepository.updateOrderDetails(id, total, customerId));
    }

    public OrderDetailsDTO deleteOrderDetailsByCustomerId(int customerId) {
        return new OrderDetailsDTO(orderDetailsRepository.deleteOrderDetailsByCustomerId(customerId));
    }

    public OrderDetailsDTO updateOrderDetailsByCustomerId(int customerId, float total) {
        return new OrderDetailsDTO(orderDetailsRepository.updateOrderDetailsByCustomerId(customerId, total));
    }
}