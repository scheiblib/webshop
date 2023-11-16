package sze.thesis.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import sze.thesis.persistence.entity.Order;
import sze.thesis.persistence.entity.Role;
import sze.thesis.persistence.entity.User;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UserResponseDto extends UserDto {
    private Role role;
    private List<Order> orders;

    public UserResponseDto(User user) {
        super(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(), user.getCity(), user.getAddress());
        this.role = user.getRole();
        this.orders = user.getOrders();
    }
}
