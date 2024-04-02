package com.finance.service;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.Optional;

// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finance.common.ApiResponse;
import com.finance.model.User;
import com.finance.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // @Autowired
    // public BCryptPasswordEncoder letterEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Transactional
    public ApiResponse registerUser(User userRequest) {
        logger.info("registerUser entered");
        
        try {
            validateSignupRequest(userRequest);

            User user = new User(userRequest.getUserId(),
                                encryptLetter(userRequest.getPassword()),
                                userRequest.getName(),
                                userRequest.getIdType(),
                                encryptLetter(userRequest.getIdValue()));

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

    private String encryptLetter(String letter) {
        // Password 암호화 로직을 적용
        // return letterEncoder.encode(letter);
        return letter;
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
            
            if (!user.getPassword().equals(encryptLetter(userRequest.getPassword()))) {
                throw new InvalidParameterException("잘못된 비밀번호입니다.");
            }

            // 가상의 토큰 발행 (실제 사용시에는 보안을 고려하여 안전한 방법으로 처리해야 함)
            String accessToken = generateAccessToken(userRequest.getUserId());
            return new ApiResponse(200, "OK");
        } catch (InvalidParameterException e) {
            return new ApiResponse(400, e.getMessage());
        }   
    }

    private String generateAccessToken(String userId) {
        // 30분 후에 만료되는 가상의 access_token 발행
        // Date expiration = new Date(System.currentTimeMillis() + (30 * 60 * 1000));
        // return Jwts.builder()
        //         .setSubject(userId)
        //         .setExpiration(expiration)
        //         .signWith(SignatureAlgorithm.HS256, "secretKey") // secretKey는 보안을 위해 안전한 값으로 대체해야 함
        //         .compact();
        return "tWTO4dkT8SGtoirC0z84k3/X+/vqWrAw9YHzEH+PQG8=";
    }
}
