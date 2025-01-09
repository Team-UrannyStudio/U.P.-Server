package com.temp.up_v3.jwt.dto;

import com.temp.up_v3.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponseDto {
    private String uid;

    // Member 엔티티에서 DTO 변환
    public static MemberResponseDto of(Member member) {
        return MemberResponseDto.builder()
                .uid(member.getUid())
                .build();
    }
}
