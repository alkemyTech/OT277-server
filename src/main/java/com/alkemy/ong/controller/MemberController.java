package com.alkemy.ong.controller;

import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAllMembers(){
        var members = memberService.getAllMembers();
        return ResponseEntity.status(HttpStatus.OK).body(members);
    }
}
