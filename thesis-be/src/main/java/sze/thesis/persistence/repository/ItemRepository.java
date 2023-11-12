package sze.thesis.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sze.thesis.persistence.entity.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findById(long id);
    List<Item> findAll();
}
