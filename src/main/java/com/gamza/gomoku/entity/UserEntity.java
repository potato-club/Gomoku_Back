package com.gamza.gomoku.entity;

import com.gamza.gomoku.enumcustom.Tier;
import com.gamza.gomoku.enumcustom.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserEntity extends BaseTimeEntity{
    @Id
    private UUID uid;
    @Column(nullable = false,unique = true)
    private String userEmail;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private UserRole userRole;
    @Column(nullable = false)
    private String refreshToken;
    @Column(nullable = false)
    private Tier tier;
    @Column
    private int score;
    @Column
    private long totalPlay;
    @Column
    private long totalWin;
}
