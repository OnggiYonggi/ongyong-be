package com.onggiyonggi.domain.store.repository;

import com.onggiyonggi.domain.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

}