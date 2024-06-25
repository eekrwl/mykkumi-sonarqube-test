package com.swmarastro.mykkumiserver.domain.banner.dto;

import com.swmarastro.mykkumiserver.domain.banner.Banner;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BannersResponse {

    List<Banner> banners;
}
