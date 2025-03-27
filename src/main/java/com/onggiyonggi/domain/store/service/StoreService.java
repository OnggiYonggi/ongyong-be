package com.onggiyonggi.domain.store.service;

import com.onggiyonggi.domain.store.domain.Store;
import com.onggiyonggi.domain.store.domain.StoreRank;
import com.onggiyonggi.domain.store.dto.request.StoreRequestDto;
import com.onggiyonggi.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public Long createStore (StoreRequestDto requestDto, StoreRank storeRank) {
        Store store = Store.toEntity(requestDto);
        store.updateStoreRank(storeRank);
        return save(store).getId();
    }

    public Store save(Store store) {
        return storeRepository.save(store);
    }

}
