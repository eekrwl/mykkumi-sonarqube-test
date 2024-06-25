package com.swmarastro.mykkumiserver.domain.banner.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "배너 1개 DTO")
@Getter
@Builder
public class BannerDto {

    private Long id;
    private String imageUrl;
}
