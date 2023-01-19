package Boormii.soonDelivery.members.service;

import Boormii.soonDelivery.members.domain.Members;
import Boormii.soonDelivery.members.dto.ConfirmCertificationRequestDto;
import Boormii.soonDelivery.members.repository.MembersRepository;
import Boormii.soonDelivery.members.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MembersService {
    private final MembersRepository membersRepository;
    private final RedisUtil redisUtil;
//    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long join(Members members){
//        member.setPassword(passwordEncoder.encode(member.getPassword()));
        membersRepository.save(members);
        return members.getId();
    }
    public void validateDuplicateEmail(String email){
        Optional<Members> newMember = membersRepository.findByEmail(email);
        if(newMember.isPresent()){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
    public String validateDuplicateNickName(String nickname){
        Optional<Members> newMember = membersRepository.findByNickName(nickname);

        // 던져

        if(newMember.isPresent()){
            return "이미 존재하는 닉네임입니다.";
        }
        else {
            return "사용할 수 있는 닉네임 입니다.";
        }
    }

    public String getDefaultAddress(String email) {
        return membersRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new).getDefaultDeliveryAddress();
    }

    public void confirmCertification(ConfirmCertificationRequestDto confirmCertificationRequestDto) {

        if (!redisUtil.getData(confirmCertificationRequestDto.getCertification()).equals(confirmCertificationRequestDto.getEmail())) {
            throw new IllegalArgumentException("인증 번호가 다릅니다.");
        }
    }
}
