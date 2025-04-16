package OrderItems.service;

import OrderItems.dto.OrderItemsDTO;
import OrderItems.model.OrderItemsModel;
import OrderItems.repository.OrderItemsRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class OrderItemsService {

    @Inject
    OrderItemsRepository orderItemsRepository;

    public List<OrderItemsDTO> getOrderItemsModel(){
        List<OrderItemsModel> orderItemsModel = orderItemsRepository.getOrderItemsModel();
        return orderItemsModel.stream().map(OrderItemsDTO::new).collect(Collectors.toList());
    }

    public OrderItemsDTO getOrderItemsById(int id) {
        return new OrderItemsDTO (orderItemsRepository.getOrderItemsById(id));
    }

    public List<Object[]> getOrderItemsModelByCustomerId(int CustomerId) {
        return orderItemsRepository.getOrderItemsModelByCustomerId(CustomerId);
    }

    public OrderItemsDTO makeOrderItemsDTO(int quantity, int productId, int orderDetailsId) {
        OrderItemsModel orderItemsModel = orderItemsRepository.makeAnOrder(quantity, productId, orderDetailsId);
        return new OrderItemsDTO(orderItemsModel);
    }

    public OrderItemsDTO deleteOrderItemsById(int id) {
        return new OrderItemsDTO(orderItemsRepository.deleteOrderItemsById(id));
    }

    public List<OrderItemsDTO> deleteOrderItemsByOrderDetailsId(int orderDetailsId) {
        List<OrderItemsModel> orderItemsModelList =  orderItemsRepository.deleteOrderItemsByOrderDetailsId(orderDetailsId);
        return orderItemsModelList.stream().map(OrderItemsDTO::new).collect(Collectors.toList());
    }

    public OrderItemsDTO updateOrderItemsById(int id, int quantity, int productId, int orderDetailsId) {
        return new OrderItemsDTO(orderItemsRepository.updateOrderItemsById(id, quantity, productId, orderDetailsId));
    }
}