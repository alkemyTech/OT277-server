package com.alkemy.ong.controller;

import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.dto.PageableResponse;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.service.MemberService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping(produces = {"application/json"})
    @ApiOperation(value = "Get all member whit pagination",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK",
                    response = MemberDTO.class),
            @ApiResponse(code = 403, message = "PERMISSION_DENIED - Invalid mail o password",
                    response = ErrorResponse.class),
            @ApiResponse(code = 400, message = "Members not found",
                    response = ErrorResponse.class),})
    public ResponseEntity<PageableResponse> getAllMembers(
            @ApiParam(value = "number page")@RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @ApiParam(value = "page size")@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @ApiParam(value = "order by")@RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy){
        var members = memberService.getAllMembers(page, pageSize, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(members);
    }

    @PostMapping(produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Save member to organization",
            produces = "application/json",
            consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Member registered successfully",
                    response = MemberDTO.class),
            @ApiResponse(code = 403, message = "PERMISSION_DENIED - Invalid mail o password",
                    response = ErrorResponse.class),
            @ApiResponse(code = 400, message = "INVALID_ARGUMENT - Arguments cannot be null. ",
                    response = ErrorResponse.class)})
    public ResponseEntity<MemberDTO> saveMember(@ApiParam(value = "member id")@Valid @RequestBody MemberDTO memberDTO){
        var newMember = memberService.saveMember(memberDTO);
        return ResponseEntity.status(HttpStatus.OK).body(newMember);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a member", notes = "Delete member by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ACCEPTED - Member delete successfully"),
            @ApiResponse(code = 403, message = "PERMISSION_DENIED - Invalid mail o password",
                    response = ErrorResponse.class)
    })
    public ResponseEntity<Void> deleteMember(@ApiParam(value = "member id")@PathVariable String id){
        this.memberService.deleteMember(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "/{id}",
            produces = "application/json",
            consumes = "application/json")
    @ApiOperation(value = "Get member by id",
            produces = "application/json",
            consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Save member successfully",
                    response = MemberDTO.class),
            @ApiResponse(code = 403, message = "PERMISSION_DENIED - Invalid mail o password",
                    response = ErrorResponse.class),
            @ApiResponse(code = 400, message = "New not found",
                    response = ErrorResponse.class),})
    public ResponseEntity<MemberDTO> updateMember(@Valid @RequestBody MemberDTO memberDTO,
                                                  @ApiParam(value = "member id")@PathVariable String id){
        var saveMember = memberService.updateMember(id, memberDTO);
        return ResponseEntity.status(HttpStatus.OK).body(saveMember);
    }
}
