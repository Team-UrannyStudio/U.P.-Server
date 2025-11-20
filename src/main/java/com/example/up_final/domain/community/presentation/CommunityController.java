package com.example.up_final.domain.community.presentation;

import com.example.up_final.domain.community.presentation.dto.req.CommunityCreateReq;
import com.example.up_final.domain.community.presentation.dto.req.CommunitySearchReq;
import com.example.up_final.domain.community.presentation.dto.res.CommunityListRes;
import com.example.up_final.domain.community.presentation.dto.res.CommunityRes;
import com.example.up_final.domain.community.service.CommunityService;
import com.example.up_final.global.common.response.Response;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/community")
public class CommunityController {

    private final CommunityService communityService;

    @PostMapping("/post")
    public Response postCommunity(@RequestBody CommunityCreateReq req) {
        return communityService.saveCommunity(req);
    }

    @GetMapping("/list")
    public List<CommunityListRes> getCommunities(CommunitySearchReq req) {
        return communityService.getCommunities(req);
    }

    @GetMapping("/{id}")
    public CommunityRes getCommunity(@PathVariable("id") Long id){
        return communityService.getCommunity(id);
    }

    @PutMapping("{id}")
    public Response updateCommunity(@PathVariable("id") Long id, CommunityCreateReq req) {
        return communityService.updateCommunity(id, req);
    }

    @DeleteMapping("/{id}")
    public Response deleteCommunity(@PathVariable("id") Long id){
        return communityService.deleteCommunity(id);
    }

    @PutMapping("/like/{id}")
    public Response likeCommunity(@PathVariable("id") Long communityId) {
        return communityService.likeCommunity(communityId);
    }
}
