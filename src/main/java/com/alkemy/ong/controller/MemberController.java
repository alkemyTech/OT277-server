package com.alkemy.ong.controller;

import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.dto.PageableResponse;
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
    public ResponseEntity<PageableResponse> getAllMembers(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy){
        var members = memberService.getAllMembers(page, pageSize, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(members);
    }

    @PostMapping
    public ResponseEntity<MemberDTO> saveMember(@Valid @RequestBody MemberDTO memberDTO){
        var newMember = memberService.saveMember(memberDTO);
        return ResponseEntity.status(HttpStatus.OK).body(newMember);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable String id){
        this.memberService.deleteMember(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> updateMember(@Valid @RequestBody MemberDTO memberDTO,
                                                  @PathVariable String id){
        var saveMember = memberService.updateMember(id, memberDTO);
        return ResponseEntity.status(HttpStatus.OK).body(saveMember);
    }
}
