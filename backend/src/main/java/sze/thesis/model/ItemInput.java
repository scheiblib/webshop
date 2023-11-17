package sze.thesis.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemInput {
    @NotNull
    @Value("white")
    String colour;

    @NotNull
    double width;
    @NotNull
    double height;
}
