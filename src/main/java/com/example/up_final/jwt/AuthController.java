package com.example.up_final.jwt;

import com.example.up_final.jwt.dto.*;
import com.example.up_final.jwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    //requestBody 어노테이션은 요청 body 부분에 있는 내용을 읽어서 처리 아래의 경우 json 형식 또는 xml 방식으로 받아서 requestDto 로 변환
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

    @GetMapping("/update")
    public MemberOneResponseDto getOneMember(@RequestBody String uid) {
        return authService.findOneMember(uid);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateMember(@RequestPart String uid, @RequestPart MemberUpdateRequestDto requestDto, @RequestParam MultipartFile image) {
        authService.updateMember(uid, requestDto, image);
        return ResponseEntity.ok("success");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteMember(@RequestParam String uid) {
        authService.deleteMember(uid);
        return ResponseEntity.ok("success");
    }

}