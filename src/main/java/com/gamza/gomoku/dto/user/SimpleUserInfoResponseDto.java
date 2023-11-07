package com.gamza.gomoku.dto.user;

import com.gamza.gomoku.enumcustom.Tier;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleUserInfoResponseDto {
    private String userName;
    private Tier tier;
}
