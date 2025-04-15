package OrderDetails.service;

import OrderDetails.dto.OrderDetailsDTO;
import OrderDetails.model.OrderDetailsModel;
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
        OrderDetailsModel orderDetailsModel = orderDetailsRepository.getOrderDetailsByOrderId(id);
        return new OrderDetailsDTO(orderDetailsModel);
    }

    public OrderDetailsDTO getOrderDetailsByCustomerId(int customerId) {
        OrderDetailsModel orderDetailsModel =  orderDetailsRepository.getOrderDetailsByCustomerId(customerId);
        return new OrderDetailsDTO(orderDetailsModel);
    }

    public OrderDetailsDTO createOrderDetails(int customerId) {
        OrderDetailsModel orderDetailsModel = orderDetailsRepository.createOrderDetails(customerId);
        return new OrderDetailsDTO(orderDetailsModel);
    }

    public OrderDetailsDTO deleteOrderDetails(int id) {
        OrderDetailsModel orderDetailsModel = orderDetailsRepository.deleteOrderDetails(id);
        return new OrderDetailsDTO(orderDetailsModel);
    }
}
