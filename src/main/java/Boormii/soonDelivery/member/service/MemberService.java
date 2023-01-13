package Boormii.soonDelivery.member.service;

import Boormii.soonDelivery.member.domain.Member;
import Boormii.soonDelivery.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
//    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long join(Member member){
//        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
        return member.getId();
    }
    public void validateDuplicateEmail(String email){
        Optional<Member> newMember = memberRepository.findByEmail(email);
        if(newMember.isPresent()){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
    public String validateDuplicateNickName(String nickname){
        Optional<Member> newMember = memberRepository.findByNickName(nickname);
        
        // 던져
        
        if(newMember.isPresent()){
            return "이미 존재하는 닉네임입니다.";
        }
        else {
            return "사용할 수 있는 닉네임 입니다.";
        }
    }
}
