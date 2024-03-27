package com.finance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance.model.User;

public interface TransferRepository extends JpaRepository<User, Long> {

    boolean existsByUserId(String userId);

}
