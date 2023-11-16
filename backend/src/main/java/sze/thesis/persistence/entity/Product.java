//package sze.thesis.persistence.entity;
//
//import jakarta.persistence.*;
//
//import java.util.List;
//
//@Entity
//@Table(name = "products")
//public class Product {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private long id;
//    private String type;
//    private double size;
//    private String colour;
//    private double price;
//    @OneToMany(mappedBy = "product")
//    private List<Item> items;
//}