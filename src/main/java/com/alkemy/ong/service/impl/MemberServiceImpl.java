package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.exception.ParamNotFound;
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

    @Override
    public MemberDTO saveMember(MemberDTO memberDTO) {
        return memberMapper.toDto(memberRepository.save(memberMapper.toEntity(memberDTO)));
    }

    @Override
    public MemberDTO updateMember(String id, MemberDTO memberDTO) {
        var member = memberRepository.findById(id).orElseThrow(
                ()-> new ParamNotFound("Member whit id: "+id+" not found"));
        member.setName(memberDTO.getName());
        member.setDescription(memberDTO.getDescription());
        member.setFacebookUrl(memberDTO.getFacebookUrl());
        member.setInstagramUrl(memberDTO.getInstagramUrl());
        member.setLinkedinUrl(memberDTO.getLinkedinUrl());
        member.setImage(memberDTO.getImage());
        return memberMapper.toDto(memberRepository.save(member));
    }
}
