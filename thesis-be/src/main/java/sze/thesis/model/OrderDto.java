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
    private User user;
    @NotBlank
    private List<Item> items;
}
