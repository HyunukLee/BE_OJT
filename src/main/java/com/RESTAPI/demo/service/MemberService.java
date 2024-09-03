package com.RESTAPI.demo.service;

import com.RESTAPI.demo.domain.Member;
import com.RESTAPI.demo.dto.MemberDto;
import com.RESTAPI.demo.exception.CharacterLimitException;
import com.RESTAPI.demo.exception.IllegalCharacterInUsernameException;
import com.RESTAPI.demo.exception.MemberAlreadyExistsException;
import com.RESTAPI.demo.exception.MemberWrongPasswordException;
import com.RESTAPI.demo.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void join(MemberDto memberDto) {
        Member member = memberBuild(memberDto);

        validateDuplicateMember(member);
        memberRepository.save(member);
    }

    private static Member memberBuild(MemberDto memberDto) {
        Member member = Member.builder()
                .username(memberDto.getUsername())
                .password(memberDto.getPassword())
                .build();
        return member;
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getUsername()).ifPresent(m -> {
            throw new MemberAlreadyExistsException("중복된 username입니다.");
        });
    }

    public void signIn(MemberDto memberDto) {
        Member member = memberBuild(memberDto);

        memberRepository.findByName(member.getUsername())
                .filter(m -> m.getPassword().equals(member.getPassword()))
                .orElseThrow(() -> new MemberWrongPasswordException("비밀번호가 틀렸습니다."));
    }

}
