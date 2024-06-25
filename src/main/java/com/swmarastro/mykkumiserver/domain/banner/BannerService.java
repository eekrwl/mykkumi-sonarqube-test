package com.swmarastro.mykkumiserver.domain.banner;

import com.swmarastro.mykkumiserver.domain.banner.dto.BannersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository bannerRepository;

    public BannersResponse getAllBanners() {
        return BannersResponse.builder()
                .banners(bannerRepository.findAll())
                .build();
    }

}
