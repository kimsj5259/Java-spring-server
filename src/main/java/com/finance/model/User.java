package com.finance.model;

import java.io.Serializable;

import com.finance.common.AuditableEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false)
    private String idValue;

    @Column(nullable = false)
    @Enumerated
    private IdType idType; // idType 컬럼 추가

    // protected User() {
    //     // no-args constructor required by JPA spec
    //     // this one is protected since it should not be used directly
    // }

    public User(String userId, String password, String name, IdType idType, String idValue) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.idType = idType;
        this.idValue = idValue;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getPassword() {
        return this.password;
    }
    
    public String getName() {
        return this.name;
    }

    public IdType getIdType() {
        return this.idType;
    }

    public String getIdValue() {
        return this.idValue;
    }

    public enum IdType {
        REG_NO, BUSINESS_NO
    }
    
}
