package com.example.up_final.domain.contest.service;

import com.example.up_final.domain.contest.domain.Contest;
import com.example.up_final.domain.contest.domain.repo.ContestRepository;
import com.example.up_final.domain.contest.presentation.dto.req.ContestCreateReq;
import com.example.up_final.domain.contest.presentation.dto.req.ContestSearchReq;
import com.example.up_final.domain.contest.presentation.dto.res.ContestListRes;
import com.example.up_final.domain.contest.presentation.dto.res.ContestRes;
import com.example.up_final.global.common.response.Response;
import com.example.up_final.global.image.service.ImageService;
import com.example.up_final.global.jwt.auth.UserSessionHolder;
import com.example.up_final.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContestService {

    private final ContestRepository contestRepo;
    private final UserSessionHolder userSessionHolder;
    private final ContestMapper mapper;
    private final ImageService imageService;

    public Response saveContest(ContestCreateReq req) {
        Member user = userSessionHolder.getUser();
        Contest contest = mapper.toContest(req, user);

        contestRepo.save(contest);

        return Response.created("대회 게시물이 정상적으로 생성되었습니다.");
    }

    public List<ContestListRes> getContestList(ContestSearchReq req) {
        return contestRepo.findAll(ContestSpec.search(req))
                .stream()
                .map(ContestListRes::from)
                .toList();
    }

    public ContestRes getContest(Long contestId) {
        Contest contest = contestRepo.findById(contestId).orElseThrow();

        return mapper.toContestRes(contest);
    }

    public Response updateContest(Long contestId, ContestCreateReq req) {
        Contest contest = contestRepo.findById(contestId).orElseThrow();

        mapper.updateContest(contest, req);

        return Response.ok("정상적으로 수정되었습니다.");
    }

    public Response deleteContest(Long contestId) {
        Contest contest = contestRepo.findById(contestId).orElseThrow();

        imageService.deleteImage(contest.getImage_path());
        contestRepo.deleteById(contestId);

        return Response.ok("정상적으로 삭제되었습니다.");
    }

    public Response likeContest(Long contestId) {
        Member presentUser = userSessionHolder.getUser();
        Contest contest = contestRepo.findById(contestId).orElseThrow();

        boolean isLiked = presentUser.getLikedCommunity().contains(contestId);

        if (isLiked) {
            presentUser.getLikedContest().remove(contestId);
        } else {
            presentUser.getLikedContest().add(contestId);
        }

        contest.updateLike_num(contest.getLike_num() + (isLiked ? -1 : 1));

        return Response.ok("좋아요!");
    }

}
