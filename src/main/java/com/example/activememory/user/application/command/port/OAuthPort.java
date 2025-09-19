package com.example.activememory.user.application.command.port;

import com.example.activememory.user.application.command.port.dto.GetOAuthTokenReqDto;
import com.example.activememory.user.application.command.port.dto.GetUserInformationResDto;

public interface OAuthPort {
    String getOAuthToken(GetOAuthTokenReqDto dto);

    GetUserInformationResDto getUserInformationByToken(String OAuthToken);
}
