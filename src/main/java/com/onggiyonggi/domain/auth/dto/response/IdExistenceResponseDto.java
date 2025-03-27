package com.onggiyonggi.domain.auth.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class IdExistenceResponseDto {

    private Boolean isExist;

}
