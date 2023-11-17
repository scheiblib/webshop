package sze.thesis.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type;
    private double width;
    private double height;
    private String colour;
    private double price;

    @ManyToMany(mappedBy = "items")
    @ToString.Exclude
    private List<Order> order;


    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", colour='" + colour + '\'' +
                ", price=" + price +
                '}';
    }
}
