package com.example.activememory.user.infrastructure.acl.kakao;

import com.example.activememory.global.exception.CustomException;
import com.example.activememory.global.exception.ExceptionCode;
import com.example.activememory.user.application.command.port.OAuthPort;
import com.example.activememory.user.application.command.port.dto.GetOAuthTokenReqDto;
import com.example.activememory.user.application.command.port.dto.GetUserInformationResDto;
import com.example.activememory.user.infrastructure.acl.kakao.dto.GetKakaoUserInformationResDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
public class KakaoAdapter implements OAuthPort {
    private final RestClient rest;

    @Value("${oauth.kakao.rest.api.key}")
    private String clientId;

    @Value("${oauth.kakao.redirect.url}")
    private String redirectUrl;

    public KakaoAdapter(RestClient restClient) {
        this.rest = restClient;
    }

    @Override
    public String getOAuthToken(GetOAuthTokenReqDto dto) {
        return "";
    }

    @Override
    public GetUserInformationResDto getUserInformationByToken(String OAuthToken) {
        String infoUrl = "https://kapi.kakao.com/v2/user/me";
//        String termUrl = "https://kapi.kakao.com/v2/user/service_terms";

        try {
            GetKakaoUserInformationResDto userInfo = rest.get()
                    .uri(infoUrl)
                    .header("Authorization", "Bearer " + OAuthToken)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                    .retrieve()
                    .body(GetKakaoUserInformationResDto.class);

            assert userInfo != null;
            return new GetUserInformationResDto(userInfo.getId().toString());
        } catch (RestClientException e) {
            throw new CustomException(ExceptionCode.INTERNAL_SERVER_ERROR, "카카오 사용자 정보 요청 실패. 사유: " + e.getMessage());
        }
    }
}
