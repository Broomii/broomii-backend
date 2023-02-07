package Boormii.soonDelivery.orders.repository;

import Boormii.soonDelivery.orders.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Orders findByTitle(String title);

    void deleteById(Long id);

    Optional<Orders> findById(Long id);

    
}
