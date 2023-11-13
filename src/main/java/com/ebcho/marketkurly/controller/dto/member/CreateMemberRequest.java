package com.ebcho.marketkurly.controller.dto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateMemberRequest(
	@NotBlank
	String name,

	@NotBlank
	@Email
	String email,

	@NotBlank
	@Size(min = 10)
	String address
) {
}

