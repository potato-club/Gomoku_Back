package com.gamza.gomoku.service;

import com.gamza.gomoku.dto.user.LoginRequestDto;
import com.gamza.gomoku.dto.user.SignupRequestDto;
import com.gamza.gomoku.dto.user.SimpleUserInfoResponseDto;
import com.gamza.gomoku.dto.user.UserInfoResponseDto;
import com.gamza.gomoku.entity.UserEntity;
import com.gamza.gomoku.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<String> login(LoginRequestDto loginRequestDto, HttpServletResponse response){
        UserEntity userEntity = userRepository.findByUserEmail(loginRequestDto.getUserEmail()).orElseThrow(()->{
            throw new UnAuthorizedException(ErrorCode.INVALID_ACCESS.getMessage(),ErrorCode.INVALID_ACCESS);});

        if ( !passwordEncoder.matches(loginRequestDto.getPassword(),userEntity.getPassword()) ) {
            throw new UnAuthorizedException(ErrorCode.INVALID_ACCESS.getMessage(),ErrorCode.INVALID_ACCESS);
        }

        String accessToken = jwtTokenProvider.generateAccessToken(basicLoginRequestDto.getUserEmail());
        String refreshToken = jwtTokenProvider.generateRefreshToken(basicLoginRequestDto.getUserEmail());
        userEntity.setRefreshToken(refreshToken);
        response.setHeader("Authorization","Bearer " + accessToken);
        response.setHeader("RefreshToken","Bearer "+ refreshToken);
        return ResponseEntity.ok("로그인 성공");
    }
    @Transactional
    public ResponseEntity<String> signUp(SignupRequestDto signupRequestDto, HttpServletResponse response){
        if (userRepository.existsByUserEmail(signupRequestDto.getUserEmail())) {
            throw new RuntimeException();
        }

        UserEntity userEntity = new UserEntity().builder()
                .userName(signupRequestDto.getUserName())
                .userEmail(signupRequestDto.getUserEmail())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .build();

        return ResponseEntity.ok("회원가입 성공");
    }
    //===========UserInfo 관련=======================
    public SimpleUserInfoResponseDto getSimpleUserInfo(HttpServletRequest request) {

    }

    public UserInfoResponseDto getUserInfo(HttpServletRequest request) {

    }

}
