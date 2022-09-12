package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.mapper.impl.MemberMapper;
import com.alkemy.ong.repository.MemberRepository;
import com.alkemy.ong.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    @Override
    public List<MemberDTO> getAllMembers() {
        var members = memberRepository.findAll();
        return members.stream().map(memberMapper::toDto).collect(Collectors.toList());
    }
}
