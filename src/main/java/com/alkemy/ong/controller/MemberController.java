package com.alkemy.ong.controller;

import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping
    public ResponseEntity<MemberDTO> saveMember(@Valid @RequestBody MemberDTO memberDTO){
        var newMember = memberService.saveMember(memberDTO);
        return ResponseEntity.status(HttpStatus.OK).body(newMember);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> updateMember(@Valid @RequestBody MemberDTO memberDTO,
                                                  @PathVariable String id){
        var saveMember = memberService.updateMember(id, memberDTO);
        return ResponseEntity.status(HttpStatus.OK).body(saveMember);
    }
}
