package sze.thesis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import sze.thesis.persistence.entity.Item;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ItemDto implements Serializable {
    private String type;
    private double size;
    private String colour;
    private double price;

    public ItemDto(Item item) {
        this.type = item.getType();
        this.size = item.getSize();
        this.colour = item.getColour();
        this.price = item.getPrice();
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "type='" + type + '\'' +
                ", size=" + size +
                ", colour='" + colour + '\'' +
                ", price=" + price +
                '}';
    }
}
