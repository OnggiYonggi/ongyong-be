package com.onggiyonggi.domain.pet.repository;

import com.onggiyonggi.domain.pet.domain.Pet;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {

    Optional<Pet> findByMemberId(String memberId);

}
