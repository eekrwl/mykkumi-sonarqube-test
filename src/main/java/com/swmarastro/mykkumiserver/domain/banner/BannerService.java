package com.swmarastro.mykkumiserver.domain.banner;

import com.swmarastro.mykkumiserver.domain.banner.dto.BannerDto;
import com.swmarastro.mykkumiserver.domain.banner.dto.BannerListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository bannerRepository;

    public BannerListResponse getAllBanners() {
        List<BannerDto> bannerList = getSimpleBanners();
        return BannerListResponse.builder()
                .banners(bannerList)
                .build();
    }

    private List<BannerDto> getSimpleBanners() {
        List<Banner> bannerList = bannerRepository.findAll();
        return bannerList.stream()
                .map(banner -> BannerDto.builder()
                        .id(banner.getId())
                        .imageUrl(banner.getImageUrl())
                        .build())
                .collect(Collectors.toList());
    }

}
