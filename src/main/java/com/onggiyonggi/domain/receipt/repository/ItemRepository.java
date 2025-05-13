package com.onggiyonggi.domain.receipt.repository;

import com.onggiyonggi.domain.receipt.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
