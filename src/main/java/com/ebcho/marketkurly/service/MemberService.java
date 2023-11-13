package com.ebcho.marketkurly.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ebcho.marketkurly.controller.dto.member.CreateMemberRequest;
import com.ebcho.marketkurly.controller.dto.member.MemberResponse;
import com.ebcho.marketkurly.controller.dto.member.UpdateMemberRequest;
import com.ebcho.marketkurly.model.Member;
import com.ebcho.marketkurly.repository.MemberRepository;

@Service
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public MemberResponse createMember(CreateMemberRequest request) {
		Member member = new Member(UUID.randomUUID(), request.name(), request.email(), request.address(),
			LocalDateTime.now(), LocalDateTime.now());
		Member savedMember = memberRepository.insert(member);
		return MemberResponse.of(savedMember);
	}

	public List<MemberResponse> getAllMembers() {
		return memberRepository.findAll().stream()
			.map(MemberResponse::of)
			.toList();
	}

	public MemberResponse getMemberById(UUID id) {
		Member member = memberRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("Member with ID: " + id + " not found"));
		return MemberResponse.of(member);
	}

	public MemberResponse updateMember(UUID id, UpdateMemberRequest request) {
		Member member = memberRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("Member with ID: " + id + " not found"));
		member.changeAddress(request.address());
		memberRepository.update(member);
		return MemberResponse.of(member);
	}

	public void deleteMember(UUID id) {
		memberRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("Member with ID: " + id + " not found"));
		memberRepository.deleteById(id);
	}
}
