package com.finance.service;

import java.security.InvalidParameterException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finance.common.JwtUtil;
import com.finance.dto.ApiResponse;
import com.finance.dto.LoginResponse;
import com.finance.model.User;
import com.finance.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired // 이것에 대한 정확한 이해 필요.
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtutil;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Transactional
    public ApiResponse registerUser(User userRequest) {
        logger.info("registerUser entered");
        
        try {
            validateSignupRequest(userRequest);

            User user = new User(userRequest.getUserId(),
                                passwordEncoder.encode(userRequest.getPassword()),
                                userRequest.getName(),
                                userRequest.getIdType(),
                                passwordEncoder.encode(userRequest.getIdValue()));

            userRepository.save(user);
            return  new ApiResponse(200, "OK");

        } catch (InvalidParameterException e) {
            return new ApiResponse(400, e.getMessage());
        }   
    }

    private void validateSignupRequest(User userRequest) {
        validateUserId(userRequest.getUserId());
        validateIdType(userRequest.getIdType());
    }

    private void validateUserId(String userId) {
        if (userRepository.existsByUserId(userId)) {
            throw new InvalidParameterException("이미 존재하는 userId입니다.");
        }
    }

    private void validateIdType(User.IdType idType) {
        logger.info("This is an info log message=====");
        if (idType != User.IdType.REG_NO && idType != User.IdType.BUSINESS_NO) {
            throw new InvalidParameterException("잘못된 파라미터입니다, REG_NO 혹은 BUSINESS_NO만 허용됩니다.");
        }
    }

    
    @Transactional
    public ApiResponse authenticateUser(User userRequest) {
        logger.info("authenticateUser entered");

        try {
            if (userRequest.getUserId() == null || userRequest.getPassword() == null) {
                throw new InvalidParameterException("userId 또는 password가 빈값은 안 됩니다.");
            }

            // 여기서 userId와 password를 확인하여 인증 로직을 추가
            User user = userRepository.findByUserId(userRequest.getUserId())
                .orElseThrow(() -> new InvalidParameterException("유효하지 않은 userId입니다."));
            
            if (!passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
                throw new InvalidParameterException("잘못된 password입니다.");
            }

            // 가상의 토큰 발행 (실제 사용시에는 보안을 고려하여 안전한 방법으로 처리해야 함)
            String accessToken = jwtutil.createJwtToken(userRequest.getUserId());
            return new LoginResponse(200, "OK", accessToken);
        } catch (InvalidParameterException e) {
            return new ApiResponse(400, e.getMessage());
        }   
    }
}
