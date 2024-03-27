package com.finance.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import com.finance.common.AuditableEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "quote")
public class Quote extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision=15, scale=2, nullable = false)
    private BigDecimal amount;

    @Column(nullable = false, length = 5)
    private String targetCurrency;

    @Column(precision=15, scale=2, nullable = false)
    private BigDecimal exchangeRate;

    @Column(nullable = false)
    private Instant expireTime;

    @Column(precision=15, scale=2, nullable = false)
    private String targetAmount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // getters and setters...
}
