package com.ebcho.marketkurly.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Member {
	private final UUID memberId;
	private final String name;
	private final String email;
	private String address;
	private final LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Member(UUID memberId, String name, String email, String address, LocalDateTime createdAt,
		LocalDateTime updatedAt) {
		this.memberId = memberId;
		this.name = name;
		this.email = email;
		this.address = address;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public UUID getMemberId() {
		return memberId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void changeAddress(String address) {
		this.address = address;
		this.updatedAt = LocalDateTime.now();
	}
}
