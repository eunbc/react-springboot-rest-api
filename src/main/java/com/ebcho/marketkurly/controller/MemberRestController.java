package com.ebcho.marketkurly.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebcho.marketkurly.controller.dto.member.CreateMemberRequest;
import com.ebcho.marketkurly.controller.dto.member.MemberResponse;
import com.ebcho.marketkurly.controller.dto.member.UpdateMemberRequest;
import com.ebcho.marketkurly.service.MemberService;

@RestController
@RequestMapping("/api/v1/members")
public class MemberRestController {

	private final MemberService memberService;

	public MemberRestController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping
	public ResponseEntity<MemberResponse> createMember(@RequestBody CreateMemberRequest request) { //todo valid
		MemberResponse createdMember = memberService.createMember(request);
		return new ResponseEntity<>(createdMember, HttpStatus.CREATED); // todo Location 추가
	}

	@GetMapping
	public ResponseEntity<List<MemberResponse>> getAllMembers() {
		List<MemberResponse> members = memberService.getAllMembers();
		return new ResponseEntity<>(members, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MemberResponse> getMemberById(@PathVariable UUID id) {
		MemberResponse member = memberService.getMemberById(id);
		return new ResponseEntity<>(member, HttpStatus.OK);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<MemberResponse> updateMember(@PathVariable UUID id,
		@RequestBody UpdateMemberRequest request) {
		MemberResponse member = memberService.updateMember(id, request);
		return new ResponseEntity<>(member, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMember(@PathVariable UUID id) {
		memberService.deleteMember(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
