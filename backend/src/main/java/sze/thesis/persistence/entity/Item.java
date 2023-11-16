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
    @Column(name = "item_id")
    private long id;
    private String type;
    private double size;
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
                ", size=" + size +
                ", colour='" + colour + '\'' +
                ", price=" + price +
                '}';
    }
}
