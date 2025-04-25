package com.onggiyonggi.domain.review.domain;

import com.onggiyonggi.domain.member.domain.Member;
import com.onggiyonggi.domain.review.dto.request.ReviewRequestDto;
import com.onggiyonggi.domain.store.domain.Store;
import com.onggiyonggi.domain.store.dto.request.StoreRequestDto;
import com.onggiyonggi.global.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(nullable = false)
    private String imageURL;

    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReusableContainerType reusableContainerType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReusableContainerSize reusableContainerSize;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FillLevel fillLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FoodTaste foodTaste;

    @Column(nullable = false)
    private Long likes;

    public static Review toEntity(ReviewRequestDto requestDto) {
        return Review.builder()
            .imageURL(requestDto.getImageURL())
            .content(requestDto.getContent())
            .reusableContainerType(requestDto.getReusableContainerType())
            .reusableContainerSize(requestDto.getReusableContainerSize())
            .fillLevel(requestDto.getFillLevel())
            .foodTaste(requestDto.getFoodTaste())
            .likes(0L)
            .build();
    }

    public void updateMember(Member member) {
        this.member = member;
    }

    public void updateStore(Store store) {
        this.store = store;
    }

    public void increaseLike() {
        this.likes++;
    }

    public void decreaseLike() {
        if (likes > 0) {
            this.likes--;
        }
    }

}
