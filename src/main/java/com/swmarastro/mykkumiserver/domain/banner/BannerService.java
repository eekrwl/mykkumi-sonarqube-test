package com.swmarastro.mykkumiserver.domain.banner;

import com.swmarastro.mykkumiserver.domain.banner.dto.BannerDto;
import com.swmarastro.mykkumiserver.domain.banner.dto.BannerListResponse;
import com.swmarastro.mykkumiserver.global.exception.CommonException;
import com.swmarastro.mykkumiserver.global.exception.ErrorCode;
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

    public BannerDto getBannerById(Long bannerId) {
        Banner banner = bannerRepository.findById(bannerId)
                .orElseThrow(() -> new CommonException(ErrorCode.BANNER_NOT_FOUND,"존재하지 않는 배너입니다.","해당 id의 배너를 찾을 수 없습니다."));
        return BannerDto.builder()
                .id(banner.getId())
                .imageUrl(banner.getDetailImageUrl())
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
