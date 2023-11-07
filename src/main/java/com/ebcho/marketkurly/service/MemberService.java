package com.ebcho.marketkurly.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ebcho.marketkurly.controller.dto.CreateMemberRequest;
import com.ebcho.marketkurly.controller.dto.MemberResponseDto;
import com.ebcho.marketkurly.controller.dto.UpdateMemberRequest;
import com.ebcho.marketkurly.model.Member;
import com.ebcho.marketkurly.repository.MemberRepository;

@Service
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public MemberResponseDto createMember(CreateMemberRequest request) {
		Member member = new Member(UUID.randomUUID(), request.name(), request.email(), request.address(),
			LocalDateTime.now(), LocalDateTime.now());
		Member savedMember = memberRepository.insert(member);
		return MemberResponseDto.of(savedMember);
	}

	public List<MemberResponseDto> getAllMembers() {
		return memberRepository.findAll().stream()
			.map(MemberResponseDto::of)
			.toList();
	}

	public MemberResponseDto getMemberById(UUID id) {
		Member member = memberRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("Member with ID: " + id + " not found"));
		return MemberResponseDto.of(member);
	}

	public MemberResponseDto updateMember(UUID id, UpdateMemberRequest request) {
		Member member = memberRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("Member with ID: " + id + " not found"));
		member.changeAddress(request.address());
		memberRepository.update(member);
		return MemberResponseDto.of(member);
	}

	public void deleteMember(UUID id) {
		memberRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("Member with ID: " + id + " not found"));
		memberRepository.deleteById(id);
	}
}
