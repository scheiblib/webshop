package sze.thesis.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sze.thesis.model.OrderDto;
import sze.thesis.persistence.entity.Order;
import sze.thesis.service.OrderService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/myOrders")
    public List<OrderDto> getMyOrders() {
        return orderService.findLoggedInUserOrders();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public Order getOrder(@PathVariable("id") long id) {
        return orderService.findOrderById(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/allOrders")
    public List<OrderDto> getAllOrders() {
        return orderService.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/addItem/{id}")
    public OrderDto addItem(@PathVariable("id") long id) {
        return orderService.addItemToOrder(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/deleteItem/{id}")
    public OrderDto removeItem(@PathVariable("id") long id) throws Exception {
        return orderService.removeItemFromOrder(id);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/placeOrder")
    public OrderDto placeOrder() {
        return orderService.placeOrder();
    }
}
