package Boormii.soonDelivery.member.repository;

import Boormii.soonDelivery.member.domain.Members;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Members, Long> {

    Optional<Members> findByEmail(String email);

    Optional<Members> findByNickName(String nickName);

}
