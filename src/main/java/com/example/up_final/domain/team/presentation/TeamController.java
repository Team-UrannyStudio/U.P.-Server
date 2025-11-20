package com.example.up_final.domain.team.presentation;

import com.example.up_final.domain.team.presentation.dto.req.TeamCreateReq;
import com.example.up_final.domain.team.presentation.dto.req.TeamSearchReq;
import com.example.up_final.domain.team.presentation.dto.res.TeamListRes;
import com.example.up_final.domain.team.presentation.dto.res.TeamRes;
import com.example.up_final.domain.team.service.TeamService;
import com.example.up_final.global.common.response.Response;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/post")
    public Response postTeam(@RequestBody TeamCreateReq req) {
        return teamService.saveTeam(req);
    }

    @GetMapping("/list")
    public List<TeamListRes> getTeams(TeamSearchReq req) {
        return teamService.getTeams(req);
    }

    @GetMapping("/{id}")
    public TeamRes getTeam(@PathVariable("id") Long id) {
        return teamService.getTeam(id);
    }

    @PutMapping("/{id}")
    public Response updateTeam(@PathVariable("id") Long id, @RequestBody TeamCreateReq req) {
        return teamService.updateTeam(id, req);
    }

    @DeleteMapping("/{id}")
    public Response deleteTeam(@PathVariable("id") Long id) {
        return teamService.deleteTeam(id);
    }

    @PutMapping("/like/{id}")
    public Response likeTeam(@PathVariable("id") Long teamId) {
        return teamService.likeTeam(teamId);
    }
}
