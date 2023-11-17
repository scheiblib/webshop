package sze.thesis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sze.thesis.model.ItemInput;
import sze.thesis.model.OrderDto;
import sze.thesis.persistence.entity.*;
import sze.thesis.persistence.repository.ItemRepository;
import sze.thesis.persistence.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;
    @Autowired
    private ItemRepository itemRepository;

    public OrderDto findOrderById(long id){
        return new OrderDto(orderRepository.findById(id));
    }

    public List<Order> findLoggedInUserOrders() {
        User user = userService.getLoggedUser();
        return orderRepository.findAllByUser(user);
    }

    public List<OrderDto> getUserOrderdtoList(){
        User user = userService.getLoggedUser();
        ArrayList<OrderDto> result = new ArrayList<>();
        return convertEntityListToDtoList(orderRepository.findAllByUser(user));
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

    public Order getPendingOrder(){
        List<Order> allOrders = findLoggedInUserOrders();
        if (allOrders == null) {
            return null;
        }
        Order pendingOrder = null;
        for(Order o : allOrders){
            if(o.getStatus().equals(OrderStatus.PENDING)){
                pendingOrder = o;
                break;
            }
        }
        return pendingOrder;
    }

    public List<OrderDto> findAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = convertEntityListToDtoList(orders);
        return orderDtos;
    }

    public Order addItemToOrder(long itemId, ItemInput input){
        Order pendingOrder = getPendingOrder() == null ?
                createPendingOrder() :
                getPendingOrder();
        Item item = itemService.findItemById(itemId);
        item.setColour(input.getColour());
        item.setWidth(input.getWidth());
        item.setHeight(input.getHeight());
//        item.setPrice(item.getPrice() * (input.getHeight() * input.getWidth()) /10_000);
        pendingOrder.getItems().add(item);
        pendingOrder.setTotalPrice(pendingOrder.getTotalPrice() + item.getPrice() * (input.getHeight() * input.getWidth()) /10_000);
        orderRepository.save(pendingOrder);
        return pendingOrder;
    }

    public Order removeItemFromOrder(long itemId) throws Exception {
        Order pendingOrder = getPendingOrder();
        if (pendingOrder == null) {
            throw new Exception("There is no pending order.");
        }
        pendingOrder.getItems().remove(itemRepository.findById(itemId));
        pendingOrder.setTotalPrice(pendingOrder.getTotalPrice() - itemRepository.findById(itemId).getPrice());
        orderRepository.save(pendingOrder);
        return pendingOrder;
    }
    public Order placeOrder(){
        Order orderToSend = getPendingOrder();
        orderToSend.setStatus(OrderStatus.SENT);
        orderToSend.setCreatedAt(LocalDateTime.now());
        orderRepository.save(orderToSend);
        return orderToSend;
    }
    private List<OrderDto> convertEntityListToDtoList(List<Order> orders){
        List<OrderDto> result = new ArrayList<>();
        orders.forEach(order -> {
            result.add(new OrderDto(order));
        });
        return result;
    }
}
