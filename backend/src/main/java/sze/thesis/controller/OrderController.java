package sze.thesis.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sze.thesis.model.OrderDto;
import sze.thesis.model.ItemInput;
import sze.thesis.service.OrderService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/orders")
@AllArgsConstructor
public class OrderController {

    @Autowired
    private OrderService orderService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/my_orders")
    public List<OrderDto> getMyOrders() {
        return orderService.getUserOrderdtoList();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable("id") long id) {
        return orderService.findOrderById(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/cart")
    public OrderDto getCart() {
        return new OrderDto(orderService.getPendingOrder());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public List<OrderDto> getAllOrders() {
        return orderService.findAll();
    }

//    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/add_item/{id}")
    public OrderDto addItem(@PathVariable("id") long id,
                            @RequestBody ItemInput input) {
        return new OrderDto(orderService.addItemToOrder(id, input));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delete_item/{id}")
    public OrderDto removeItem(@PathVariable("id") long id) throws Exception {

        return new OrderDto(orderService.removeItemFromOrder(id));
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/place")
    public OrderDto placeOrder() {
        return new OrderDto(orderService.placeOrder());

    }
}
