package Boormii.soonDelivery.member.repository;

import Boormii.soonDelivery.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Optional<Member> findByEmail(String email);

    public Optional<Member> findByNickName(String nickName);
}
