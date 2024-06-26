package com.swmarastro.mykkumiserver.domain.banner.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Schema(description = "홈화면 상단 배너 전체 List DTO")
@Getter
@Builder
public class BannerListResponse {

    private List<BannerDto> banners;
}
