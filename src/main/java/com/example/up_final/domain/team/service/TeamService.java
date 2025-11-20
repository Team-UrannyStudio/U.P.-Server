package com.example.up_final.domain.team.service;

import com.example.up_final.domain.team.domain.Team;
import com.example.up_final.domain.team.domain.repo.TeamRepository;
import com.example.up_final.domain.team.presentation.dto.req.TeamCreateReq;
import com.example.up_final.domain.team.presentation.dto.req.TeamSearchReq;
import com.example.up_final.domain.team.presentation.dto.res.TeamListRes;
import com.example.up_final.domain.team.presentation.dto.res.TeamRes;
import com.example.up_final.global.common.response.Response;
import com.example.up_final.global.image.service.ImageService;
import com.example.up_final.global.jwt.auth.UserSessionHolder;
import com.example.up_final.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {

    private final TeamRepository teamRepo;
    private final UserSessionHolder userSessionHolder;
    private final ImageService imageService;

    public Response saveTeam(TeamCreateReq req) {
        Member writer = userSessionHolder.getUser();
        Team team = TeamCreateReq.toTeam(req, writer);

        teamRepo.save(team);

        return Response.created("구인공고 게시물이 정상적으로 생성되었습니다.");
    }

    public List<TeamListRes> getTeams(TeamSearchReq req) {
        return teamRepo.findAll(TeamSpec.search(req))
                .stream()
                .map(TeamListRes::from)
                .toList();
    }

    public TeamRes getTeam(Long teamId) {
        Team team = teamRepo.findById(teamId).orElseThrow();

        return TeamRes.from(team);
    }

    public Response updateTeam(Long teamId, TeamCreateReq req) {
        Team team = teamRepo.findById(teamId).orElseThrow();

        team.update(req);

        return Response.ok("정상적으로 수정되었습니다.");
    }

    public Response deleteTeam(Long teamId) {
        Team team = teamRepo.findById(teamId).orElseThrow();

        imageService.deleteImage(team.getImage_path());
        teamRepo.deleteById(teamId);

        return Response.ok("정상적으로 삭제되었습니다.");
    }

    public Response likeTeam(Long teamId) {
        Member presentUser = userSessionHolder.getUser();
        Team team = teamRepo.findById(teamId).orElseThrow();

        boolean isLiked = presentUser.getLikedTeam().contains(teamId);

        if (isLiked) {
            presentUser.getLikedTeam().remove(teamId);
        } else {
            presentUser.getLikedTeam().add(teamId);
        }

        team.updateLike_num(team.getLike_num() + (isLiked ? -1 : 1));

        return Response.ok("좋아요!");
    }
}
