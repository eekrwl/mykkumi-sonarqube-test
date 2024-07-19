package com.swmarastro.mykkumiserver.category;

import com.swmarastro.mykkumiserver.user.User;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class UserSubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_sub_category")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id", nullable = false)
    private SubCategory subCategory;
}
