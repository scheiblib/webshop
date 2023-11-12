package sze.thesis.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sze.thesis.persistence.entity.Order;
import sze.thesis.service.OrderService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/myOrders")
    public List<Order> getMyOrders() {
        return orderService.findLoggedInUserOrders();
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable("id") long id) {
        return orderService.findOrderById(id);
    }

    @GetMapping("/allOrders")
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @PutMapping("/addItem/{id}")
    public Order addItem(@PathVariable("id") long id) {
        return orderService.addItemToOrder(id);
    }

    @DeleteMapping("/deleteItem/{id}")
    public Order removeItem(@PathVariable("id") long id) throws Exception {
        return orderService.removeItemFromOrder(id);
    }
    @PostMapping("/placeOrder")
    public Order placeOrder() {
        return orderService.placeOrder();
    }
}
