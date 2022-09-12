package com.alkemy.ong.mapper.impl;

import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.entity.MemberEntity;
import com.alkemy.ong.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper implements Mapper<MemberDTO, MemberEntity> {
    @Override
    public MemberDTO toDto(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName(memberEntity.getName());
        memberDTO.setDescription(memberEntity.getDescription());
        memberDTO.setImage(memberEntity.getImage());
        memberDTO.setFacebookUrl(memberEntity.getFacebookUrl());
        memberDTO.setInstagramUrl(memberEntity.getInstagramUrl());
        memberDTO.setLinkedinUrl(memberEntity.getLinkedinUrl());
        memberDTO.setTimestamp(memberEntity.getTimestamp());
        return memberDTO;
    }

    @Override
    public MemberEntity toEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setName(memberDTO.getName());
        memberEntity.setDescription(memberDTO.getDescription());
        memberEntity.setImage(memberDTO.getImage());
        memberEntity.setFacebookUrl(memberDTO.getFacebookUrl());
        memberEntity.setInstagramUrl(memberDTO.getInstagramUrl());
        memberEntity.setLinkedinUrl(memberDTO.getLinkedinUrl());
        return memberEntity;
    }

    @Override
    public MemberDTO toBasicDto(MemberEntity memberEntity) {
        return null;
    }
}
