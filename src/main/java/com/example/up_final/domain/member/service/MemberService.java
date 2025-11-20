package com.example.up_final.domain.member.service;

import com.example.up_final.domain.contest.domain.Contest;
import com.example.up_final.domain.member.domain.Member;
import com.example.up_final.domain.member.domain.repo.MemberRepository;
import com.example.up_final.domain.member.presentation.dto.req.MemberUpdateReq;
import com.example.up_final.domain.member.presentation.dto.res.MemberRes;
import com.example.up_final.global.common.response.Response;
import com.example.up_final.global.image.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepo;
    private final ImageService imageService;

    public MemberRes findOneMember(String uid) {
        Member user = memberRepo.findById(uid).orElseThrow();

        return MemberRes.from(user);
    }

    public Response updateMember(String uid, MemberUpdateReq req) {
        Member user = memberRepo.findById(uid).orElseThrow();

        user.update(req);

        return Response.ok("정상적으로 수정되었습니다.");
    }

    public Response deleteMember(String uid) {
        Member user = memberRepo.findById(uid).orElseThrow();

        imageService.deleteImage(user.getProfile());
        memberRepo.deleteById(uid);

        return Response.ok("정상적으로 삭제되었습니다.");
    }
}
