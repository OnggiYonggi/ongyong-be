package com.onggiyonggi.domain.item.repository;

import com.onggiyonggi.domain.item.domain.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByReviewId(Long reviewId);

}
