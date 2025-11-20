package com.example.up_final.domain.contest.presentation;

import com.example.up_final.domain.contest.presentation.dto.req.ContestCreateReq;
import com.example.up_final.domain.contest.presentation.dto.req.ContestSearchReq;
import com.example.up_final.domain.contest.presentation.dto.res.ContestListRes;
import com.example.up_final.domain.contest.presentation.dto.res.ContestRes;
import com.example.up_final.domain.contest.service.ContestService;
import com.example.up_final.global.common.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/contest")
public class ContestController {

    private final ContestService contestService;

    @PostMapping("/post")
    public Response postContest(@RequestBody ContestCreateReq req) {
        return contestService.saveContest(req);
    }

    @GetMapping("/list")
    @Operation(summary = "각 항목별로 null로 보내면 전체 검색 (전부 비어있는 json으로 보내면 전체 검색)")
    public List<ContestListRes> getContestList(ContestSearchReq req) {
        return contestService.getContestList(req);
    }

    @GetMapping("/{id}")
    public ContestRes getContest(@PathVariable("id") Long id) {
        return contestService.getContest(id);
    }

    @PutMapping("/{id}")
    public Response updateContest(@PathVariable("id") Long id, @RequestBody ContestCreateReq req) {
        return contestService.updateContest(id, req);
    }

    @DeleteMapping("/{id}")
    public Response deleteContest(@PathVariable("id") Long id) {
        return contestService.deleteContest(id);
    }

    @PutMapping("/like/{id}")
    public Response likeContest(@PathVariable("id") Long contestId) {
        return contestService.likeContest(contestId);
    }
}
