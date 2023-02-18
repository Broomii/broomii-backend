package Boormii.soonDelivery.members.service;

import Boormii.soonDelivery.global.exception.ApiException;
import Boormii.soonDelivery.members.domain.Members;
import Boormii.soonDelivery.members.dto.EditProfileRequestDto;
import Boormii.soonDelivery.members.dto.MyPageResponseDto;
import Boormii.soonDelivery.members.dto.ProfileResponseDto;
import Boormii.soonDelivery.members.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final MembersRepository membersRepository;

    public MyPageResponseDto getMyPage(String email) {
        Optional<Members> members = membersRepository.findByEmail(email);
        if (!members.isPresent()){
            throw new ApiException(HttpStatus.EXPECTATION_FAILED, "존재하지 않는 회원입니다.");
        }
        return MyPageResponseDto.register(members.get());
    }

    @Transactional
    public Long editProfile(EditProfileRequestDto editProfileRequestDto, String email){
        Optional<Members> members = membersRepository.findByEmail(email);
        if (!members.isPresent()){
            throw new ApiException(HttpStatus.EXPECTATION_FAILED, "존재하지 않는 회원입니다.");
        }
        return members.get().editProfile(editProfileRequestDto);
    }

    public ProfileResponseDto getProfile(String email){
        Optional<Members> members = membersRepository.findByEmail(email);
        if (!members.isPresent()){
            throw new ApiException(HttpStatus.EXPECTATION_FAILED, "존재하지 않는 회원입니다.");
        }
        return ProfileResponseDto.register(members.get());
    }
}
