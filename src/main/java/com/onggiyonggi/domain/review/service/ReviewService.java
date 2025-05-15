package com.onggiyonggi.domain.review.service;

import com.onggiyonggi.domain.item.service.ItemService;
import com.onggiyonggi.domain.like.service.LikeService;
import com.onggiyonggi.domain.member.domain.Member;
import com.onggiyonggi.domain.review.domain.Review;
import com.onggiyonggi.domain.review.dto.request.ReviewRequestDto;
import com.onggiyonggi.domain.review.dto.response.ReviewPreviewResponseDto;
import com.onggiyonggi.domain.review.dto.response.ReviewResponseDto;
import com.onggiyonggi.domain.review.repository.ReviewRepository;
import com.onggiyonggi.domain.store.domain.Store;
import com.onggiyonggi.domain.store.facade.StoreReviewFacade;
import com.onggiyonggi.domain.store.service.StoreService;
import com.onggiyonggi.global.auth.CustomUserDetails;
import com.onggiyonggi.global.response.CursorPageResponse;
import com.onggiyonggi.global.response.GeneralException;
import com.onggiyonggi.global.response.Status;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreService storeService;
    private final LikeService likeService;

    public Long createReview(ReviewRequestDto requestDto, CustomUserDetails customUserDetails) {
        Review review = Review.toEntity(requestDto);
        Member member = customUserDetails.getMember();
        Long storeId = requestDto.getStoreId();
        Store store = storeService.getStore(storeId);
        review.updateMember(member);
        review.updateStore(store);
        return save(review).getId();
    }

    public ReviewResponseDto getReviewDetail(Long id, CustomUserDetails customUserDetails) {
        Review review = getReviewById(id);
        Member member = customUserDetails.getMember();
        ReviewResponseDto responseDto = ReviewResponseDto.toDto(review);
        Boolean hasLikeByMe = likeService.isExistByReviewIdAndMemberId(id, member.getId());
        responseDto.setHasLikeByMe(hasLikeByMe);
        return responseDto;
    }

    public List<ReviewPreviewResponseDto> getMyReviewPreview(CustomUserDetails customUserDetails) {
        Member member = customUserDetails.getMember();
        return getReviewListByMemberId(member.getId()).stream()
            .map(ReviewPreviewResponseDto::from)
            .collect(Collectors.toList());
    }

    public Review getReviewEntityById(Long id) {
        return getReviewById(id);
    }

    public Review saveReviewEntity(Review review){
        return save(review);
    }

    public CursorPageResponse<ReviewResponseDto> getPagedReviewsByStoreId(Long storeId, LocalDateTime cursor, int size) {
        List<Review> reviews;

        if (cursor == null) {
            reviews = findByStoreIdOrderByCreatedAtDesc(storeId, size);
        } else {
            reviews = findByStoreIdAndCreatedAtLessThanOrderByCreatedAtDesc(
                storeId, cursor, size);
        }
        Boolean hasNext = hasNextAndTrim(reviews, size);
        LocalDateTime nextCursor = reviews.isEmpty() ? null : reviews.get(reviews.size() - 1).getCreatedAt();
        List<ReviewResponseDto> responseDtos = reviews.stream()
            .map(ReviewResponseDto::toDto)
            .toList();
        return new CursorPageResponse<>(responseDtos, nextCursor, hasNext);
    }

    private List<Review> findByStoreIdOrderByCreatedAtDesc(Long storeId, int size) {
        return reviewRepository.findByStoreIdOrderByCreatedAtDesc(
            storeId, PageRequest.of(0, size + 1));
    }

    public int getReviewCountByStoreId(Long storeId) {
        log.info(countByStoreId(storeId) + "");
        return countByStoreId(storeId);
    }

    private boolean hasNextAndTrim(List<?> list, int size) {
        if (list.size() > size) {
            list.remove(list.size() - 1);
            return true;
        }
        return false;
    }

    private Review save(Review review) {
        return reviewRepository.save(review);
    }

    private Review getReviewById(Long id) {
        return reviewRepository.findById(id)
            .orElseThrow(() -> new GeneralException(Status.REVIEW_NOT_FOUND));
    }

    private List<Review> getReviewListByMemberId(String memberId) {
        return reviewRepository.findAllByMemberId(memberId);
    }

    private List<Review> findByStoreIdAndCreatedAtLessThanOrderByCreatedAtDesc(Long storeId, LocalDateTime cursor, int size) {
        return reviewRepository.findByStoreIdAndCursor(
            storeId, cursor, PageRequest.of(0, size + 1));
    }

    private int countByStoreId(Long storeId) {
        return reviewRepository.countByStoreId(storeId);
    }

}
