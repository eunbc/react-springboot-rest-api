package com.ebcho.marketkurly.controller.dto.member;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ebcho.marketkurly.model.Member;

public record MemberResponse(UUID memberId, String name, String email, String address, LocalDateTime createdAt,
							 LocalDateTime updatedAt) {
	public static MemberResponse of(Member member) {
		return new MemberResponse(member.getMemberId(), member.getName(), member.getEmail(), member.getAddress(),
			member.getCreatedAt(), member.getUpdatedAt());
	}
}
