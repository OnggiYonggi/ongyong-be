package com.onggiyonggi.domain.collection.repository;

import com.onggiyonggi.domain.collection.domain.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<Collection, Long> {

    List<Collection> findAllByMemberId(String memberId);

    Collection findByMemberIdAndNaturalMonumentCharacterId(String memberId, Long characterId);

}
