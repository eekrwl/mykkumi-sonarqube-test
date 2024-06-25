package com.swmarastro.mykkumiserver.domain.banner;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banner_id")
    private Long id;
    private String image_url;
}
