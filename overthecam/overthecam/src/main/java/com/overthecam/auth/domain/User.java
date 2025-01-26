package com.overthecam.auth.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String nickname;

    private String profileImage;

    @Column(unique = true)
    private String email;

    private LocalDate birth;

    @Column(nullable = false)
    private Integer gender;

    private String password;

    @Column(columnDefinition = "INT DEFAULT 100")
    private Integer supportScore = 100;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer point = 0;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Builder
    public User(String nickname, String email, Integer gender, String password) {
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
        this.password = password;
        this.supportScore = 50000;
    }
}