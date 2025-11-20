package com.example.up_final.domain.community.service;

import com.example.up_final.domain.community.domain.Community;
import com.example.up_final.domain.community.domain.repo.CommunityRepository;
import com.example.up_final.domain.community.presentation.dto.req.CommunityCreateReq;
import com.example.up_final.domain.community.presentation.dto.req.CommunitySearchReq;
import com.example.up_final.domain.community.presentation.dto.res.CommunityListRes;
import com.example.up_final.domain.community.presentation.dto.res.CommunityRes;
import com.example.up_final.global.common.response.Response;
import com.example.up_final.global.image.service.ImageService;
import com.example.up_final.global.jwt.auth.UserSessionHolder;
import com.example.up_final.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepo;
    private final UserSessionHolder userSessionHolder;
    private final ImageService imageService;

    public Response saveCommunity(CommunityCreateReq req) {
        Member user = userSessionHolder.getUser();
        Community community = CommunityCreateReq.toCommunity(req, user);

        communityRepo.save(community);

        return Response.created("커뮤니티 게시물이 정상적으로 생성되었습니다.");
    }

    public List<CommunityListRes> getCommunities(CommunitySearchReq req) {
        return communityRepo.findAll(CommunitySpec.search(req))
                .stream()
                .map(CommunityListRes::from)
                .toList();
    }

    public CommunityRes getCommunity(Long communityId) {
        Community community = communityRepo.findById(communityId).orElseThrow();

        return CommunityRes.from(community);
    }

    public Response updateCommunity(Long communityId, CommunityCreateReq req) {
        Community community = communityRepo.findById(communityId).orElseThrow();

        community.update(req);

        return Response.ok("정상적으로 수정되었습니다.");
    }

    public Response deleteCommunity(Long communityId) {
        Community community = communityRepo.findById(communityId).orElseThrow();

        imageService.deleteImage(community.getImage_path());
        communityRepo.deleteById(communityId);

        return Response.ok("정상적으로 삭제되었습니다.");
    }

    public Response likeCommunity(Long communityId) {
        Member presentUser = userSessionHolder.getUser();
        Community community = communityRepo.findById(communityId).orElseThrow();

        boolean isLiked = presentUser.getLikedCommunity().contains(communityId);

        if (isLiked) {
            presentUser.getLikedCommunity().remove(communityId);
        } else {
            presentUser.getLikedCommunity().add(communityId);
        }

        community.updateLike_num(community.getLike_num() + (isLiked ? -1 : 1));

        return Response.ok("좋아요!");
    }
}
