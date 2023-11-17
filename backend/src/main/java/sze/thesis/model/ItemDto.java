package sze.thesis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import sze.thesis.persistence.entity.Item;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ItemDto implements Serializable {
    private String type;
    private double width;
    private double height;
    private String colour;
    private double price;

    public ItemDto(Item item) {
        this.type = item.getType();
        this.width = item.getWidth();
        this.height = item.getHeight();
        this.colour = item.getColour();
        this.price = item.getPrice();
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "type='" + type + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", colour='" + colour + '\'' +
                ", price=" + price +
                '}';
    }
}
