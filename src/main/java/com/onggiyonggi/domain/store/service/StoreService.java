package com.onggiyonggi.domain.store.service;

import com.onggiyonggi.domain.store.domain.Store;
import com.onggiyonggi.domain.store.domain.StoreRank;
import com.onggiyonggi.domain.store.dto.request.StorePreviewRequestDto;
import com.onggiyonggi.domain.store.dto.request.StoreRequestDto;
import com.onggiyonggi.domain.store.dto.response.StoreDetailResponseDto;
import com.onggiyonggi.domain.store.dto.response.StorePreviewResponseDto;
import com.onggiyonggi.domain.store.repository.StoreRepository;
import com.onggiyonggi.global.response.GeneralException;
import com.onggiyonggi.global.response.Status;
import java.time.LocalDateTime;
import java.util.List;
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

    public StoreDetailResponseDto getStoreDetail(Long id) {
        Store store = getStoreById(id);
        return StoreDetailResponseDto.toDto(store);
    }

    public List<StorePreviewResponseDto> getNearbyStore(Double latitude, Double longitude, Integer radius) {
        List<Store> stores = findAllByDistance(latitude, longitude, radius);
        return stores.stream()
            .map(StorePreviewResponseDto::toDto)
            .toList();
    }

    public List<StoreDetailResponseDto> searchStores(String keyword){
        return searchStoresByKeyword(keyword).stream()
            .map(StoreDetailResponseDto::toDto)
            .toList();
    }

    public List<Store> searchStoresByKeyword(String keyword) {
        return storeRepository.findByNameContainingKeyword(keyword);
    }

    public Store getStore(Long id) {
        return getStoreById(id);
    }

    public void saveAll(List<Store> rookieStores) {
        saveAllStoreEntity(rookieStores);
    }

    public List<Store> getStoreListByGradeAndCreatedAtBefore(StoreRank storeRank, LocalDateTime duration) {
        return findByGradeAndCreatedAtBefore(storeRank, duration);
    }

    public Store saveStore(Store store) {
        return save(store);
    }

    private List<Store> findByGradeAndCreatedAtBefore(StoreRank storeRank, LocalDateTime duration) {
        return storeRepository.findByRankAndCreatedAtBefore(storeRank, duration);
    }

    private void saveAllStoreEntity(List<Store> rookieStores) {
        storeRepository.saveAll(rookieStores);
    }

    private Store save(Store store) {
        return storeRepository.save(store);
    }

    private Store getStoreById(Long id) {
        return storeRepository.findById(id)
            .orElseThrow(() -> new GeneralException(Status.STORE_NOT_FOUND));
    }

    private List<Store> findAllByDistance(Double latitude, Double longitude, Integer radius) {
        return storeRepository.findStoresWithinRadius(
            latitude, longitude, radius
        );
    }

    public void deleteStore(Long id) {
        Store store = getStoreById(id);
        storeRepository.delete(store);
    }

}
