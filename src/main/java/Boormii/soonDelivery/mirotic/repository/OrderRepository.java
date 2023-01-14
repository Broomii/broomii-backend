package Boormii.soonDelivery.mirotic.repository;

import Boormii.soonDelivery.mirotic.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    Orders findByTitle(String title);

}
