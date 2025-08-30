package com.example.activememory.user.domain.user.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;

@Embeddable
@Getter
public class Profile {
    @Column(name = "nickname", length = 30, unique = true, nullable = false)
    private String nickname;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "bio")
    private String bio;

    protected Profile() {}

    private Profile(String nickname, String imageUrl, String bio) {
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.bio = bio;
    }

    public static Profile of(String nickname, String imageUrl, String bio) {
        String nn = normalizeNickname(nickname);
        validateNickname(nn);
        String iu = normalizeImageUrl(imageUrl);
        String b  = normalizeBio(bio);
        return new Profile(nn, iu, b);
    }

    // 선택: 기본값 프로필 팩토리
    public static Profile ofNicknameOnly(String nickname) {
        return of(nickname, null, null);
    }

    private static String normalizeNickname(String raw) {
        if (raw == null) throw new IllegalArgumentException("nickname은 필수");
        return raw.trim();
    }
    private static void validateNickname(String nn) {
        if (nn.isEmpty()) throw new IllegalArgumentException("nickname은 비어있을 수 없음");
        if (nn.length() > 30) throw new IllegalArgumentException("nickname은 30자 이하");
        // TODO: 금칙어/허용 문자 등 규칙 추가?
    }
    private static String normalizeImageUrl(String raw) {
        if (raw == null || raw.isBlank()) return null;
        return raw.trim();
    }
    private static String normalizeBio(String raw) {
        if (raw == null || raw.isBlank()) return null;
        return raw.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null)  return false;
        if (!(o instanceof Profile other)) return false;
        return Objects.equals(nickname, other.nickname)
                && Objects.equals(imageUrl, other.imageUrl)
                && Objects.equals(bio, other.bio);
    }
    @Override
    public int hashCode() {
        return Objects.hash(nickname, imageUrl, bio);
    }
}
