package com.example.up_final.global.jwt.presentation;

import com.example.up_final.domain.member.presentation.dto.req.MemberCreateReq;
import com.example.up_final.global.common.response.Response;
import com.example.up_final.global.jwt.presentation.dto.req.LoginReq;
import com.example.up_final.global.jwt.presentation.dto.req.TokenReq;
import com.example.up_final.global.jwt.presentation.dto.res.TokenRes;
import com.example.up_final.global.jwt.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public Response signup(@RequestBody MemberCreateReq req) {
        return authService.signup(req);
    }

    @PostMapping("/login")
    public TokenRes login(@RequestBody LoginReq req) {
        return authService.login(req);
    }

    @PostMapping("/reissue")
    public TokenRes reissue(@RequestBody TokenReq req) {
        return authService.reissue(req);
    }
}