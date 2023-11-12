package sze.thesis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sze.thesis.persistence.entity.Item;
import sze.thesis.persistence.entity.Order;
import sze.thesis.persistence.entity.OrderStatus;
import sze.thesis.persistence.entity.User;
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

    public Order findOrderById(long id){
        return orderRepository.findById(id);
    }

    public List<Order> findLoggedInUserOrders() {
        User user = userService.getLoggedUser();
        return user.getOrders();
    }

    public Order getPendingOrder(){
        List<Order> allOrders = findLoggedInUserOrders();
        Order pendingOrder = null;
        for(Order o : allOrders){
            if(o.getStatus().equals(OrderStatus.PENDING)){
                pendingOrder = o;
                break;
            }
        }
        return pendingOrder;
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
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
    public Order addItemToOrder(long itemId){
        Order pendingOrder = getPendingOrder() == null ? createPendingOrder() : getPendingOrder();
        Item item = itemService.findItemById(itemId);
        User u = userService.getLoggedUser();
        pendingOrder.setTotalPrice(pendingOrder.getTotalPrice() + item.getPrice());
        pendingOrder.getItems().add(item);
        orderRepository.save(pendingOrder);
        return pendingOrder;
    }

    public Order removeItemFromOrder(long itemId) throws Exception {
        Order pendingOrder = getPendingOrder();
        if (pendingOrder == null) {
            throw new Exception("There is no pending order.");
        }
        pendingOrder.setTotalPrice(pendingOrder.getTotalPrice() - itemRepository.findById(itemId).getPrice());
        pendingOrder.getItems().remove(itemRepository.findById(itemId));
        orderRepository.save(pendingOrder);
        return pendingOrder;
    }
    public Order placeOrder(){
        Order orderToSend = getPendingOrder();
        orderToSend.setStatus(OrderStatus.valueOf("SENT"));
        orderRepository.save(orderToSend);
        return orderToSend;
    }
}
