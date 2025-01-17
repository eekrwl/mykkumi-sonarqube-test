package com.swmarastro.mykkumiserver.banner;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banner_id")
    private Long id;
    private String imageUrl;
    private String detailImageUrl;
}
