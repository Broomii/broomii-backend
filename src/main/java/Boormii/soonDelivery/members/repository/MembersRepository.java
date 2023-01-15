package Boormii.soonDelivery.members.repository;

import Boormii.soonDelivery.members.domain.Members;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembersRepository extends JpaRepository<Members, Long> {

    Optional<Members> findByEmail(String email);

    Optional<Members> findByNickName(String nickName);

}
