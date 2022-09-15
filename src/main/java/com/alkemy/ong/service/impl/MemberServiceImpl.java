package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.dto.NewDtoResponse;
import com.alkemy.ong.dto.PageableResponse;
import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.mapper.impl.MemberMapper;
import com.alkemy.ong.repository.MemberRepository;
import com.alkemy.ong.service.MemberService;
import com.alkemy.ong.utils.PageableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final PageableUtils pageableUtils;
    @Override
    public PageableResponse getAllMembers(int page, int pageSize, String sortBy) {
        Pageable p = PageRequest.of(page, pageSize, Sort.by(sortBy));
        var member = memberRepository.findAll(p);
        var allMembers = member.getContent();
        var memberDTO =allMembers.stream().map(memberMapper::toDto).collect(Collectors.toList());
        PageableResponse response = new PageableResponse();
        return pageableUtils.pageableUtils(member, memberDTO, response, page, pageSize);
    }

    @Override
    public MemberDTO saveMember(MemberDTO memberDTO) {
        return memberMapper.toDto(memberRepository.save(memberMapper.toEntity(memberDTO)));
    }

    @Override
    public void deleteMember(String id) {
        var member = memberRepository.findById(id).orElseThrow(
                ()-> new ParamNotFound("Member not found, id: "+ id));
        this.memberRepository.delete(member);
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
