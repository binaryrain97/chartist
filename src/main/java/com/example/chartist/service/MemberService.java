package com.example.chartist.service;

import com.example.chartist.domain.dto.MemberCreateForm;
import com.example.chartist.domain.entity.Member;
import com.example.chartist.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(MemberCreateForm form) {
        Member member = Member.builder()
                .userId(form.getUserId())
                .password(passwordEncoder.encode(form.getPassword()))
                .build();
        memberRepository.save(member);
    }
}
