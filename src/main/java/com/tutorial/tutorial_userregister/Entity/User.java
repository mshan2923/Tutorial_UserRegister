package com.tutorial.tutorial_userregister.Entity;

import com.tutorial.tutorial_userregister.Dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Entity// Create TABLE 없이 이걸로 자동 생성
@Table(name = "tutorial_register")//Table 생성
@Getter
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class) // 생성/수정 시간을 자동으로 반영하도록 설정
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userNo;
    @Column(nullable = false)
    private String userid;
    @Column(nullable = false)
    private String pw;
    private String userAuth;
    @CreatedDate // 생성일자임을 나타냅니다.
    private LocalDateTime CreateAt;
    @LastModifiedDate // 마지막 수정일자임을 나타냅니다.
    private LocalDateTime UpdateAt;


    public void Crypt()
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        pw = passwordEncoder.encode(getPw());
    }
    public User(UserRequestDto data)
    {
        this.userid = data.getUserid();
        this.pw = data.getPw();
    }
}
