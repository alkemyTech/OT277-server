package com.alkemy.ong.service;

import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.dto.PageableResponse;

import java.util.List;

public interface MemberService {

    PageableResponse getAllMembers(int page, int pageSize, String sortBy);

    MemberDTO saveMember(MemberDTO memberDTO);

    void deleteMember(String id);

    MemberDTO updateMember(String id, MemberDTO memberDTO);
}
