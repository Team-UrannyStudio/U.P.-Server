package com.example.up_final.domain.member.presentation;

import com.example.up_final.domain.member.presentation.dto.req.MemberUpdateReq;
import com.example.up_final.domain.member.presentation.dto.res.MemberRes;
import com.example.up_final.domain.member.service.MemberService;
import com.example.up_final.global.common.response.Response;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{id}")
    public MemberRes getMember(@PathVariable("id") String id) {
        return memberService.findOneMember(id);
    }

    @PutMapping("/{id}")
    public Response updateMember(@PathVariable("id") String id, @RequestBody MemberUpdateReq req) {
        return memberService.updateMember(id, req);
    }

    @DeleteMapping("/{id}")
    public Response deleteMember(@PathVariable("id") String id) {
        return memberService.deleteMember(id);
    }
}
