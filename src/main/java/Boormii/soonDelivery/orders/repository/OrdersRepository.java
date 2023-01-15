package Boormii.soonDelivery.orders.repository;

import Boormii.soonDelivery.orders.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Orders findByTitle(String title);

}
