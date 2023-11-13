package sze.thesis.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sze.thesis.model.OrderDto;
import sze.thesis.model.UserResponseDto;
import sze.thesis.persistence.entity.Order;
import sze.thesis.persistence.entity.OrderStatus;
import sze.thesis.persistence.entity.Role;
import sze.thesis.persistence.entity.User;
import sze.thesis.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {
    @Autowired
    private UserService userService;
    public Order mapForCreateOrder (OrderDto orderDto) {
        return Order.builder()
                .status(orderDto.getStatus())
                .totalPrice(orderDto.getTotalPrice())
                .createdAt(orderDto.getCreatedAt())
                .user(orderDto.getUser())
                .items(orderDto.getItems())
                .build();
    }

    public OrderDto mapOrderEntityToOrderResponseDto (Order order) {
        return OrderDto.builder()
                .status(order.getStatus())
                .totalPrice(order.getTotalPrice())
                .user(order.getUser())
                .createdAt(order.getCreatedAt())
                .items(order.getItems())
                .build();
    }

    public List<OrderDto> orderResponseDtoList(List<Order> orderList){
        List<OrderDto> orderResponseDtoList = new ArrayList<>();
        for(Order order : orderList){
            orderResponseDtoList.add(mapOrderEntityToOrderResponseDto(order));
        }
        return orderResponseDtoList;
    }
}
