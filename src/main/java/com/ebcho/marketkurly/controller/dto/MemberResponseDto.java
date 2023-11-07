package com.ebcho.marketkurly.controller.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ebcho.marketkurly.model.Member;

public record MemberResponseDto(UUID memberId, String name, String email, String address, LocalDateTime createdAt,
								LocalDateTime updatedAt) {
	public static MemberResponseDto of(Member member) {
		return new MemberResponseDto(member.getMemberId(), member.getName(), member.getEmail(), member.getAddress(),
			member.getCreatedAt(), member.getUpdatedAt());
	}
}
