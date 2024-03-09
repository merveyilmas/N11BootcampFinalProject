package com.merveyilmaz.userservice.entitiy;

import com.merveyilmaz.userservice.enums.EnumRate;
import com.merveyilmaz.userservice.general.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_reviews")
public class UserReview extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserReview")
    @SequenceGenerator(name = "UserReview", sequenceName = "USER_REVİEW_ID_SEQ")
    @Id
    private Long id;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "RESTAURANT_ID", nullable = false)
    private Long restaurantId;

    @Enumerated(EnumType.STRING)
    @Column(name = "RATE", length = 30, nullable = false)
    private EnumRate rate;

    @Column(name = "COMMENT", length = 200)
    private String comment;
}
