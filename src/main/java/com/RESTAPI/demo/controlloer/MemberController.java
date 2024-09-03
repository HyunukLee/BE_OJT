package com.RESTAPI.demo.controlloer;

import com.RESTAPI.demo.dto.CalculationDto;
import com.RESTAPI.demo.dto.MemberDto;

import com.RESTAPI.demo.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members/new")
    public void save(@Valid @RequestBody MemberDto memberDto) {
        memberService.join(memberDto);
    }

    @PostMapping("/members/sign-in")
    public void signIn(@RequestBody MemberDto memberDto) {
        memberService.signIn(memberDto);
    }

}
