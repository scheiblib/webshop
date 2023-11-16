package sze.thesis.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sze.thesis.persistence.entity.Order;
import sze.thesis.persistence.entity.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findById(long id);

    List<Order> findAllByUser(User user);
}
