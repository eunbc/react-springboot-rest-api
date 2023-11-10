package com.ebcho.marketkurly.controller.dto.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateMemberRequest(@NotBlank
								  @Size(min = 10)
								  String address) {
}
