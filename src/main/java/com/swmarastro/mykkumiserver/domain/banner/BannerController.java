package com.swmarastro.mykkumiserver.domain.banner;

import com.swmarastro.mykkumiserver.domain.banner.dto.BannersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/banners")
public class BannerController {

    private final BannerService bannerService;

    @GetMapping
    public ResponseEntity<BannersResponse> getBanners() {
        return ResponseEntity.ok(bannerService.getAllBanners());
    }
}
