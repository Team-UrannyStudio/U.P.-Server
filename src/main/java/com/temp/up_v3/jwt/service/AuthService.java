package com.temp.up_v3.jwt.service;

import com.temp.up_v3.comment.CommentService;
import com.temp.up_v3.comment.dto.CommentListResponseDto;
import com.temp.up_v3.image.ImageDetails;
import com.temp.up_v3.image.ImageInfo;
import com.temp.up_v3.image.ImageRepository;
import com.temp.up_v3.image.ImageService;
import com.temp.up_v3.jwt.Member;
import com.temp.up_v3.jwt.MemberRepository;
import com.temp.up_v3.jwt.RefreshToken;
import com.temp.up_v3.jwt.RefreshTokenRepository;
import com.temp.up_v3.jwt.dto.*;
import com.temp.up_v3.post.PostRepository;
import com.temp.up_v3.post.PostService;
import com.temp.up_v3.post.dto.PostListResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final CommentService commentService;
    private final PostService postService;
    private final ImageService imageService;
    private final ImageRepository imageRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public MemberResponseDto signup(MemberRequestDto memberRequestDto) {
        if (memberRepository.existsByUid(memberRequestDto.getUid())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

        ImageDetails imageDetails = new ImageDetails("https://uranny.s3.ap-northeast-2.amazonaws.com/basic+profile+image.png", "basic+profile+image.png");
        imageDetails.setContentId(memberRequestDto.getUid());
        imageRepository.save(imageDetails);

        Member member = memberRequestDto.toMember(passwordEncoder);
        return MemberResponseDto.of(memberRepository.save(member));
    }

    public TokenDto login(MemberRequestDto memberRequestDto) {
        // 1. email과 password 즉, memberRequestDto를 사용하여 authenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = memberRequestDto.toAuthentication();

        // 2. 실제로 검증 (위에서 만든 authenticationToken을 활용해서 데이터베이스의 내용과 일치하는 지 확인)
        // CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드도 함께 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 받아오기
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 5. 토큰 발급
        return tokenDto;
    }

    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDto;
    }

    //한 개 찾기
    public MemberOneResponseDto findOneMember(String uid) {

        Member member = memberRepository.findByUid(uid).orElseThrow(
                () -> new IllegalArgumentException("조회 실패")
        );

        List<PostListResponseDto> likedContentList = postService.memberConverter(member.getLikedContent());
        List<CommentListResponseDto> likedCommentList = commentService.Converter(member.getLikedComment());
        List<CommentListResponseDto> disLikedCommentList = commentService.Converter(member.getDisLiked());

        MemberOneResponseDto responseDto = new MemberOneResponseDto(member);

        responseDto.setLikedContent(likedContentList);
        responseDto.setLikedComment(likedCommentList);
        responseDto.setDislikedComment(disLikedCommentList);

        return responseDto;
    }

    //글 수정
    @Transactional
    public void updateMember(String uid, MemberUpdateRequestDto requestDto, MultipartFile image) {

        ImageDetails imageDetails = imageRepository.findByContentId(uid);

        Member member = memberRepository.findByUid(uid).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );

        if ( !image.getOriginalFilename().equals("basic profile image.png")) {

            imageService.deleteImage(imageDetails.getImageName());

            ImageInfo imageInfo = imageService.uploadImage(image);

            imageDetails.update(imageInfo.getImageUrl(), imageInfo.getImageName());

            member.setProfilePicture(imageInfo.getImageUrl());
        }

        member.update(requestDto);
    }

    //글 삭제
    public void deleteMember(String uid) {

        memberRepository.deleteByUid(uid);

        imageService.deleteImage(imageRepository.findByContentId(uid).getImageName());

        imageRepository.delete(imageRepository.findByContentId(uid));
    }
}
