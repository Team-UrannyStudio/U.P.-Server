package com.example.up_final.global.jwt.service;

import com.example.up_final.domain.member.presentation.dto.req.MemberCreateReq;
import com.example.up_final.global.common.response.Response;
import com.example.up_final.global.jwt.auth.AuthDetails;
import com.example.up_final.domain.member.domain.Member;
import com.example.up_final.domain.member.domain.repo.MemberRepository;
import com.example.up_final.global.jwt.domain.RefreshToken;
import com.example.up_final.global.jwt.domain.repo.RefreshTokenRepository;
import com.example.up_final.global.jwt.presentation.dto.req.LoginReq;
import com.example.up_final.global.jwt.presentation.dto.req.TokenReq;
import com.example.up_final.global.jwt.presentation.dto.res.TokenRes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    // 회원가입
    public Response signup(MemberCreateReq req) {

        if (memberRepository.existsById(req.uid())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

        req.toMember(passwordEncoder);

        return Response.created("회원가입이 왑료되었습니다.");
    }

    // 로그인
    public TokenRes login(LoginReq req) {

        // 1) uid + password → AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                req.toAuthentication();

        // 2) DB 검증 → CustomUserDetailsService.loadUserByUsername 실행됨
        Authentication authentication =
                authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3) Member 조회
        Member member = memberRepository.findById(req.uid())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 4) AuthDetails 기반 Authentication 재구성
        AuthDetails authDetails = new AuthDetails(member);

        Authentication newAuth = new UsernamePasswordAuthenticationToken(
                authDetails,
                authentication.getCredentials(),
                authentication.getAuthorities()
        );

        // 5) JWT 생성
        TokenRes tokenRes = tokenProvider.generateTokenRes(newAuth);

        // 6) Refresh Token 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(member.getUid())
                .value(tokenRes.refreshToken())   // record 방식
                .build();

        refreshTokenRepository.save(refreshToken);

        // 7) 토큰 반환
        return tokenRes;
    }

    // 토큰 재발급
    public TokenRes reissue(TokenReq req) {

        // 1) Refresh Token 유효성 검사
        if (!tokenProvider.validateToken(req.refreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2) Access Token에서 사용자 정보 추출
        Authentication authentication =
                tokenProvider.getAuthentication(req.accessToken());

        // 3) DB에 저장된 Refresh Token 조회
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // 4) Refresh Token 일치 여부 체크
        if (!refreshToken.getValue().equals(req.refreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5) 새로운 JWT 발급
        TokenRes tokenRes = tokenProvider.generateTokenRes(authentication);

        // 6) Refresh Token 갱신
        RefreshToken newRefreshToken =
                refreshToken.updateValue(tokenRes.refreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 7) 최종 반환
        return tokenRes;
    }
}
