package com.example.activememory.reference.infrastructure.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record KakaoKeywordSearchResponse(
        Meta meta,
        List<Document> documents
) {
    public record Meta(
            @JsonProperty("total_count") Integer totalCount, // 검색어에 검색된 문서 수
            @JsonProperty("pageable_count") Integer pageableCount, // total_count 중 노출 가능 문서 수(최대 45)
            @JsonProperty("is_end") Boolean isEnd, // 마지막 페이지 여부
            @JsonProperty("same_name") SameName sameName // 질의어 지역 및 키워드 분석 정보

    ) {
    }

    public record SameName(
            List<String> region, // 질의어에서 인식된 지역 리스트 - '중앙로 맛집'이라고 검색 시 중앙로에 해당하는 지역 리스트
            String keyword, // 질의어에서 지역 정보를 제외한 키워드 - '중앙로 맛집'에서 '맛집'
            @JsonProperty("selected_region") String selectedRegion // 인식된 지역 리스트 중, 현재 검색에 사용된 지역 정보
    ) {
    }

    public record Document(
            String id, // 장소 id
            @JsonProperty("place_name") String placeName, // 장소명, 업체명
            @JsonProperty("category_name") String categoryName, // 카테고리 이름
            @JsonProperty("category_group_code") String categoryGroupCode, // 중요 카테고리만 그룹핑한 카테고리 그룹 코드
            @JsonProperty("category_group_name") String categoryGroupName, // 중요 카테고리만 그룹핑한 카테고리 그룹명
            String phone, // 전화번호
            @JsonProperty("address_name") String addressName, // 전체 지번 주소
            @JsonProperty("road_address_name") String roadAddressName, // 전체 도로명 주소
            String x, // 경도
            String y, // 위도
            @JsonProperty("place_url") String placeUrl, // 장소 상세페이지 URL
            String distance // 중심좌표까지의 거리 - meter

    ) {
    }
}
