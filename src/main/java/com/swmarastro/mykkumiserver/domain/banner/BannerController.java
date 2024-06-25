package com.swmarastro.mykkumiserver.domain.banner;

import com.swmarastro.mykkumiserver.domain.banner.dto.BannerListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "banners", description = "홈화면 상단 배너 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/banners")
public class BannerController {

    private final BannerService bannerService;

    @GetMapping
    @Operation(summary = "상단 배너 전체 불러오기", description = "배너id와 배너 이미지 url을 list로 불러옵니다.")
    public ResponseEntity<BannerListResponse> getBanners() {
        return ResponseEntity.ok(bannerService.getAllBanners());
    }
}
