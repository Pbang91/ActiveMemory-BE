package com.example.activememory.account.domain.vo;

import com.example.activememory.global.exception.CustomException;
import com.example.activememory.global.exception.ExceptionCode;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Profile {
    @Column(name = "nickname", length = 20, nullable = false)
    private String nickname;

    @Column(name = "bio")
    private String bio;

    protected Profile() {}

    private Profile(String nickname, String bio) {
        if (nickname == null || nickname.isEmpty()) {
            throw new CustomException(ExceptionCode.INVALID_PARAMETER, "nickname은 필수값입니다");
        }

        nickname = nickname.trim();

        if (nickname.length() > 20) {
            throw new CustomException(ExceptionCode.INVALID_PARAMETER, "nickname은 20자 이하로 지정해야 합니다");
        }

        this.nickname = nickname;
        this.bio = bio;
    }

    public static Profile of(String nickname, String bio) {
        return new Profile(nickname, bio);
    }
}
