package sze.thesis.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sze.thesis.persistence.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findById(long id);
}
