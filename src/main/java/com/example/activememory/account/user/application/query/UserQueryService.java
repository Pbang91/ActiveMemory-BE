package com.example.activememory.account.user.application.query;

import com.example.activememory.account.user.application.query.model.UserMeReadModel;
import com.example.activememory.global.exception.BusinessException;
import com.example.activememory.global.exception.ExceptionCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserQueryService {
    private final UserQueryRepository userQueryRepository;

    public UserQueryService(UserQueryRepository userQueryRepository) {
        this.userQueryRepository = userQueryRepository;
    }

    public UserMeReadModel getMe(Long userId) {
        return userQueryRepository.findMe(userId).orElseThrow(() -> new BusinessException(ExceptionCode.INVALID_TOKEN));
    }
}
