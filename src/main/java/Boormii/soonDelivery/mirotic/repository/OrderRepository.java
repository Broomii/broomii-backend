package Boormii.soonDelivery.mirotic.repository;

import Boormii.soonDelivery.mirotic.domain.Mirotic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Mirotic, Long> {
    public Mirotic findByTitle(String title);

}
