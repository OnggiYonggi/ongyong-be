package com.onggiyonggi.domain.pet.service;

import com.onggiyonggi.domain.member.domain.Member;
import com.onggiyonggi.domain.pet.domain.Pet;
import com.onggiyonggi.domain.pet.dto.response.MyPetResponseDto;
import com.onggiyonggi.domain.pet.repository.PetRepository;
import com.onggiyonggi.global.auth.CustomUserDetails;
import com.onggiyonggi.global.response.GeneralException;
import com.onggiyonggi.global.response.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    public MyPetResponseDto getMyPet(CustomUserDetails customUserDetails) {
        Member member = customUserDetails.getMember();
        Pet myPet = getPetByMemberId(member.getId());
        return MyPetResponseDto.toDto(myPet);
    }

    private Pet getPetByMemberId(String memberId) {
        return petRepository.findByMemberId(memberId)
            .orElseThrow(() -> new GeneralException(Status.PET_NOT_FOUND));
    }

}
