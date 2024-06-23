package com.example.chartist.api;

import com.example.chartist.domain.dto.MemberInfo;
import com.example.chartist.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberApiController {
    private final MemberService memberService;
    @GetMapping("/info")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<MemberInfo> getUserInfo(Principal principal) {
        MemberInfo info = memberService.findInfoByUserId(principal.getName());
        return ResponseEntity.status(HttpStatus.OK).body(info);
    }

}
