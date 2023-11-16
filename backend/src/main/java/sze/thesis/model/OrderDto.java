package sze.thesis.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import sze.thesis.persistence.entity.Item;
import sze.thesis.persistence.entity.Order;
import sze.thesis.persistence.entity.OrderStatus;
import sze.thesis.persistence.entity.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OrderDto implements Serializable {
    @NotBlank
    private OrderStatus status;
    @NotNull
    private double totalPrice;
    @NotBlank
    private LocalDateTime createdAt;
    @NotBlank
    private UserDto user;
    @NotBlank
    private List<ItemDto> items;

    public OrderDto(Order order) {
        this.status = order.getStatus();
        this.totalPrice = order.getTotalPrice();
        this.createdAt = order.getCreatedAt();
        this.user = new UserDto(order.getUser());
        this.items = createItemDtoList(order);
    }

    private ArrayList<ItemDto> createItemDtoList(Order order){
        ArrayList<ItemDto> result = new ArrayList<>();
        order.getItems().forEach(item -> {
            result.add(new ItemDto(item));
        });
        return result;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "status=" + status +
                ", totalPrice=" + totalPrice +
                ", createdAt=" + createdAt +
                ", user=" + user +
                ", items=" + items +
                '}';
    }
}
