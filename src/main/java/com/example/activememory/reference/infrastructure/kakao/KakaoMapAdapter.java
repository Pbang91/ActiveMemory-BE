package com.example.activememory.reference.infrastructure.kakao;

import com.example.activememory.reference.domain.gym.service.PlaceSearchPort;
import com.example.activememory.reference.domain.gym.service.dto.PlaceSearchResponse;
import com.example.activememory.reference.infrastructure.kakao.dto.KakaoKeywordSearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class KakaoMapAdapter implements PlaceSearchPort {
    private final RestClient restClient;
    private final String apiKey;

    public KakaoMapAdapter(
            RestClient restClient,
            @Value("${oauth.kakao.rest.api.key}")
            String apiKey
    ) {
        this.restClient = restClient;
        this.apiKey = apiKey;
    }

    @Override
    public List<PlaceSearchResponse> searchByKeyword(String keyword) {
        String KEYWORD_URL = "https://dapi.kakao.com/v2/local/search/keyword.json";

        KakaoKeywordSearchResponse response = restClient.get()
                .uri(KEYWORD_URL + "?query={query}", keyword)
                .header("Authorization", "KakaoAK " + apiKey)
                .retrieve()
                .body(KakaoKeywordSearchResponse.class);

        if (response == null || response.documents() == null || response.documents().isEmpty()) {
            return List.of();
        }

        // TODO: 페이지네이션 기능 추가(?)
        return response.documents().stream().map(doc -> new PlaceSearchResponse(
                "kakao:%s".formatted(doc.id()),
                doc.placeName(),
                doc.addressName(),
                doc.x(),
                doc.y()
        )).toList();
    }
}
