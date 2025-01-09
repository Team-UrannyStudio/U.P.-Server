package com.temp.up_v3.controller;

import com.temp.up_v3.domain.Member;
import com.temp.up_v3.jwt.dto.MemberRequestDto;
import com.temp.up_v3.jwt.dto.MemberResponseDto;
import com.temp.up_v3.jwt.dto.TokenDto;
import com.temp.up_v3.jwt.dto.TokenRequestDto;
import com.temp.up_v3.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    //requestbody 어노테이션은 요청 body 부분에 있는 내용을 읽어서 처리 아래의 경우 json 형식 또는 xml 방식으로 받아서 requestdto로 변환
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.login(memberRequestDto));
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}