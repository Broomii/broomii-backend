package Boormii.soonDelivery.members.service;

import Boormii.soonDelivery.global.exception.ApiException;
import Boormii.soonDelivery.global.jwt.JwtProvider;
import Boormii.soonDelivery.members.domain.Members;
import Boormii.soonDelivery.members.dto.ConfirmCertificationRequestDto;
import Boormii.soonDelivery.members.dto.EditPasswordRequestDto;
import Boormii.soonDelivery.members.dto.JoinRequestDto;
import Boormii.soonDelivery.members.dto.LoginRequestDto;
import Boormii.soonDelivery.members.dto.token.RefreshRequestDto;
import Boormii.soonDelivery.members.dto.token.TokenDto;
import Boormii.soonDelivery.members.repository.MembersRepository;
import Boormii.soonDelivery.members.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MembersService {
    private final MembersRepository membersRepository;
    private final AuthenticationManagerBuilder authenticationManageBuilder;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate redisTemplate;

    private final RedisUtil redisUtil;

    @Transactional
    public TokenDto join(JoinRequestDto joinRequestDto) {
        validateDuplicateEmail(joinRequestDto.getEmail());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(joinRequestDto.getEmail(), joinRequestDto.getPassword());
        Members members = Members.registerMember(joinRequestDto, passwordEncoder.encode(joinRequestDto.getPassword()));
        membersRepository.save(members);
        Authentication authentication = authenticationManageBuilder.getObject().authenticate(authenticationToken);
        TokenDto tokenDto = jwtProvider.generateToken(authentication);
        redisTemplate.opsForValue()
                .set(authentication.getName(), tokenDto.getRefreshToken(), tokenDto.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);
        return tokenDto;
    }

    public TokenDto login(LoginRequestDto loginRequestDto) {
        Members member = Members.createMemberByEmailAndPW(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(member.getEmail(), member.getPassword());
        Authentication authentication = authenticationManageBuilder.getObject().authenticate(authenticationToken);
//        member = membersRepository.findByEmail(member.getEmail()).get();
        TokenDto tokenDto = jwtProvider.generateToken(authentication);

        redisTemplate.opsForValue()
                .set(authentication.getName(), tokenDto.getRefreshToken(), tokenDto.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);
        return tokenDto;
    }

    public void validateDuplicateEmail(String email) {
        Optional<Members> newMember = membersRepository.findByEmail(email);
        if (newMember.isPresent()) {
            throw new ApiException(HttpStatus.UNPROCESSABLE_ENTITY, "이미 가입된 회원입니다.");
        }
    }

    public String validateDuplicateNickName(String nickname) {
        Optional<Members> newMember = membersRepository.findByNickName(nickname);
        if (newMember.isPresent()) {
            throw new ApiException(HttpStatus.EXPECTATION_FAILED, "사용중인 닉네임입니다.");
        } else {
            return nickname;
        }
    }

    public String getDefaultAddress(String email) {
        String address = membersRepository.findByEmail(email).get().getDefaultDeliveryAddress();
        if (address == null) {
            throw new ApiException(HttpStatus.EXPECTATION_FAILED, "기본 배송지가 없습니다.");
        }
        return address;
    }

    public void confirmCertification(ConfirmCertificationRequestDto confirmCertificationRequestDto) {
        if (!redisUtil.getData(confirmCertificationRequestDto.getEmail()).equals(confirmCertificationRequestDto.getCertification())) {
            throw new ApiException(HttpStatus.EXPECTATION_FAILED, "인증 번호가 틀렸습니다.");
        }
    }

    public TokenDto reissueToken(RefreshRequestDto refreshRequestDto) {
//        if (!jwtProvider.validateToken(refreshRequestDto.getRefreshToken())) {
//            throw new IllegalStateException("Refresh Token 정보가 유효하지 않습니다.");
//        }
        // AccessToken에서 Member ID 가져오기
        Authentication authentication = jwtProvider.getAuthentication(refreshRequestDto.getAccessToken());
        // 저장소에서 Member ID 기반으로 Refresh Token 값 가져오기
        String refreshToken = (String) redisTemplate.opsForValue().get(authentication.getName());
        // Refresh 토큰 일치하는지 검사
        if (!refreshToken.equals(refreshRequestDto.getRefreshToken())) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "토큰의 유저 정보가 일치하지 않습니다.");
        }

//        Members member = membersRepository.findByEmail(claims.get("email").toString()).get();

        // 새로운 토큰 생성
        TokenDto newToken = jwtProvider.generateToken(authentication);

        redisTemplate.opsForValue()
                .set(authentication.getName(), newToken.getRefreshToken(), newToken.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

        // 토큰 발급
        return newToken;
    }

    // 비밀번호 변경
    @Transactional
    public Long editPassword(EditPasswordRequestDto editPasswordRequestDto) {
        Optional<Members> members = membersRepository.findByEmail(editPasswordRequestDto.getEmail());

        return members.get().editPassword(passwordEncoder.encode(editPasswordRequestDto.getPassword()));
    }
}
