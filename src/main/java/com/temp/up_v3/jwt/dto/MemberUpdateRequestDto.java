package com.temp.up_v3.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class MemberUpdateRequestDto {
    private String name;
    private String address;
    private String phone;
    private String email;
}
