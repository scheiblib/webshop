package sze.thesis.service;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sze.thesis.model.OrderDto;
import sze.thesis.persistence.entity.Item;
import sze.thesis.persistence.entity.Order;
import sze.thesis.persistence.entity.OrderStatus;
import sze.thesis.persistence.entity.User;
import sze.thesis.persistence.repository.ItemRepository;
import sze.thesis.persistence.repository.OrderRepository;
import sze.thesis.service.mapper.OrderMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;
    @Autowired
    private ItemRepository itemRepository;

    public Order findOrderById(long id){
        return orderRepository.findById(id);
    }

    public List<OrderDto> findLoggedInUserOrders() {
        User user = userService.getLoggedUser();
        return orderMapper.orderResponseDtoList(user.getOrders());
    }

    public Order createPendingOrder(){
        return Order.builder()
                .status(OrderStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .items(new ArrayList<>())
                .user(userService.getLoggedUser())
                .totalPrice(0)
                .build();
    }

    public OrderDto getPendingOrder(){
        List<OrderDto> allOrders = findLoggedInUserOrders();
        OrderDto pendingOrder = null;
        for(OrderDto o : allOrders){
            if(o.getStatus().equals(OrderStatus.PENDING)){
                pendingOrder = o;
                break;
            }
        }
        return pendingOrder;
    }

    public List<OrderDto> findAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = orderMapper.orderResponseDtoList(orders);
        return orderDtos;
    }

    public OrderDto addItemToOrder(long itemId){
        OrderDto pendingOrder = getPendingOrder() == null ?
                orderMapper.mapOrderEntityToOrderResponseDto(createPendingOrder()) :
                getPendingOrder();
        Item item = itemService.findItemById(itemId);
        pendingOrder.setTotalPrice(pendingOrder.getTotalPrice() + item.getPrice());
        pendingOrder.getItems().add(item);
        Order orderToSave = orderMapper.mapForCreateOrder(pendingOrder);
        orderRepository.save(orderToSave);
        return pendingOrder;
    }

    public OrderDto removeItemFromOrder(long itemId) throws Exception {
        OrderDto pendingOrder = getPendingOrder();
        if (pendingOrder == null) {
            throw new Exception("There is no pending order.");
        }
        pendingOrder.setTotalPrice(pendingOrder.getTotalPrice() - itemRepository.findById(itemId).getPrice());
        pendingOrder.getItems().remove(itemRepository.findById(itemId));
        Order orderToSave = orderMapper.mapForCreateOrder(pendingOrder);
        orderRepository.save(orderToSave);
        return pendingOrder;
    }
    public OrderDto placeOrder(){
        OrderDto orderToSend = getPendingOrder();
        orderToSend.setStatus(OrderStatus.SENT);
        Order orderToSave = orderMapper.mapForCreateOrder(orderToSend);
        orderRepository.save(orderToSave);
        return orderToSend;
    }
}
