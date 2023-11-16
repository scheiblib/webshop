package sze.thesis.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class CreateUserDto extends UserDto {
    @NotBlank
    private String password;
}
